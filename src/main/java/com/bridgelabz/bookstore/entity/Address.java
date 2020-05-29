package com.bridgelabz.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Address")
@Data
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private long addressId;

	@Column(name = "customer_pincode")
	private long Pincode;

	@Column(name = "customer_locality")
	private String Locality;

	@Column(name = "customer_address")
	private String Address;

	@Column(name = "customer_city")
	private String City;

	@Column(name = "customer_landmark")
	private String Landmark;


}
