package com.bridgelabz.bookstore.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cartId;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Book> booksList;
	
	@OneToMany
	private List<Quantity> quantityOfBook;

	public List<Book> getBooksList() {
		return booksList;
	}

	public void setBooksList(List<Book> booksList) {
		this.booksList = booksList;
	}


	

	public List<Quantity> getQuantityOfBook() {
		return quantityOfBook;
	}

	public void setQuantityOfBook(List<Quantity> quantityOfBook) {
		this.quantityOfBook = quantityOfBook;
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	

}