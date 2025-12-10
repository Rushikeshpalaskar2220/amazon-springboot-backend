package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.model.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
}
