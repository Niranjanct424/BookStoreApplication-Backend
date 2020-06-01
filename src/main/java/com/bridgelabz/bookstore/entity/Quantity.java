package com.bridgelabz.bookstore.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Table
<<<<<<< HEAD
@Data
public class Quantity 
=======
public class Quantity implements Serializable
>>>>>>> faab57573c2840cd2d51f5809b00a6e6960ed1a0
{
   
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long quantity_id;
    @Column
	private Long quantityOfBook;
	@Column
	private Double totalprice;

}
