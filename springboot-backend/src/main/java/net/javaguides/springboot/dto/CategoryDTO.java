package net.javaguides.springboot.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CategoryDTO {

	private Long id;

	@NotBlank
	private String name;

	@NotNull
	private List<SubCategoryDTO> subCategories;

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SubCategoryDTO> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<SubCategoryDTO> subCategories) {
		this.subCategories = subCategories;
	}
}
