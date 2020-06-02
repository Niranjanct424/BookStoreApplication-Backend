package com.bridgelabz.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.entity.Order;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.service.IOrderService;

@RestController
@CrossOrigin("*")
public class OrderController {

	@Autowired
	private IOrderService orderService;


	@PostMapping(value = "orders/confrim/{token}")
	public ResponseEntity<Response> OrderConfrim(@RequestHeader("token") String token) throws Exception {
		
		 Order orderdetails = orderService.getOrderConfrim(token);
			return ResponseEntity.status(200).body(new Response( "cofirm order" ,200, orderdetails));
		
	}
	
}
