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

	@PostMapping("books/addbook")
	public ResponseEntity<BookResponse> addBook(@RequestBody BookDto information,@RequestHeader("token") String token) {
		
		boolean res=bookservice.addBooks(information,token);
		if(res)
			return ResponseEntity.status(HttpStatus.CREATED).body(new BookResponse("The Book details are", information));
		else
			return ResponseEntity.status(HttpStatus.CREATED).body(new BookResponse(400,"The Book details not added "));
	}
	
	
	@GetMapping("books/getAllBooks")
	public ResponseEntity<BookResponse> getBooks(@RequestHeader("token") String token) {
		List<Book> books = bookservice.getBookInfo(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new BookResponse("The Book details are", books ));
 
	}
	@GetMapping("books/unsorting")
	public ResponseEntity<BookResponse> sort(){
		List<Book> list=bookservice.sortGetAllBooks();
		return ResponseEntity.status(HttpStatus.OK).body(new BookResponse("all books",list));
	}

	
	@GetMapping("books/sorting")
	public ResponseEntity<BookResponse> sorting(@RequestParam("value") boolean value){
		List<Book> list=bookservice.sorting( value);
		if (value==true) {
			return ResponseEntity.status(HttpStatus.OK).body(new BookResponse("all books",list));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(new BookResponse("all books",list));
		}

	}
	@GetMapping( value = "books/pagewise/{pagenumber}")
	public ResponseEntity<BookResponse> getBookPagewise( @PathVariable( value = "pagenumber") int pagenumber) {
		List<Book> pageList = bookservice.findAllPageBySize( pagenumber);
	
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new BookResponse("Successfull", pageList));
	}
	
	@GetMapping( value = "books/{bookId}")
 public ResponseEntity<BookResponse> getBookbyId( @PathVariable("bookId") long bookId) {
		Book info = bookservice.getBookbyId(bookId);	
			return ResponseEntity.status(HttpStatus.OK).body(new BookResponse("The book is",info));
	}
	
	
	@PutMapping("books/editbook")
	public ResponseEntity<BookResponse> editBook(@RequestBody EditBookDto information,@RequestHeader("token") String token){
		boolean res =bookservice.editBook(information,token);
		if(res)
			return ResponseEntity.status(HttpStatus.CREATED).body(new BookResponse("The Book is Edited", information));
		return null;
	}
	
	@DeleteMapping("books/deletebook/{bookId}")
	public ResponseEntity<BookResponse> deleteBook(@PathVariable long bookId,@RequestHeader("token") String token){
		boolean res =bookservice.deleteBook(bookId,token);
		if(res)
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new BookResponse(202,"The Book is Deleted"));
		return null;
	}
	
	
	@PutMapping("books/editBookStatus/{bookId}/{status}")
	public ResponseEntity<BookResponse> editBookStatus(@PathVariable long bookId,@PathVariable String status,@RequestHeader("token") String token){
		boolean res =bookservice.editBookStatus(bookId,status,token);
		if(res)
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new BookResponse(202,"The Book Status is changed sucessfully.."));
		else
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new BookResponse(400,"The Book Status is not updated.."));
	}
	
	@GetMapping("books/approvedBooks")
	public ResponseEntity<BookResponse> getAllApprovedBooks(@RequestHeader("token") String token) {
	List<Book> books = bookservice.getAllAprovedBooks(token);
		if(books != null)
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new BookResponse("The Approved Book details are", books ));
		else
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new BookResponse(400,"No Approved Books available" ));
	}
	
	@GetMapping("books/onHoldBooks")
	public ResponseEntity<BookResponse> getAllOnHoldBooks(@RequestHeader("token") String token) {
		List<Book> books = bookservice.getAllOnHoldBooks(token);
		if(books != null)
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new BookResponse("The Approved & OnHold Book details are", books ));
		else
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new BookResponse(400,"No Approved & OnHold Books available" ));
	}
	
	@GetMapping("books/rejectedBooks")
	public ResponseEntity<BookResponse> getAllRejectedBooks(@RequestHeader("token") String token) {
		List<Book> books = bookservice.getAllRejectedBooks(token);
		if(books != null)
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new BookResponse("The Rejected Book details are", books ));
		else
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new BookResponse(400,"No Rejected Books available" ));

	}
}
