//
//package com.bridgelabz.bookstore.entity;
//
//import java.time.LocalDateTime;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
//@Entity
//
//public class Order {
//
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long orderId;
//	
//	private LocalDateTime orderTime;
//	
//	private Long quantityOfBooks;
//	
////	@ManyToOne
////	private List<Book> BooksList;
//
//
//	public Long getOrderId() {
//		return orderId;
//	}
//
//	public void setOrderId(Long orderId) {
//		this.orderId = orderId;
//	}
//
//	public LocalDateTime getOrderTime() {
//		return orderTime;
//	}
//
//	public void setOrderTime(LocalDateTime orderTime) {
//		this.orderTime = orderTime;
//	}
//
//	public Long getQuantityOfBooks() {
//		return quantityOfBooks;
//	}
//
//	public void setQuantityOfBooks(Long quantityOfBooks) {
//		this.quantityOfBooks = quantityOfBooks;
//	}
//
//}
