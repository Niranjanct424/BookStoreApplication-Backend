package com.bridgelabz.bookstore.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.AddressDto;
import com.bridgelabz.bookstore.entity.Address;
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

}
