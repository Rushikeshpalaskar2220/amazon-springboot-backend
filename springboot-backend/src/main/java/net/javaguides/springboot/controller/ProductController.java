package net.javaguides.springboot.controller;

import java.util.List;
import java.util.UUID;

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
import net.javaguides.springboot.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<List<ProductDTO>> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());
	}

	@PostMapping
	public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO productDTO) {
		return ResponseEntity.ok(productService.createProduct(productDTO));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable UUID id) {
		return ResponseEntity.ok(productService.getProductById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable UUID id, @RequestBody @Valid ProductDTO productDTO) {
		return ResponseEntity.ok(productService.updateProduct(id, productDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
}
