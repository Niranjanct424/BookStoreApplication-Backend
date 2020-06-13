package com.bridgelabz.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.entity.Order;
import com.bridgelabz.bookstore.response.UserResponse;
import com.bridgelabz.bookstore.service.OrderService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private Environment env;

	@ApiOperation(value = "Getting the confrim order of user")
	@PostMapping(value = "/confrim/{token}")
	public ResponseEntity<UserResponse> OrderConfrim(@PathVariable("token") String token) throws Exception {

		Order orderdetails = orderService.getOrderConfrim(token);
		return ResponseEntity.status(200).body(new UserResponse("confirm order details", orderdetails, HttpStatus.OK));

	}

	@ApiOperation(value = "Getting the OrderList")
	@GetMapping(value = "/books/{token}")
	public ResponseEntity<UserResponse> getOrderlist(@PathVariable("token") String token) throws Exception {

		List<Order> orderdetails = orderService.getOrderList(token);
		return ResponseEntity.status(200).body(new UserResponse("ordersList ", orderdetails, HttpStatus.OK));

	}

	@ApiOperation(value = "Getting the booksCount")
	@GetMapping(value = "/books_count/{token}")
	public ResponseEntity<UserResponse> getBooksCount(@PathVariable("token") String token) throws Exception {

		int userdetails = orderService.getCountOfBooks(token);
		return ResponseEntity.status(200).body(new UserResponse("count of books", userdetails, HttpStatus.OK));

	}
}