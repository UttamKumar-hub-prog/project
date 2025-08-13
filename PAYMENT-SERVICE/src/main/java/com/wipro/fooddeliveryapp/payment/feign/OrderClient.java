package com.wipro.fooddeliveryapp.payment.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wipro.fooddeliveryapp.order.entitys.Order;

@FeignClient(name = "order-service", url = "http://localhost:8082")
public interface OrderClient {

	@GetMapping("/api/v1/order/getOrderById")
	Order getOrderById(@RequestParam Long orderId);
}
