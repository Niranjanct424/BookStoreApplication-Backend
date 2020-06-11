package com.bridgelabz.bookstore.controllayer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import org.springframework.web.context.WebApplicationContext;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.service.IOrderServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class AdminController {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	AdminController adminController;
	
//	ObjectMapper om = new ObjectMapper();
	
	@Autowired
	IOrderServices ordreServices;
	
	@Autowired
	WebApplicationContext context;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
	}

	@Test
	void getAllUnverifiedBooks() throws Exception {
		BookDto dto = new BookDto();
		ObjectMapper object = new ObjectMapper();
		String bookdto = object.writeValueAsString(dto);
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("admin/unVerifedBooks").content(bookdto).contentType(MediaType.APPLICATION_JSON);
		ResultActions resultAction = mockMvc.perform(request);
		MvcResult result = resultAction.andReturn();
//		 Assert.assertTrue(HttpStatus.OK.value() == result.getResponse().getStatus());
		
		Assert.assertEquals(result.getResponse(), "All unverified books by admin");
	
	}

}
