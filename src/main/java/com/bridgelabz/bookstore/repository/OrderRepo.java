package com.bridgelabz.bookstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bridgelabz.bookstore.entity.Order;

public interface OrderRepo extends CrudRepository<Order, Long> {
	
//	List<Order>  findByOrderStatus(String status);
	
//	Order findByOrderByOrderId(Long orderId);
	
//	List<Order>  findBooksList();
	
	
}
