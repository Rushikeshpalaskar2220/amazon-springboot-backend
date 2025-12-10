package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.javaguides.springboot.dto.CategoryDTO;
import net.javaguides.springboot.dto.ProductDTO;
import net.javaguides.springboot.service.CategoryService;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<CategoryDTO>> getAllCategories() {
		return ResponseEntity.ok(categoryService.getAllCategories());
	}

	@GetMapping("/{categoryId}/products")
	public ResponseEntity<List<ProductDTO>> getProductsByCategoryId(@PathVariable Long categoryId) {
		List<ProductDTO> products = categoryService.getProductsByCategoryId(categoryId);
		return ResponseEntity.ok(products);
	}

	@PostMapping
	public ResponseEntity<CategoryDTO> createCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
		return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
		return ResponseEntity.ok(categoryService.getCategoryById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id,
			@RequestBody @Valid CategoryDTO categoryDTO) {
		return ResponseEntity.ok(categoryService.updateCategory(id, categoryDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return ResponseEntity.noContent().build();
	}
}
