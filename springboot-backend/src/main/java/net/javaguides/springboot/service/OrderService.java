package net.javaguides.springboot.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.dto.OrderHistoryDTO;
import net.javaguides.springboot.dto.OrderItemDTO;
import net.javaguides.springboot.model.Order;
import net.javaguides.springboot.model.OrderItem;
import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.OrderRepository;
import net.javaguides.springboot.repository.ProductsRepository;
import net.javaguides.springboot.repository.UserRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductsRepository productsRepository;

	@Autowired
	private UserRepository userRepository;

	public Order placeOrder(String username, net.javaguides.springboot.dto.OrderRequestDTO dto) {
		User user = userRepository.findByUsername(username).orElseThrow();
		Order order = new Order();
		order.setUser(user);
		order.setOrderDate(LocalDateTime.now());
		order.setTotalAmount(dto.getTotalAmount());
		order.setStatus("PLACED");

		List<OrderItem> items = new ArrayList<>();
		for (net.javaguides.springboot.dto.OrderItemDTO itemDTO : dto.getItems()) {
			Product product = productsRepository.findById(itemDTO.getProductId()).orElseThrow();
			OrderItem item = new OrderItem();
			item.setOrder(order);
			item.setProduct(product);
			item.setQuantity(itemDTO.getQuantity());
			item.setPrice(itemDTO.getPrice());
			items.add(item);
		}

		order.setItems(items);
		return orderRepository.save(order);
	}

	public List<OrderHistoryDTO> getOrderHistory(String username) {
		List<Order> orders = orderRepository.findByUserUsername(username);
		List<OrderHistoryDTO> history = new ArrayList<>();

		for (Order order : orders) {
			OrderHistoryDTO dto = new OrderHistoryDTO();
			dto.setId(order.getId());
			dto.setOrderDate(order.getOrderDate());
			dto.setStatus(order.getStatus());
			dto.setTotalAmount(order.getTotalAmount());

			List<OrderItemDTO> itemDTOs = new ArrayList<>();
			for (OrderItem item : order.getItems()) {
				Product product = item.getProduct();
				OrderItemDTO itemDTO = new OrderItemDTO();
				itemDTO.setProductId(product.getId());
				itemDTO.setProductName(product.getName());
				itemDTO.setQuantity(item.getQuantity());
				itemDTO.setPrice(item.getPrice());
				itemDTOs.add(itemDTO);
			}

			dto.setItems(itemDTOs);
			history.add(dto);
		}

		return history;
	}
}
