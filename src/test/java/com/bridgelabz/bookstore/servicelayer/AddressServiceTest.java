package com.bridgelabz.bookstore.servicelayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.bridgelabz.bookstore.dto.AddressDto;
import com.bridgelabz.bookstore.entity.Address;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.implementation.AdressImpService;
import com.bridgelabz.bookstore.repository.AddressRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.util.JwtGenerator;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AddressServiceTest {

	@InjectMocks
	AdressImpService impl;

	@Mock
	UserRepository userRepo;
	
	@Mock
	JwtGenerator generate;
	
	@Mock
	AddressRepository addressRepository;
	
	@BeforeEach
	void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test()
	public void addAddressTest() {
		
		Address address = new Address();
		address.setCity("Bangalore");
		
		Users user = new Users();
		user.setEmail("nayan@gmail.com"); 
		
		AddressDto dto = new AddressDto();
		dto.setCity("Bangalore");
		
//		Mockito.when(userRepo.findById(Mockito.anyLong())).thenReturn( user);
		Mockito.when(addressRepository.save(Mockito.any())).thenReturn( user);
		Address res = impl.addAddress(dto, "token");
	}

}
