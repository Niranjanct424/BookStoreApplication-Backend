package com.bridgelabz.bookstore.exception;

public class AdminNotFoundException extends RuntimeException{
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AdminNotFoundException(String message) {
		this.message = message;

	}
}
