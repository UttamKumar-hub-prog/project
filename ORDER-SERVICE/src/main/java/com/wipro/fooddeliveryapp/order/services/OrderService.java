package com.wipro.fooddeliveryapp.order.services;

import java.util.List;

import com.wipro.fooddeliveryapp.order.entitys.Order;

public interface OrderService {

	Order placeOrder(Order order);

	List<Order> getAllOrders();

	Order getOrderById(Long id);

	Order updateOrderStatus(Long id, String status);

	void deleteOrder(Long id);

}
