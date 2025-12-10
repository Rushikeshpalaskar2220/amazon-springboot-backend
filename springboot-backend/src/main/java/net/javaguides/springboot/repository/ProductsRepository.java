package net.javaguides.springboot.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.model.Product;

public interface ProductsRepository extends JpaRepository<Product, UUID> {
}
