package com.wipro.fooddeliveryapp.order.exception;


public class MenuItemNotFoundException extends RuntimeException {
    public MenuItemNotFoundException(String message) {
        super(message);
    }
}
