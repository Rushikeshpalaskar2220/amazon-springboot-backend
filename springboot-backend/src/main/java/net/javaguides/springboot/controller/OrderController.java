package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.dto.OrderHistoryDTO;
import net.javaguides.springboot.dto.OrderRequestDTO;
import net.javaguides.springboot.model.Order;
import net.javaguides.springboot.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping
	public ResponseEntity<?> placeOrder(@RequestBody OrderRequestDTO orderRequest, Authentication auth) {
		String username = auth.getName();
		Order order = orderService.placeOrder(username, orderRequest);
		return ResponseEntity.ok(order);
	}

	@GetMapping("/history")
	public ResponseEntity<List<OrderHistoryDTO>> getOrderHistory(Authentication auth) {
		String username = auth.getName();
		return ResponseEntity.ok(orderService.getOrderHistory(username));
	}

}
