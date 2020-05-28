package com.bridgelabz.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class Quantity 
{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long quantity_id;
    @Column
    private int quantityOfBook;
	@Column
	private Double totalprice;
	
	public long getQuantity_id() {
		return quantity_id;
	}
	public void setQuantity_id(long quantity_id) {
		this.quantity_id = quantity_id;
	}
	public int getQuantityOfBook() {
		return quantityOfBook;
	}
	public void setQuantityOfBook(int quantityOfBook) {
		this.quantityOfBook = quantityOfBook;
	}
	public Double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}
	


}
