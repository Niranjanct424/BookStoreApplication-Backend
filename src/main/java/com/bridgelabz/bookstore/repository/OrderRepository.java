package com.bridgelabz.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
//	@Modifying
//	@Query("update from order_details set order_status=:status where order_id=:orderId")
//	String updateOrderStatus(String status,long orderId);

	@Modifying
	@Query("update Order set order_status=:status where order_id=:orderId")
	int OrderStatus(String status,long orderId);
}
