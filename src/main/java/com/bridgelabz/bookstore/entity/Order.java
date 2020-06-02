
package com.bridgelabz.bookstore.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="order_details")
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private Long orderId;
	@Column(name = "order_placed_time", nullable = false)
	private LocalDateTime orderPlaceTime;
	
	//private Double totalPrice;
	
	@OneToMany
	(cascade = CascadeType.ALL, targetEntity = Quantity.class)
	@JoinColumn(name = "orderId")
	private List<Quantity> QuantityOfBooks;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Book> BooksList;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public LocalDateTime getOrderPlaceTime() {
		return orderPlaceTime;
	}

	public void setOrderPlaceTime(LocalDateTime orderPlaceTime) {
		this.orderPlaceTime = orderPlaceTime;
	}

	public List<Quantity> getQuantityOfBooks() {
		return QuantityOfBooks;
	}

	public void setQuantityOfBooks(List<Quantity> quantityOfBooks) {
		QuantityOfBooks = quantityOfBooks;
	}

	public List<Book> getBooksList() {
		return BooksList;
	}

	public void setBooksList(List<Book> booksList) {
		BooksList = booksList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}