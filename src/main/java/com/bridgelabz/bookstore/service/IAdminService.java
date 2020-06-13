package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.entity.Book;

public interface IAdminService {
	

	boolean verifyBook(long bookId, String staus, String token);

<<<<<<< HEAD
	List<Book> rejectedBooks(String token);

//	boolean orderStatus(long orderId, String token);

	boolean rejectBook(long bookId, String token);

//	List<Book> getUnVerifiedBooks();

	List<Book> getAllApprovedBooks(String token);

	List<Book> getUnVerifiedBooks(String token);
=======
	List<Book> getBooksByStatus(String status);
>>>>>>> 244f51500a674a8c535845c8ccc2d121f97168dd

}
