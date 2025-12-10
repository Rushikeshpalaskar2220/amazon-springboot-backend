package net.javaguides.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.dto.CategoryDTO;
import net.javaguides.springboot.dto.ProductDTO;
import net.javaguides.springboot.dto.SubCategoryDTO;
import net.javaguides.springboot.exception.CategoryNotFoundException;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Category;
import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.model.SubCategory;
import net.javaguides.springboot.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public List<CategoryDTO> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryDTO> categoryDTOs = new ArrayList<>();
		for (Category category : categories) {
			categoryDTOs.add(toDTO(category));
		}
		return categoryDTOs;
	}

	public CategoryDTO getCategoryById(Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
		return toDTO(category);
	}

	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		Category category = new Category();
		category.setName(categoryDTO.getName());

		List<SubCategory> subCategoryEntities = new ArrayList<>();
		for (SubCategoryDTO subDTO : categoryDTO.getSubCategories()) {
			SubCategory subCategory = new SubCategory();
			subCategory.setName(subDTO.getName());
			subCategory.setCategory(category); // Set parent

			List<Product> productEntities = new ArrayList<>();
			for (ProductDTO prodDTO : subDTO.getProducts()) {
				Product product = new Product();
				product.setName(prodDTO.getName());
				product.setDescription(prodDTO.getDescription());
				product.setPrice(prodDTO.getPrice());
				product.setSubcategory(subCategory); // Set parent
				productEntities.add(product);
			}

			subCategory.setProducts(productEntities);
			subCategoryEntities.add(subCategory);
		}

		category.setSubCategories(subCategoryEntities);
		Category saved = categoryRepository.save(category);
		return toDTO(saved);
	}

	public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));

		category.setName(categoryDTO.getName());

		List<SubCategory> updatedSubCategories = new ArrayList<>();

		for (SubCategoryDTO subDTO : categoryDTO.getSubCategories()) {
			SubCategory subCategory;

			if (subDTO.getId() != null) {
				subCategory = category.getSubCategories().stream().filter(s -> s.getId().equals(subDTO.getId()))
						.findFirst().orElse(new SubCategory());
			} else {
				subCategory = new SubCategory();
			}

			subCategory.setName(subDTO.getName());
			subCategory.setCategory(category);

			List<Product> updatedProducts = new ArrayList<>();
			for (ProductDTO prodDTO : subDTO.getProducts()) {
				Product product;

				if (prodDTO.getId() != null) {
					product = subCategory.getProducts().stream().filter(p -> p.getId().equals(prodDTO.getId()))
							.findFirst().orElse(new Product());
				} else {
					product = new Product();
				}

				product.setName(prodDTO.getName());
				product.setDescription(prodDTO.getDescription());
				product.setPrice(prodDTO.getPrice());
				product.setSubcategory(subCategory);
				updatedProducts.add(product);
			}

			subCategory.setProducts(updatedProducts);
			updatedSubCategories.add(subCategory);
		}

		category.setSubCategories(updatedSubCategories);
		Category saved = categoryRepository.save(category);
		return toDTO(saved);
	}

	public void deleteCategory(Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
		categoryRepository.delete(category);
	}

	private CategoryDTO toDTO(Category category) {
		CategoryDTO dto = new CategoryDTO();
		dto.setId(category.getId());
		dto.setName(category.getName());

		List<SubCategoryDTO> subDTOs = new ArrayList<>();
		for (SubCategory sub : category.getSubCategories()) {
			SubCategoryDTO subDTO = new SubCategoryDTO();
			subDTO.setId(sub.getId());
			subDTO.setName(sub.getName());

			if (sub.getCategory() != null) {
				subDTO.setCategoryId(sub.getCategory().getId());
			} else {
				subDTO.setCategoryId(null); // or log a warning
			}

			List<ProductDTO> prodDTOs = new ArrayList<>();
			for (Product prod : sub.getProducts()) {
				ProductDTO prodDTO = new ProductDTO();
				prodDTO.setId(prod.getId());
				prodDTO.setName(prod.getName());
				prodDTO.setDescription(prod.getDescription());
				prodDTO.setSubcategoryId(sub.getId());
				prodDTO.setPrice(prod.getPrice());
				prodDTO.setSubcategoryName(sub.getName());
				prodDTOs.add(prodDTO);
			}

			subDTO.setProducts(prodDTOs);
			subDTOs.add(subDTO);
		}

		dto.setSubCategories(subDTOs);
		return dto;
	}

	public List<ProductDTO> getProductsByCategoryId(Long categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));
		List<ProductDTO> productDTOs = new ArrayList<>();
		for (SubCategory sub : category.getSubCategories()) {
			for (Product product : sub.getProducts()) {
				ProductDTO dto = new ProductDTO();
				dto.setId(product.getId());
				dto.setName(product.getName());
				dto.setDescription(product.getDescription());
				dto.setSubcategoryId(sub.getId());
				dto.setPrice(product.getPrice());
				dto.setSubcategoryName(sub.getName());
				productDTOs.add(dto);
			}
		}
		return productDTOs;
	}

}
