package com.wipro.fooddeliveryapp.order.exception;


public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(String message) {
        super(message);
    }
}
