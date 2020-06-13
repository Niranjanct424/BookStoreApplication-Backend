package com.bridgelabz.bookstore.controllayer;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.bridgelabz.bookstore.controller.UserController;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.request.LoginInformation;
import com.bridgelabz.bookstore.service.UserServices;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {


	private MockMvc mockMvc;

	@InjectMocks
	UserController controller;

	
	@MockBean
	private UserServices service;


	@BeforeEach
	void setUp(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
	}

	@Test
	public void userRegistrationtest() throws Exception{


		UserDto userDto = new UserDto();
		userDto.setEmail("nayan12@gmail.com");
		userDto.setMobileNumber((long) 904820061);
		userDto.setName("nayan");
		userDto.setPassword("nayan@12");
		userDto.setRole("admin");
		ObjectMapper object = new ObjectMapper();
		Mockito.when(service.register(Mockito.any(UserDto.class))).thenReturn(true);
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.post("/registration")
				.contentType(MediaType.APPLICATION_JSON)
				.content(object.writeValueAsString(userDto))).andReturn();
		
		System.out.println("register response:"+result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), HttpStatus.CREATED.value());

	}

	@Test
	public void userLogintest() throws Exception{
		Users user = new Users();
		user.setName("Nayan");
		user.setUserId(1);
		LoginInformation loginDto = new LoginInformation();
		loginDto.setEmail("nayangkumar@gmail.com");
		loginDto.setPassword("nayan@123");
		loginDto.setRole("admin");
		ObjectMapper object = new ObjectMapper();
		Mockito.when(service.login(Mockito.any())).thenReturn(user);
		
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(object.writeValueAsString(loginDto))).andReturn();
		System.out.println("register response:"+result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), HttpStatus.ACCEPTED.value());
	
	}
	
	@Test
	public void userVerificationtest() throws Exception{
	
		Mockito.when(service.verify(Mockito.anyString())).thenReturn(true);
		
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.get("/user/verify/token")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println("register response:"+result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), HttpStatus.ACCEPTED.value());
	
	}

}
