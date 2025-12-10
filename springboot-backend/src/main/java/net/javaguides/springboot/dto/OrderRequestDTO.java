package net.javaguides.springboot.dto;

import java.util.List;

public class OrderRequestDTO {
	private List<OrderItemDTO> items;
	private Double totalAmount;

	// Getters and Setters
	public List<OrderItemDTO> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDTO> items) {
		this.items = items;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
}
