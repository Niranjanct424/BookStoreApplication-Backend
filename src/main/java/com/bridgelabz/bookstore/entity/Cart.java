package com.bridgelabz.bookstore.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartId;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Book> booksList;
	
	@OneToMany
	private List<Quantity> quantityOfBook;
}