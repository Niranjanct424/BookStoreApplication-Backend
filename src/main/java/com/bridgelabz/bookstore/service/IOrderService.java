package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.entity.Order;

public interface IOrderService {

	Order getOrderConfrim(String token);

}
