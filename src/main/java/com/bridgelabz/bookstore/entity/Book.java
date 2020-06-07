package com.bridgelabz.bookstore.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "bookinfo")
public class Book implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;
	private String bookName;
	
	private Long noOfBooks;
	private Double price;
	private String authorName;
	private String bookDetails;
	private LocalDateTime createdDateAndTime;
	private LocalDateTime updatedDateAndTime;
	private String status;
	private String image;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "book_id")
	private List<ReviewAndRating> reviewRating;

}