package com.bridgelabz.bookstore.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table
public class QuantityEntity 
{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long quantity_id;
	@Column
    private int quantity;
	
	public long getQuantity_id() 
	{
		return quantity_id;
	}
	public void setQuantity_id(long quantity_id) {
		this.quantity_id = quantity_id;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name ="bookId")	
	private BookInformation  bookquantity;
	
	public BookInformation getBookquantity() {
		return bookquantity;
	}
	public void setBookquantity(BookInformation bookquantity) {
		this.bookquantity = bookquantity;
	}
}
