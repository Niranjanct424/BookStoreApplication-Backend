package com.bridgelabz.bookstore.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.AddressDto;
import com.bridgelabz.bookstore.dto.UpdateAddressDto;
import com.bridgelabz.bookstore.entity.Address;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.repository.AddressRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.service.IAdressService;
import com.bridgelabz.bookstore.util.JwtGenerator;
@Service
public class AdressImpService  implements IAdressService{
	@Autowired
	private JwtGenerator generate;
     @Autowired
     private UserRepository userRepo;
   
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
	
	@Override
	public Optional<Address> updateAddress(UpdateAddressDto addressupdate, String token) {
		List<Address> list=new ArrayList<>();

		Long id = generate.parseJWT(token);
		Users userdetails = userRepo.findById(id)
				.orElseThrow(null);
		Optional<Address> ad= addressRepository.findById(addressupdate.getAddressId());
		return ad.filter(note -> {
			return note != null;
		}).map(add->{
			add.setAddressId((addressupdate.getAddressId()));
			add.setAddress(addressupdate.getAddress());
			add.setAddressType(addressupdate.getType());
			add.setCity(addressupdate.getCity());
			add.setCountry(addressupdate.getCountry());
			add.setLandmark(addressupdate.getLandmark());
			add.setPincode(addressupdate.getPincode());
			add.setState(addressupdate.getState());
			
			addressRepository.save(add);
			userdetails.getAddress().add(add);
			return ad;
		}).orElseThrow(null);
	}
	@Transactional
	@Override
	public List<Address> getAllAddress() {
		List<Address> addList=new ArrayList<>();
		addressRepository.findAll().forEach(addList::add);
		return addList;
	}
	@Override
	public Address getAddress(Long id) {
		Address add=addressRepository.findAddressById(id);
		return add;
	}
	@Override
	public List<Address> getAddressByUserId(String token) {

		Long id = generate.parseJWT(token);
		Users userdetails = userRepo.findById(id)
				.orElseThrow(null);
		try {
			List<Address> user = addressRepository.findAddressByUserId(id);
			if (user != null) {
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Address getAddress(String type,String token) {
		Long id = generate.parseJWT(token);
		Users userdetails = userRepo.findById(id)
				.orElseThrow(null);
		Address add=addressRepository.findAddressBytext(id,type);
		return add;
	}

	@Transactional
	@Override
	public Users deleteAddress(String token, Long addressId) {
		Long id = generate.parseJWT(token);
		Users userdetails = userRepo.findById(id)
				.orElseThrow(null);
		List<Address> deleteaddress = getAllAddress();
		Address filteredaddress = deleteaddress.stream().filter(address ->address.getAddress().equals(addressId) ).findFirst()
				.orElseThrow(null);
		deleteaddress.remove(filteredaddress);
		addressRepository.delete(filteredaddress);
		return userRepo.save(userdetails);



	}


}
