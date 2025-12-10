package net.javaguides.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByUserUsername(String username);
}
