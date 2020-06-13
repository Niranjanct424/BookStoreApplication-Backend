package com.bridgelabz.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.response.BookResponse;
import com.bridgelabz.bookstore.service.IAdminService;

@RestController
@CrossOrigin("*")
public class AdminController {

	@Autowired
	private IAdminService adminService;

	@PutMapping("admin/approveBook/{bookId}")
	public ResponseEntity<BookResponse> approveBook(@PathVariable long bookId, @RequestHeader String token) {
		if (adminService.verifyBook(bookId, token)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new BookResponse("This Seller book is approved by Admin", HttpStatus.ACCEPTED));
		}
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new BookResponse(406,"book not approved"));

	}

	@PutMapping("admin/rejectBook/{bookId}")
	public ResponseEntity<BookResponse> rejectBook(@PathVariable long bookId, @RequestHeader String token) {
		if (adminService.rejectBook(bookId, token)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new BookResponse("This Seller book is rejected by the admin", HttpStatus.NOT_ACCEPTABLE));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BookResponse(400,"Bad request"));

	}

//	@PutMapping("admin/orderStatus/{orderId}")
//	public ResponseEntity<BookResponse> orderstatus(@PathVariable long orderId, @RequestHeader String token) {
//		if (adminService.orderStatus(orderId, token)) {
//			return ResponseEntity.status(HttpStatus.OK).body(new BookResponse("Your book is Delivered", HttpStatus.OK));
//		}
//		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
//				.body(new BookResponse(406,"your order is pending still didn't approved"));
//	}

	@GetMapping("admin/unVerifedBooks")
	public ResponseEntity<BookResponse> getAllUnverifiedBooks(@RequestHeader String token) {
		List<Book> unverifiedBooks = adminService.getUnVerifiedBooks(token);
		{
			System.out.println("unverified books:"+unverifiedBooks);
			System.out.println("admin token:"+token);
			return ResponseEntity.status(HttpStatus.OK).body(new BookResponse("All unverified books by admin", unverifiedBooks));
		}

	}
	
	@GetMapping("admin/allRejectedBooks")
	public ResponseEntity<BookResponse> getAllRejectedBooks(@RequestHeader String token) {
		List<Book> rejectedBooks = adminService.rejectedBooks(token);
		{
			return ResponseEntity.status(HttpStatus.OK).body(new BookResponse("All rejected books by admin", rejectedBooks));
		}

	}

	
	@GetMapping("admin/allApprovedBooks")
	public ResponseEntity<BookResponse> getAllApprovedBooks(@RequestHeader String token) {
		List<Book> approvedBooks = adminService.getAllApprovedBooks(token);
		{
			return ResponseEntity.status(HttpStatus.OK).body(new BookResponse("All Approved books by admin", approvedBooks));
		}

	}
	
//	@GetMapping("admin/getAllBooks")
//	public ResponseEntity<BookResponse> getAllBooks(@RequestHeader String token) {
//		List<Book> unverifiedBooks = adminService.getAllBooks();
//		{
//			return ResponseEntity.status(HttpStatus.FOUND).body(new BookResponse("All books", unverifiedBooks));
//		}
//
//	}

	
}
