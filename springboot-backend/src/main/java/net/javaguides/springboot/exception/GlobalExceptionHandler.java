package net.javaguides.springboot.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	// Handle ResourceNotFoundException for Products
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleProductNotFound(ProductNotFoundException ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("timestamp", LocalDateTime.now());
		errorDetails.put("Code", 101);
		errorDetails.put("error", "Product Not Found");
		errorDetails.put("message", ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	// Handle ResourceNotFoundException for Categories
	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleCategoryNotFound(CategoryNotFoundException ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("timestamp", LocalDateTime.now());
		errorDetails.put("code", 222);
		errorDetails.put("error", "Category Not Found");
		errorDetails.put("message", ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	// Handle ResourceNotFoundException for Subcategories
	@ExceptionHandler(SubCategoryNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleSubCategoryNotFound(SubCategoryNotFoundException ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("timestamp", LocalDateTime.now());
		errorDetails.put("code", 304);
		errorDetails.put("error", "Subcategory Not Found");
		errorDetails.put("message", ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	// Handle validation errors
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("timestamp", LocalDateTime.now());
		errorDetails.put("status", HttpStatus.BAD_REQUEST.value());
		errorDetails.put("error", "Validation Failed");

		Map<String, String> fieldErrors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));
		errorDetails.put("message", fieldErrors);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	// Handle all other exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("timestamp", LocalDateTime.now());
		errorDetails.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorDetails.put("error", "Internal Server Error");
		errorDetails.put("message", ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
