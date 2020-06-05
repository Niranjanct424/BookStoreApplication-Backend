package com.bridgelabz.bookstore.dto;

import lombok.Data;

@Data
public class UpdateAddressDto {
	private String address;
	private long addressId;
	private String houseNo;
 
	private String street;

	private String city;

	private String landmark;

	private String state;

	private String pincode;

	private String country;

	private String type;
}
