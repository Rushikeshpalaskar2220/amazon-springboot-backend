package net.javaguides.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SubCategoryNotFoundException extends RuntimeException {
	public SubCategoryNotFoundException(String message) {
		super(message);
	}
}
