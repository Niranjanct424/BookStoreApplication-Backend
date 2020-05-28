package com.bridgelabz.bookstore.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "bookinfo")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bookId;
	
	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	private String bookName;

	private int quantity;

	private Double price;

	private String authorName;

	private String bookDetails;
	private LocalDateTime createdDateAndTime;
	private LocalDateTime updatedDateAndTime;
	
	private boolean isApproved;
	
	private String image;
	@ManyToMany(mappedBy = "bookId")
    @JsonIgnore
    private List<Cart> cartId;

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

	

	

	public LocalDateTime getUpdatedDateAndTime() {
		return updatedDateAndTime;
	}

	public void setUpdatedDateAndTime(LocalDateTime updatedDateAndTime) {
		this.updatedDateAndTime = updatedDateAndTime;
	}

	public List<Cart> getCartId() {
		return cartId;
	}

	public void setCartId(List<Cart> cartId) {
		this.cartId = cartId;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getBookDetails() {
		return bookDetails;
	}

	public void setBookDetails(String bookDetails) {
		this.bookDetails = bookDetails;
	}

	public LocalDateTime getCreatedDateAndTime() {
		return createdDateAndTime;
	}

	public void setCreatedDateAndTime(LocalDateTime createdDateAndTime) {
		this.createdDateAndTime = createdDateAndTime;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


}