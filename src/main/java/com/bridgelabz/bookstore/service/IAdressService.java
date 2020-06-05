package com.bridgelabz.bookstore.service;


import com.bridgelabz.bookstore.dto.AddressDto;
import com.bridgelabz.bookstore.entity.Address;

public interface IAdressService {

	
		Address addAddress(AddressDto address, String token);
		
		


	}
