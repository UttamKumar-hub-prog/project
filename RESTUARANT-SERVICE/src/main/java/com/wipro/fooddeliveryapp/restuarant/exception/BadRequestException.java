package com.wipro.fooddeliveryapp.restuarant.exception;

public class BadRequestException extends RuntimeException {
	public BadRequestException(String message) {
		super(message);
	}
}
