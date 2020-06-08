package com.bridgelabz.bookstore.service;


import java.util.List;

import com.bridgelabz.bookstore.entity.Order;


public interface IOrderServices {
	 boolean confirmBooktoOrder(String token, Long bookId);
	Order  confrimOrder(String token);
	int getCountOfBooks(String token);
	 List<Order> getOrderList(String token);
	
}
