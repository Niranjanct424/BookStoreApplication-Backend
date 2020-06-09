package com.bridgelabz.bookstore.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.AddressDto;
import com.bridgelabz.bookstore.dto.UpdateAddressDto;
import com.bridgelabz.bookstore.entity.Address;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.service.IAdressService;


@RestController
@CrossOrigin("*")
public class AddressController {
	@Autowired
	private IAdressService addressService;

	@PostMapping("/address/add/{token}")
	public ResponseEntity<Response> addAddress(@RequestBody AddressDto address, @PathVariable String token)
			throws Exception {

		Address addres = addressService.addAddress(address, token);

		if (addres != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("added adress", 200, addres));
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new Response("not added", 400,addres));

	}
	
	@PutMapping
	("/update/{token}")
	public ResponseEntity<Response> updateAddress(@PathVariable("token") String token,@RequestBody UpdateAddressDto address)
	{

		Optional<Address> addres=addressService.updateAddress(address,token);
		if (addres != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("added adress", 200, addres));
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("added adress", 200, addres));

	}

	@DeleteMapping("/delete")
	public ResponseEntity<Response> deleteAddress(@RequestParam Long addressId,@RequestHeader String token )
	{
		System.out.println("####");
		Users message= addressService.deleteAddress(token, addressId);
		System.out.println("==="+message);
		if (message != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("added adress", 200, message));
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("added adress", 200, message));		
	}
	
	@GetMapping("/getAllAddress")
	public List<Address> getAllAddress()
	{
		return addressService.getAllAddress();

	}

	@GetMapping(value = "/getAddress/{id}")
	public ResponseEntity<Response> getAddress(@PathVariable Long id) {
		Address add = addressService.getAddress(id);
		if (add != null) {
		}
		return null;	
	}
	@GetMapping(value = "/getAddress/{type}")
	public ResponseEntity<Response> getAddress(@PathVariable String type,@RequestHeader String token) {
		Address add = addressService.getAddress(type,token);
		if (add != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("added adress", 200, add));
		}
		return null;
		
	}
	@GetMapping(value = "/users/{token}")
	public List<Address> getAddressByUserId(@PathVariable String token) {
		List<Address> result = addressService.getAddressByUserId(token);
		System.out.println("-----------result"+result);
		if (result != null) {
			 return result;
		}
		return null;
	}

}
