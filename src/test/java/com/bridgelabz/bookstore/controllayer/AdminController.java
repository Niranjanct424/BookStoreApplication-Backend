package com.bridgelabz.bookstore.controllayer;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bridgelabz.bookstore.controller.UserController;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.implementation.UserServiceImplementation;
import com.bridgelabz.bookstore.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
class AdminController {
	
	@InjectMocks
	private UserController userController;
	@Mock
	private UserServiceImplementation userService;
	private MockMvc mockMvc;
	private ObjectMapper objectMapper;
	
	private static final String REGISTER_USER_URI = "/registration";
	
	
	@Test
	public void register() throws Exception {
		Users user = new Users();
		user.setEmail("validEmail");
		ResponseEntity<Response> responseEntity = ResponseEntity.status(HttpStatus.CREATED)
				.body(new Response("registration successfull", 200));
		ObjectMapper ob = new ObjectMapper();
		String newUserDto = objectMapper.writeValueAsString(user);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(REGISTER_USER_URI)
				.content(newUserDto).contentType(MediaType.APPLICATION_JSON);
		Mockito.when(userService.register(Mockito.any())).thenReturn(true);

		MockHttpServletResponse fetchedResponse = mockMvc.perform(requestBuilder).andReturn().getResponse();

		System.out.println("fetch result : " + fetchedResponse.getStatus());
		Assert.assertEquals("register user with valid user data", fetchedResponse.getStatus(),
				202);
	}
}
