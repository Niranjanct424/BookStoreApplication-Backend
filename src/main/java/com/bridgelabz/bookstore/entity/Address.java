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
@Table(name = "Address")
<<<<<<< HEAD
@Data
public class Address {

public class Address implements Serializable {

	private static final long serialVersionUID = 1L;


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
	
	@Column(name = "country")
    private String country;
	
	@Column(name = "address_type")
    private String addressType;
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

}
