package com.wipro.fooddeliveryapp.order.repositoryy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.fooddeliveryapp.order.entitys.Order;
import com.wipro.fooddeliveryapp.order.entitys.OrderDTO;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	Order save(OrderDTO existingOrder);

}
