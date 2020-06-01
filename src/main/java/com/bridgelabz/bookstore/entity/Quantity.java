package com.bridgelabz.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Table
@Data
public class Quantity 
{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long quantity_id;
    @Column
    private long quantityOfBook;
	@Column
	private Double totalprice;
	
}
