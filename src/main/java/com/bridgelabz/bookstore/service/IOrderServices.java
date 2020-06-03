package com.bridgelabz.bookstore.service;


import com.bridgelabz.bookstore.entity.Order;

public interface IOrderServices {
	 boolean confirmBooktoOrder(String token, Long bookId);
	Order  confrimOrder(String token);
	
	
}
