package com.bridgelabz.bookstore.service;

import java.util.List;
import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.entity.BookInformation;

public interface IBookService {
	
	boolean addBooks(BookDto information);

	List<BookInformation> getBookInfo();
	
	void removefromcart(Long userId, Long bookId);

	List<BookInformation> sortGetAllBooks();
	
	boolean addandupdatecart(Long userId, int quantity, Long bookId);

	List<BookInformation> sorting(boolean value);

    List<BookInformation> findAllPageBySize( int pagenumber);
	
	BookInformation getBookbyId( long bookId);
	
	BookInformation getTotalPriceofBook( long bookId, int quantity);
	
}