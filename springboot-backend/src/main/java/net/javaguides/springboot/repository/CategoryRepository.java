package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
