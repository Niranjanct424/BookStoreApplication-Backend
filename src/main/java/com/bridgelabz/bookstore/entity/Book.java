package com.bridgelabz.bookstore.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
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

}