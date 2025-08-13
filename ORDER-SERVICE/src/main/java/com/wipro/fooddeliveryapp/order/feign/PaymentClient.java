package com.wipro.fooddeliveryapp.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wipro.fooddeliveryapp.order.entitys.PaymentRequest;
import com.wipro.fooddeliveryapp.order.entitys.PaymentResponse;

@FeignClient(name = "PAYMENT-SERVICE")
public interface PaymentClient {
	@PostMapping("/payments")
	PaymentResponse processPayment(@RequestBody PaymentRequest request);
}