package com.bridgelabz.bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.dto.EditBookDto;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.response.BookResponse;
import com.bridgelabz.bookstore.service.IBookService;

@RestController
@CrossOrigin
public class BookStoreController {

	@Autowired
	IBookService bookservice;

	@PostMapping("books/addbook/{imageName}")
	public ResponseEntity<BookResponse> addBook(@PathVariable String imageName, @RequestBody BookDto information,@RequestHeader("token") String token) {
		
		boolean res=bookservice.addBooks(imageName,information,token);
		if(res)
			return ResponseEntity.status(HttpStatus.CREATED).body(new BookResponse(200, "Book Added Successfully.."));
		else
			return ResponseEntity.status(HttpStatus.CREATED).body(new BookResponse(400, "The Book details not added "));
	}

	@GetMapping("books/getAllBooks")
	public ResponseEntity<BookResponse> getBooks(@RequestHeader("token") String token) {
		List<Book> books = bookservice.getBookInfo(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new BookResponse("The Book details are", books));

	}

	/**
	 * This controller is for getting 12 approval books per page! it can search book
	 * based on there autherName it can sort the book by anything like price,
	 * book_name, book_id etc, it can order the book both asc and desc order default
	 * will be desc order it can return the book based on there passing url
	 * paramater
	 * 
	 * @param searchByBooKName example (" ", book name, raju, etc)
	 * @param page             Example (" ", 1,2,3,4.........)
	 * @param sortBy           example (" ", book_id, price, created_date_and_time
	 *                         etc)
	 * @param order            (" ", asc,desc,)
	 * @return 12 books and number of page and everything
	 */

	@GetMapping("books/approved")
	public ResponseEntity<BookResponse> getAllApproved(@RequestParam Optional<String> searchByBooKName,
			@RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy,
			@RequestParam Optional<String> order) {
		Page<Book> books = bookservice.getBookAproval(searchByBooKName, page, sortBy, order);
		if (books != null)
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new BookResponse("The Approved Book details are", books));
		else
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new BookResponse(400, "No Approved Books available"));
	}

	@GetMapping(value = "books/{bookId}")
	public ResponseEntity<BookResponse> getBookbyId(@PathVariable("bookId") long bookId) {
		Book info = bookservice.getBookbyId(bookId);
		return ResponseEntity.status(HttpStatus.OK).body(new BookResponse("The book is", info));
	}
	
	
	@PutMapping("books/editbook/{bookId}/{imageName}")
	public ResponseEntity<BookResponse> editBook(@PathVariable("bookId") long bookId,@PathVariable String imageName,@RequestBody EditBookDto information,@RequestHeader("token") String token){
		boolean res =bookservice.editBook(bookId,imageName,information,token);
		if(res)
			return ResponseEntity.status(HttpStatus.CREATED).body(new BookResponse(200, "The Book is Updated Successfully"));
		return null;
	}

	@DeleteMapping("books/deletebook/{bookId}")
	public ResponseEntity<BookResponse> deleteBook(@PathVariable long bookId, @RequestHeader("token") String token) {
		boolean res = bookservice.deleteBook(bookId, token);
		if (res)
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new BookResponse(202, "The Book is Deleted"));
		return null;
	}

	@PutMapping("books/editBookStatus/{bookId}/{status}")
	public ResponseEntity<BookResponse> editBookStatus(@PathVariable long bookId, @PathVariable String status,
			@RequestHeader("token") String token) {
		boolean res = bookservice.editBookStatus(bookId, status, token);
		if (res)
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new BookResponse(202, "The Book Status is changed sucessfully.."));
		else
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new BookResponse(400, "The Book Status is not updated.."));
	}

	@GetMapping("books/approvedBooks")
	public ResponseEntity<BookResponse> getAllApprovedBook() {
		List<Book> books = bookservice.getAllAprovedBook();
		if (books != null)
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new BookResponse("The Approved Book details are", books));
		else
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new BookResponse(400, "No Approved Books available"));
	}

	@GetMapping("books/onHoldBooks")
	public ResponseEntity<BookResponse> getAllOnHoldBooks(@RequestHeader("token") String token) {
		List<Book> books = bookservice.getAllOnHoldBooks(token);
		if (books != null)
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new BookResponse("The Approved & OnHold Book details are", books));
		else
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new BookResponse(400, "No Approved & OnHold Books available"));
	}

	@GetMapping("books/rejectedBooks")
	public ResponseEntity<BookResponse> getAllRejectedBooks(@RequestHeader("token") String token) {
		List<Book> books = bookservice.getAllRejectedBooks(token);
		if (books != null)
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new BookResponse("The Rejected Book details are", books));
		else
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new BookResponse(400, "No Rejected Books available"));
	}
}
