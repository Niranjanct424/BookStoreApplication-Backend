package com.bridgelabz.bookstore.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cartinfo")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cartId;
	//private LocalDateTime placeTime;
	@OneToOne
	private Users userId;
	@ManyToMany
	@JsonIgnore
	private List<Book> bookId;

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public Users getUserId() {
		return userId;
	}

	public void setUserId(Users userId) {
		this.userId = userId;
	}

	public List<Book> getBookId() {
		return bookId;
	}

	public void setBookId(List<Book> bookId) {
		this.bookId = bookId;
	}

}