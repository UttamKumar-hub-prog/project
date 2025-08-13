package com.wipro.fooddeliveryapp.order.services;

import java.util.List;

import com.wipro.fooddeliveryapp.order.entitys.Order;
import com.wipro.fooddeliveryapp.order.entitys.OrderDTO;

public interface OrderService {

	OrderDTO placeOrder(OrderDTO orderDTO);

	OrderDTO getOrderById1(Long id);

	List<Order> getAllOrders();

	Order updateOrderStatus(Long id, String status);

	void deleteOrder(Long id);

	OrderDTO getOrderById(Long id);

	OrderDTO createOrder(Order order);

	 

	 

}
