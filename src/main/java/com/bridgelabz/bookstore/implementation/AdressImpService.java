package com.bridgelabz.bookstore.implementation;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.bookstore.dto.AddressDto;
import com.bridgelabz.bookstore.entity.Address;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.repository.AddressRepository;
import com.bridgelabz.bookstore.repository.BookImple;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.service.IAdminService;
import com.bridgelabz.bookstore.service.IAdressService;
import com.bridgelabz.bookstore.util.JwtGenerator;

public class AdressImpService  implements IAdressService{
	@Autowired
	private JwtGenerator generate;
     @Autowired
     private UserRepository userRepo;
     @Autowired
 	private BookImple bookRepository;
  @Autowired
  private AddressRepository addressRepository;
	@Transactional
	@Override
	public Address addAddress(AddressDto address,String token) {
		Long id = generate.parseJWT(token);
		Address add=new Address( address);
		System.out.println(add.getAddress()+"->"+add.getCity());
		Users userdetails = userRepo.findById(id)
				.orElseThrow(null);
		userdetails.getAddress().add(add);
		return   addressRepository.save(add);

	}


}
