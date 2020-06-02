package com.bridgelabz.bookstore.service;


import com.bridgelabz.bookstore.entity.Order;

public interface IOrderServices {
	Order  confrimOrder(String token);
	
}
