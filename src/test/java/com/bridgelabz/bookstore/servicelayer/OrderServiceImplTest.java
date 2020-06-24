package com.bridgelabz.bookstore.servicelayer;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Order;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.implementation.OrderServiceImp;
import com.bridgelabz.bookstore.repository.CustomerRepository;
import com.bridgelabz.bookstore.repository.OrderRepository;
import com.bridgelabz.bookstore.service.IOrderServices;
import com.bridgelabz.bookstore.util.JwtGenerator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;



@RunWith(MockitoJUnitRunner.class)
class OrderServiceImplTest {

	@InjectMocks
	private OrderServiceImp orderService;
	
	
	private MockMvc mockMvc;
	
	@Mock
	CustomerRepository userRepo;
	
	@Mock
	JwtGenerator jwt;
	
	@Mock
	OrderRepository orderRepository;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(orderService).build();
	}
	
	
	
}
