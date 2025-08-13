package com.wipro.fooddeliveryapp.order.services;

 
import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.fooddeliveryapp.menu.entity.Menu;
import com.wipro.fooddeliveryapp.order.entitys.Order;
import com.wipro.fooddeliveryapp.order.entitys.RestaurantDTO;
import com.wipro.fooddeliveryapp.order.exception.MenuItemNotFoundException;
import com.wipro.fooddeliveryapp.order.exception.OrderNotFoundException;
import com.wipro.fooddeliveryapp.order.exception.RestaurantNotFoundException;
import com.wipro.fooddeliveryapp.order.feign.MenuClient;
import com.wipro.fooddeliveryapp.order.feign.RestaurantClient;
import com.wipro.fooddeliveryapp.order.repositoryy.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MenuClient menuClient;   
    private final RestaurantClient restaurantClient;   

    @Override
    public Order placeOrder(Order order) {
        // Verify if the restaurant exists
        RestaurantDTO restaurant = restaurantClient.getRestaurantById(order.getRestaurantId());
        if (restaurant == null) {
            throw new RestaurantNotFoundException("Restaurant not found");
        }

        // Verify if the menu items exist
        List<Menu> menus = menuClient.getMenusByIds(order.getMenuItemIds());
        if (menus.isEmpty()) {
            throw new MenuItemNotFoundException("Menu items not found");
        }

        // Calculate total amount
        double totalAmount = 0;
        for (Menu menu : menus) {
            totalAmount += menu.getPrice();  // Sum up the price directly
        }

        // Set total amount and order status
        order.setTotalAmount(totalAmount);
        order.setOrderStatus("Pending");

        // Save and return the order
        return orderRepository.save(order);
    }


    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + id));
    }

    @Override
    public Order updateOrderStatus(Long id, String status) {
        Order existingOrder = getOrderById(id);
        existingOrder.setOrderStatus(status);
        return orderRepository.save(existingOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}