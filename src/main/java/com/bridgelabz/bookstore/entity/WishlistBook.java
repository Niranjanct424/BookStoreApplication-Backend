package com.bridgelabz.bookstore.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Data
@Table(name="wishbook")
public class WishlistBook {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long wishlistId;
    
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Book> booksList;
	
	private LocalDateTime wishlistTime;
	
	
}
