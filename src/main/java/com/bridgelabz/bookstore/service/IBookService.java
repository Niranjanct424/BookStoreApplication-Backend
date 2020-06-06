package com.bridgelabz.bookstore.service;

import java.util.List;
import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.dto.EditBookDto;
import com.bridgelabz.bookstore.entity.Book;

public interface IBookService {
	
	boolean addBooks(String imageName,BookDto information, String token);

	List<Book> getBookInfo(String token);
	

	List<Book> sortGetAllBooks();
	

	List<Book> sorting(boolean value);

    List<Book> findAllPageBySize( int pagenumber);
	
	Book getBookbyId( long bookId);
	
	Book getTotalPriceofBook( long bookId, long quantity);

	boolean editBook(long bookId,String imageName,EditBookDto information, String token);

	boolean deleteBook(long bookId, String token);

	List<Book> getAllAprovedBook();

	boolean editBookStatus(long bookId, String status,String token);

	List<Book> getAllOnHoldBooks(String token);

	List<Book> getAllRejectedBooks(String token);

	boolean uploadBookImage(long bookId, String imageName, String token);

	
	
}