package com.bridgelabz.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.entity.Order;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.service.IOrderServices;
@RestController
public class OrderController {
	@Autowired
	private IOrderServices orderService;
	@PostMapping("bookstore/confirmOrder/{token}")
	public ResponseEntity<Response> confrimOrder(@PathVariable String token) throws Exception {
		Order orderdetails = orderService.confrimOrder(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("order is placed", 200, orderdetails));
	}

}
