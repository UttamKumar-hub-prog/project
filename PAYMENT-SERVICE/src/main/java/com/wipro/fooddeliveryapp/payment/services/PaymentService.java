package com.wipro.fooddeliveryapp.payment.services;

import com.wipro.fooddeliveryapp.payment.entity.Payment;

public interface PaymentService {

	Payment processPayment(Long orderId, String paymentMethod);

	Payment completePayment(Long paymentId);

	Payment cancelPayment(Long paymentId);

}
