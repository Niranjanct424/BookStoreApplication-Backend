package com.bridgelabz.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ratingId;

	@Column(name = "rating")
	private Integer rating;
	
	@Column(name = "review")
	private String review;
	
	@Column(name = "user_name")
	private String userName;
	
	
		
}