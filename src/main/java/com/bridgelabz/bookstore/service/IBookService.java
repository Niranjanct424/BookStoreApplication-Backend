package com.bridgelabz.bookstore.service;

import java.util.List;
import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.dto.EditBookDto;
import com.bridgelabz.bookstore.entity.BookInformation;

public interface IBookService {

	boolean addBooks(BookDto information);

	List<BookInformation> getBookInfo();

	void removefromcart(Long userId, Long bookId);

	List<BookInformation> sortGetAllBooks();

	boolean addandupdatecart(Long userId, int quantity, Long bookId);

	List<BookInformation> sorting(boolean value);

	List<BookInformation> findAllPageBySize(int pagenumber);

	BookInformation getBookbyId(long bookId);

	//BookInformation getTotalPriceofBook(long bookId, int quantity);
	
	public void deleteBook(int bookId);
	BookInformation getTotalPriceofBook( long bookId, int quantity);

	boolean editBook(EditBookDto information);

	boolean deleteBook(long bookId);

	List<BookInformation> getAllAprovedBooks();

	
	
	/*
	 * 
	 * public void addBook(BookDto information);
	 * 
	 * public void updateBook(BookDto information, Long bookId);
	 * 
	 * public void deleteBook(Long bookId);
	 * 
	 * List<BookInformation> getBooks(Integer pageNo);
	 * 
	 * List<BookInformation> getBooksSortedByPriceLow();
	 * 
	 * List<BookInformation> getBooksSortedByPriceHigh();
	 * 
	 * List<BookInformation> getBooksSortedByArrival();
	 * 
	 * List<BookInformation> getBookByNameAndAuthor(String text);
	 * 
	 * List<BookInformation> getAllBooks();
	 * 
	 * List<BookInformation> VerifyBook(Long bookId);
	 */
}