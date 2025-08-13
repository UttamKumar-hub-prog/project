package com.wipro.fooddeliveryapp.order.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.fooddeliveryapp.order.entitys.MenuDTO;
import com.wipro.fooddeliveryapp.order.entitys.NotificationDTO;
import com.wipro.fooddeliveryapp.order.entitys.Order;
import com.wipro.fooddeliveryapp.order.entitys.OrderDTO;
import com.wipro.fooddeliveryapp.order.entitys.PaymentRequest;
import com.wipro.fooddeliveryapp.order.entitys.PaymentResponse;
import com.wipro.fooddeliveryapp.order.feign.MenuClient;
import com.wipro.fooddeliveryapp.order.feign.NotificationClient;
import com.wipro.fooddeliveryapp.order.feign.PaymentClient;
import com.wipro.fooddeliveryapp.order.feign.RestaurantClient;
import com.wipro.fooddeliveryapp.order.repositoryy.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final MenuClient menuClient;
	private final RestaurantClient restaurantClient;
	private final PaymentClient paymentClient;
	private final NotificationClient notificationClient; // ✅ make it final so it's injected

	@Override
	public OrderDTO placeOrder(OrderDTO orderDTO) {
		// Fetch menu details from Menu Service
		List<MenuDTO> menuItems = menuClient.getMenusByRestaurantId(orderDTO.getRestaurantId());

		// Calculate total amount
		double totalAmount = menuItems.stream().filter(item -> orderDTO.getMenuItemIds().contains(item.getId()))
				.mapToDouble(MenuDTO::getPrice).sum();

		orderDTO.setTotalAmount(totalAmount);

		// Save order in DB
		Order order = new Order();
		order.setCustomerId(orderDTO.getCustomerId());
		order.setRestaurantId(orderDTO.getRestaurantId());
		order.setMenuItemIds(orderDTO.getMenuItemIds());
		order.setTotalAmount(totalAmount);
		order.setOrderStatus("PENDING");
		order = orderRepository.save(order);

		orderDTO.setOrderId(order.getOrderId());

		// Process payment
		PaymentRequest paymentRequest = new PaymentRequest(order.getOrderId(), totalAmount);
		PaymentResponse paymentResponse = paymentClient.processPayment(paymentRequest);

		// Update status
		order.setOrderStatus(paymentResponse.getStatus());
		orderRepository.save(order);

		orderDTO.setOrderStatus(paymentResponse.getStatus());

		// ✅ Send notification
		String message = "Your order #" + order.getOrderId() + " is " + paymentResponse.getStatus();
		notificationClient.sendNotification(new NotificationDTO(order.getOrderId(), "customer@example.com", message));

		return orderDTO;
	}

	@Override
	public OrderDTO getOrderById1(Long id) {
		Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));

		return new OrderDTO(order.getOrderId(), order.getCustomerId(), order.getRestaurantId(), order.getMenuItemIds(),
				order.getTotalAmount(), order.getOrderStatus());
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order updateOrderStatus(Long id, String status) {
		Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
		order.setOrderStatus(status);
		return orderRepository.save(order);
	}

	@Override
	public void deleteOrder(Long id) {
		orderRepository.deleteById(id);
	}

	@Override
	public OrderDTO getOrderById(Long id) {
		return getOrderById1(id);
	}

	@Override
	public OrderDTO createOrder(Order order) {
		Order saved = orderRepository.save(order);
		return getOrderById1(saved.getOrderId());
	}

}
