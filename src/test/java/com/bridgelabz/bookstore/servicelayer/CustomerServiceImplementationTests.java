package com.bridgelabz.bookstore.servicelayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bridgelabz.bookstore.dto.CustomerDto;
import com.bridgelabz.bookstore.entity.Address;
import com.bridgelabz.bookstore.entity.BookInformation;
import com.bridgelabz.bookstore.entity.CartInformation;
import com.bridgelabz.bookstore.entity.CustomerInformation;
import com.bridgelabz.bookstore.implementation.CustomerserviceImplimentation;
import com.bridgelabz.bookstore.repository.BookImple;
import com.bridgelabz.bookstore.repository.CartImple;
import com.bridgelabz.bookstore.repository.CustomerRepository;

class CustomerServiceImplementationTests {

	@InjectMocks
	CustomerserviceImplimentation service;
	
	@Mock
	CustomerRepository customerrep;
	
	@Mock
    CartImple cartrepository;
	
	@Mock
    BookImple repository;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testAddCustomer() {
		CustomerDto dto = new CustomerDto();
		Address address = new Address();
		CustomerInformation info = new CustomerInformation();
		CustomerInformation cust = new CustomerInformation();
		address.setAddress("India");
		address.setCity("Bangalore");
		address.setLandmark("Agrahara School");
		address.setLocality("College");
		address.setPincode(560067);
		info.setHome(address); 
		info.setName("Naveen");
		info.setPhonenumber((long) 1234567890); 
		when(customerrep.save(info)).thenReturn(cust);
		dto.setHome(cust.getHome());
		dto.setName(cust.getName());
		dto.setPhonenumber(cust.getPhonenumber());
		CustomerInformation data = service.addCustomerDetails(dto);
 		assertTrue(data != null);
	}

	@Test
	final void addToCart() {
		CartInformation cartinfo = new CartInformation();
		CartInformation cart = new CartInformation();
		List<BookInformation> bookinfo = new ArrayList<BookInformation>();
		when( repository.fetchbyIdList( (long) 1)).thenReturn(bookinfo);
		CustomerInformation details = customerrep.getCustomerDetailsbyId( (long) 1);
		cartinfo.setBookId(bookinfo);
		cartinfo.setUserId( details); 
		when( cartrepository.save(cartinfo)).thenReturn(cart);
		List<BookInformation> info = service.getBookfromCart( (long) 1, (long) 1);
		assertEquals(info, bookinfo); 
	}
	
}
