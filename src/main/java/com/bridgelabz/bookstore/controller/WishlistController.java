package com.bridgelabz.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.entity.WishlistBook;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.service.IWishlistService;
@RestController
public class WishlistController {
	@Autowired
	private IWishlistService wishbookService;
	
	
	@PostMapping("bookstore/v3/wishlist/addbookWishlist/{bookId}")
	public ResponseEntity<Response> addBooksToCart
	(@RequestHeader String token,@PathVariable long bookId)throws Exception {
	    List<WishlistBook> wishbook = wishbookService.addwishBook(token, bookId);
	    return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new Response("book is added to wishlist Bag", 200,wishbook));
	  	}

}
