package com.wipro.fooddeliveryapp.payment.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.fooddeliveryapp.payment.entity.Payment;
import com.wipro.fooddeliveryapp.payment.services.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PaymentController {
	  
	    private final  PaymentService paymentService;

	    @PostMapping("/process")
	    public Payment processPayment(@RequestParam Long orderId, @RequestParam String paymentMethod) {
	        return paymentService.processPayment(orderId, paymentMethod);
	    }

	    @PostMapping("/complete/{paymentId}")
	    public Payment completePayment(@PathVariable Long paymentId) {
	        return paymentService.completePayment(paymentId);
	    }

	    @PostMapping("/cancel/{paymentId}")
	    public Payment cancelPayment(@PathVariable Long paymentId) {
	        return paymentService.cancelPayment(paymentId);
	    }
}
