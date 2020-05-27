/**
 * 
 */
package com.bridgelabz.bookstore.exception;

/**
 * @author HP
 *
 */
public class UserException extends RuntimeException {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserException(String message) {
		this.message = message;

	}

}
