package net.javaguides.springboot.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public String getStatus() {
		return status;
	}

	@ManyToOne
	private User user;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> items;

	public void setUser(User user) {
		this.user = user;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private LocalDateTime orderDate;
	private Double totalAmount;
	private String status;
}
