package com.bridgelabz.bookstore.service;

import java.util.List;
import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.dto.EditBookDto;
import com.bridgelabz.bookstore.entity.Book;

public interface IBookService {
	
	boolean addBooks(BookDto information);

	List<Book> getBookInfo();
	
	void removefromcart(Long userId, Long bookId);

	List<Book> sortGetAllBooks();
	
	boolean addandupdatecart(Long userId, int quantity, Long bookId);

	List<Book> sorting(boolean value);

    List<Book> findAllPageBySize( int pagenumber);
	
	Book getBookbyId( long bookId);
	
	Book getTotalPriceofBook( long bookId, int quantity);

	boolean editBook(EditBookDto information);

	boolean deleteBook(long bookId);

	List<Book> getAllAprovedBooks();

	boolean editStatus(long bookId, String status);

	List<Book> getAllApprovedAndOnHoldBooks();

	List<Book> getAllRejectedBooks();

	
	
}