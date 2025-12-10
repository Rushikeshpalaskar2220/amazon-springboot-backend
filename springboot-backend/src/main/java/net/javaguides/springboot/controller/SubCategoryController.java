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
import net.javaguides.springboot.dto.ProductDTO;
import net.javaguides.springboot.dto.SubCategoryDTO;
import net.javaguides.springboot.service.SubCategoryService;

@RestController
@RequestMapping("/api/v1/subcategories")
public class SubCategoryController {

	@Autowired
	private SubCategoryService subCategoryService;

	@GetMapping
	public ResponseEntity<List<SubCategoryDTO>> getAllSubCategories() {
		return ResponseEntity.ok(subCategoryService.getAllSubCategories());
	}

	@GetMapping("/{subCategoryId}/products")
	public ResponseEntity<List<ProductDTO>> getProductsBySubCategoryId(@PathVariable Long subCategoryId) {
		List<ProductDTO> products = subCategoryService.getProductsBySubCategoryId(subCategoryId);
		return ResponseEntity.ok(products);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SubCategoryDTO> getSubCategoryById(@PathVariable Long id) {
		return ResponseEntity.ok(subCategoryService.getSubCategoryById(id));
	}

	@PostMapping
	public ResponseEntity<SubCategoryDTO> createSubCategory(@RequestBody @Valid SubCategoryDTO subCategoryDTO) {
		return ResponseEntity.ok(subCategoryService.createSubCategory(subCategoryDTO));
	}

	@PutMapping("/{id}")
	public ResponseEntity<SubCategoryDTO> updateSubCategory(@PathVariable Long id,
			@RequestBody @Valid SubCategoryDTO subCategoryDTO) {
		return ResponseEntity.ok(subCategoryService.updateSubCategory(id, subCategoryDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSubCategory(@PathVariable Long id) {
		subCategoryService.deleteSubCategory(id);
		return ResponseEntity.noContent().build();
	}
}
