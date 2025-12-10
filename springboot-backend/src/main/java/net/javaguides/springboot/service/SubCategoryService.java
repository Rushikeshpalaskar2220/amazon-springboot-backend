package net.javaguides.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.dto.ProductDTO;
import net.javaguides.springboot.dto.SubCategoryDTO;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.exception.SubCategoryNotFoundException;
import net.javaguides.springboot.model.Category;
import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.model.SubCategory;
import net.javaguides.springboot.repository.CategoryRepository;
import net.javaguides.springboot.repository.SubCategoryRepository;

@Service
public class SubCategoryService {

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public List<SubCategoryDTO> getAllSubCategories() {
		List<SubCategory> subCategories = subCategoryRepository.findAll();
		List<SubCategoryDTO> dtos = new ArrayList<>();
		for (SubCategory sub : subCategories) {
			dtos.add(toDTO(sub));
		}
		return dtos;
	}

	public List<ProductDTO> getProductsBySubCategoryId(Long subCategoryId) {
		SubCategory subCategory = subCategoryRepository.findById(subCategoryId)
				.orElseThrow(() -> new SubCategoryNotFoundException("SubCategory not found with id: " + subCategoryId));

		List<ProductDTO> productDTOs = new ArrayList<>();
		for (Product product : subCategory.getProducts()) {
			ProductDTO dto = new ProductDTO();
			dto.setId(product.getId());
			dto.setName(product.getName());
			dto.setDescription(product.getDescription());
			dto.setSubcategoryId(subCategory.getId());
			dto.setSubcategoryName(subCategory.getName());
			dto.setPrice(product.getPrice());

			productDTOs.add(dto);
		}
		return productDTOs;
	}

	public SubCategoryDTO getSubCategoryById(Long id) {
		SubCategory subCategory = subCategoryRepository.findById(id)
				.orElseThrow(() -> new SubCategoryNotFoundException("SubCategory not found with id: " + id));
		return toDTO(subCategory);
	}

	public SubCategoryDTO createSubCategory(SubCategoryDTO dto) {
		Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(
				() -> new SubCategoryNotFoundException("Category not found with id: " + dto.getCategoryId()));

		SubCategory subCategory = new SubCategory();
		subCategory.setName(dto.getName());
		subCategory.setCategory(category);

		return toDTO(subCategoryRepository.save(subCategory));
	}

	public SubCategoryDTO updateSubCategory(Long id, SubCategoryDTO dto) {
		SubCategory subCategory = subCategoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("SubCategory not found with id: " + id));

		Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(
				() -> new SubCategoryNotFoundException("Category not found with id: " + dto.getCategoryId()));

		subCategory.setName(dto.getName());
		subCategory.setCategory(category);

		return toDTO(subCategoryRepository.save(subCategory));
	}

	public void deleteSubCategory(Long id) {
		SubCategory subCategory = subCategoryRepository.findById(id)
				.orElseThrow(() -> new SubCategoryNotFoundException("SubCategory not found with id: " + id));
		subCategoryRepository.delete(subCategory);
	}

	private SubCategoryDTO toDTO(SubCategory subCategory) {
		SubCategoryDTO dto = new SubCategoryDTO();
		dto.setId(subCategory.getId());
		dto.setName(subCategory.getName());
		dto.setCategoryId(subCategory.getCategory().getId());

		List<ProductDTO> productDTOs = new ArrayList<>();
		for (Product product : subCategory.getProducts()) {
			ProductDTO prodDTO = new ProductDTO();
			prodDTO.setId(product.getId());
			prodDTO.setName(product.getName());
			prodDTO.setDescription(product.getDescription());
			prodDTO.setSubcategoryId(subCategory.getId());
			prodDTO.setSubcategoryName(subCategory.getName());
			prodDTO.setPrice(product.getPrice());
			productDTOs.add(prodDTO);
		}

		dto.setProducts(productDTOs);
		return dto;
	}

}
