package com.bridgelabz.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.entity.CartItem;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.service.ICartService;

@RestController
@CrossOrigin
public class CartController {

	@Autowired
	private ICartService cartService;
	@PostMapping(value="/bookdetails/{bookId}")
	public ResponseEntity<Response> addBooksToCart(@RequestHeader String token,@PathVariable long bookId) throws Exception {
	    List<CartItem> cartItem = cartService.addBooktoCart(token,bookId);
	    return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new Response("book added to cart", 200,cartItem));
	  	}
	

	@GetMapping(value="/detials")
	public ResponseEntity<Response> getBooksfromCart(@RequestHeader(name="token")  String token) throws Exception {
		    List<CartItem> cartdetails = cartService.getBooksfromCart(token);
		    return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new Response("books fetched from cart", 200,cartdetails));
	}

	
	@DeleteMapping(value="/book/{token}/{bookId}")
	public ResponseEntity<Response> removeBooksToCart(@PathVariable String token,@PathVariable Long bookId) throws Exception {
		boolean cartdetails = cartService.removeBooksFromCart(token,bookId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("book removed from cart", 200,cartdetails));  				
	}
	
	@GetMapping(value="/bookCount")
	public ResponseEntity<Response> getBooksCount(@RequestHeader(name="token") String token) throws Exception {
		    int cartdetails = cartService.getCountOfBooks(token);
		    return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Count of book in cart", 200,cartdetails));
	}

}
