package com.bridgelabz.bookstore.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "bookinfo")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bookId;
	
	
	private String bookName;

	private int quantity;

	private Double price;

	private String authorName;

	private String bookDetails;
	private LocalDateTime createdDateAndTime;
	private LocalDateTime updatedDateAndTime;
	
	private String status;
	
	private String image;


	public Book() {
		super();
	}
	
	public Book(int bookId, String bookName, int quantity, Double price, String authorName,
			String bookDetails, String image, LocalDateTime createdDateAndTime) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.quantity = quantity;
		this.price = price;
		this.authorName = authorName;
		this.bookDetails = bookDetails;
		this.image = image;
		this.createdDateAndTime = createdDateAndTime;
	}
}