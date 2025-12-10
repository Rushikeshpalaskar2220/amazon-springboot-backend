package net.javaguides.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.dto.ProductDTO;
import net.javaguides.springboot.exception.ProductNotFoundException;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.model.SubCategory;
import net.javaguides.springboot.repository.ProductsRepository;
import net.javaguides.springboot.repository.SubCategoryRepository;

@Service
public class ProductService {

	@Autowired
	private ProductsRepository productsRepository;

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	public List<ProductDTO> getAllProducts() {
		List<Product> products = productsRepository.findAll();
		List<ProductDTO> dtos = new ArrayList<>();
		for (Product product : products) {
			dtos.add(toDTO(product));
		}
		return dtos;
	}

	public ProductDTO getProductById(UUID id) {
		Product product = productsRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
		return toDTO(product);
	}

	public ProductDTO createProduct(ProductDTO dto) {
		SubCategory subCategory = subCategoryRepository.findById(dto.getSubcategoryId()).orElseThrow(
				() -> new ResourceNotFoundException("SubCategory not found with id: " + dto.getSubcategoryId()));

		Product product = new Product();
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setSubcategory(subCategory);
		product.setPrice(dto.getPrice());
		dto.setPrice(product.getPrice());
		return toDTO(productsRepository.save(product));
	}

	public ProductDTO updateProduct(UUID id, ProductDTO dto) {
		Product product = productsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

		SubCategory subCategory = subCategoryRepository.findById(dto.getSubcategoryId()).orElseThrow(
				() -> new ResourceNotFoundException("SubCategory not found with id: " + dto.getSubcategoryId()));

		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setSubcategory(subCategory);

		return toDTO(productsRepository.save(product));
	}

	public void deleteProduct(UUID id) {
		Product product = productsRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
		productsRepository.delete(product);
	}

	private ProductDTO toDTO(Product product) {
		ProductDTO dto = new ProductDTO();
		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setDescription(product.getDescription());
		dto.setSubcategoryId(product.getSubcategory().getId());
		dto.setSubcategoryName(product.getSubcategory().getName());
		dto.setPrice(product.getPrice());

		return dto;
	}
}
