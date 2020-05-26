package com.bridgelabz.bookstore.controllayer;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.bridgelabz.bookstore.controller.CustomerController;
import com.bridgelabz.bookstore.dto.CustomerDto;
import com.bridgelabz.bookstore.entity.Address;
import com.bridgelabz.bookstore.entity.BookInformation;
import com.bridgelabz.bookstore.service.Customerservice;
import com.fasterxml.jackson.databind.ObjectMapper;

class CustomerControllerTests {

	@InjectMocks
	CustomerController controller;

	@Mock
	Customerservice service;
	
	@Autowired
	MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test 
	final void testAddCustomer() throws Exception {
		CustomerDto dto = new CustomerDto();
		Address address = new Address();
		address.setAddress("India");
		address.setCity("Bangalore");
		address.setLandmark("Agrahara School");
		address.setLocality("College");
		address.setPincode(560067);
		dto.setHome(address);
		dto.setName("Naveen");
		dto.setPhonenumber( (long) 1230456987);
		ObjectMapper object = new ObjectMapper();
		String customerdto = object.writeValueAsString(dto);
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/customers/addcustomer", dto).content(customerdto).contentType(MediaType.APPLICATION_JSON);
		ResultActions resultAction = mockMvc.perform(request);
		MvcResult result = resultAction.andReturn();
		assertEquals(result.getResponse().getStatus(), HttpStatus.CREATED.value());
	}
	@Test
	final void testAddCart() throws Exception {
		List<BookInformation> book = new ArrayList<BookInformation>();
		ObjectMapper object = new ObjectMapper();
		String bookdto = object.writeValueAsString(book);
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/carts/addcart/1/1").content(bookdto).contentType(MediaType.APPLICATION_JSON);
		ResultActions resultAction = mockMvc.perform(request);
		MvcResult result = resultAction.andReturn(); 
        Assert.assertTrue(HttpStatus.OK.value() ==  result.getResponse().getStatus()); 
	}

//	@Test
//	final void testGetPrice() throws Exception {
//		BookInformation book = new BookInformation();
//		ObjectMapper object = new ObjectMapper();
//		String cart = object.writeValueAsString(book);
//		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/books/1/4").content(cart).contentType(MediaType.APPLICATION_JSON);
//		ResultActions resultAction = mockMvc.perform(request);
//		MvcResult result = resultAction.andReturn(); 
//        Assert.assertTrue(HttpStatus.OK.value() ==  result.getResponse().getStatus()); 
//	}
}
