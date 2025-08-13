package com.wipro.fooddeliveryapp.payment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.fooddeliveryapp.order.entitys.Order;
import com.wipro.fooddeliveryapp.payment.entity.Payment;
import com.wipro.fooddeliveryapp.payment.feign.OrderClient;
import com.wipro.fooddeliveryapp.payment.repositorys.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderClient orderClient;  

	public Payment processPayment(Long orderId, String paymentMethod) {
		 
		Order order = orderClient.getOrderById(orderId);

		if (order == null) {
			throw new RuntimeException("Order not found");
		}

		 
		Payment payment = new Payment();
		payment.setOrderId(orderId);
		payment.setAmount(order.getTotalAmount());
		payment.setPaymentMethod(paymentMethod);
		payment.setPaymentStatus("Pending"); 
		payment.setTransactionId(generateTransactionId());  

		 
		return paymentRepository.save(payment);
	}

	public Payment completePayment(Long paymentId) {
		Payment payment = paymentRepository.findById(paymentId)
				.orElseThrow(() -> new RuntimeException("Payment not found"));

		payment.setPaymentStatus("Completed");

		return paymentRepository.save(payment);
	}

	public Payment cancelPayment(Long paymentId) {
		Payment payment = paymentRepository.findById(paymentId)
				.orElseThrow(() -> new RuntimeException("Payment not found"));

		payment.setPaymentStatus("Cancelled");

		return paymentRepository.save(payment);
	}

	private String generateTransactionId() {
		 
		return "TXN" + System.currentTimeMillis();
	}
}