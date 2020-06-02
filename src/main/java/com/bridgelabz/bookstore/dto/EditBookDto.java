package com.bridgelabz.bookstore.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class EditBookDto {
	private long bookId;
	private String bookName;
	private Long quantity;
	private Double price;
	private String authorName;
	private String image;
	private String bookDetails;
	private LocalDateTime updatedAt;
	

	
	
	
}
