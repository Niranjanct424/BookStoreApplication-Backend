package com.bridgelabz.bookstore.service;


import java.util.List;

import com.bridgelabz.bookstore.entity.Order;


public interface IOrderServices {
	
    boolean confirmBooktoOrder(String token, Long bookId);
    
	Order  placeOrder(String token ,Long bookId, Long addressId);
	
//	List<Order> getOrderList(String orderStatus);

//	int changeOrderStatus(String orderStatus, Long orderId);
	

	int getCountOfBooks(String token);
	
	 List<Order> getOrderList(String token);

	
	
	List<Order> getallOrders();

	int changeOrderStatus(String status,long orderId);

	List<Order> getInProgressOrders();

	
}
