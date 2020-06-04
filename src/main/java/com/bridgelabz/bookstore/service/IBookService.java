package com.bridgelabz.bookstore.service;

import java.util.List;
import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.dto.EditBookDto;
import com.bridgelabz.bookstore.entity.Book;

public interface IBookService {

	
	boolean addBooks(BookDto information, String token);

	List<Book> getBookInfo(String token);
	

	List<Book> sortGetAllBooks();
	

	List<Book> sorting(boolean value);



	
	public void deleteBook(int bookId);
    List<Book> findAllPageBySize( int pagenumber);
	
	Book getBookbyId( long bookId);
	
	Book getTotalPriceofBook( long bookId, int quantity);

	boolean editBook(EditBookDto information, String token);

	boolean deleteBook(long bookId, String token);

	List<Book> getAllAprovedBooks(String token);

	boolean editBookStatus(long bookId, String status,String token);

	List<Book> getAllOnHoldBooks(String token);

	List<Book> getAllRejectedBooks(String token);
}
	
	