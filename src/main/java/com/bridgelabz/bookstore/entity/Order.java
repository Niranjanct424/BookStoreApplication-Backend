package com.bridgelabz.bookstore.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity

public class Order {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	
	private LocalDateTime orderTime;
	
	private Long quantityOfBooks;
	
	@ManyToMany
	private List<Book> BooksList;

	public Order(Long orderId, LocalDateTime orderTime, Long quantityOfBooks, List<Book> booksList) {
		this.orderId = orderId;
		this.orderTime = orderTime;
		this.quantityOfBooks = quantityOfBooks;
		BooksList = booksList;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public LocalDateTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}

	public Long getQuantityOfBooks() {
		return quantityOfBooks;
	}

	public void setQuantityOfBooks(Long quantityOfBooks) {
		this.quantityOfBooks = quantityOfBooks;
	}

	public List<Book> getBooksList() {
		return BooksList;
	}

	public void setBooksList(List<Book> booksList) {
		BooksList = booksList;
	}
}
