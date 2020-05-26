package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.dto.CustomerDto;
import com.bridgelabz.bookstore.entity.BookInformation;
import com.bridgelabz.bookstore.entity.CustomerInformation;

public interface Customerservice {

    List<BookInformation> getBookfromCart( long bookId, long userId);
	
	boolean deletefromCart( long bookId, long cartId);
	
//	BookInformation getTotalPriceofBook( long bookId, int quantity);
	
	CustomerInformation addCustomerDetails( CustomerDto dto);
	
}
