package com.bridgelabz.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
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
	
	@GetMapping(value = "bookstore/confirmBook/{bookId}")
	public ResponseEntity<Response> confirmBooktoOrder(@RequestHeader(name= "token") String token,@PathVariable("bookId") long bookId) throws Exception {	
		boolean userdetails = orderService.confirmBooktoOrder(token, bookId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("order is placed", 200, userdetails));
	
		
	}
	
	
	
	@GetMapping(value = "/books/{token}")
	public ResponseEntity<Response> getOrderlist(@PathVariable("token") String token) throws Exception {
		
		List<Order> orderdetails = orderService.getOrderList(token);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("placed orderlist", 200, orderdetails));
		
	}
	@GetMapping(value = "/books_count/{token}")
	public ResponseEntity<Response> getBooksCount(@PathVariable("token") String token) throws Exception {
		
		int userdetails = orderService.getCountOfBooks(token);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("count of books", 200, userdetails));
		
	}


}
