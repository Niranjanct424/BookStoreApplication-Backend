package com.bridgelabz.bookstore.servicelayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.entity.BookInformation;
import com.bridgelabz.bookstore.implementation.BookServiceImplementation;
import com.bridgelabz.bookstore.implementation.CustomerserviceImplimentation;
import com.bridgelabz.bookstore.repository.BookImple;

class BookServiceImplementationTests {

	@InjectMocks
	BookServiceImplementation service;

	@InjectMocks
	CustomerserviceImplimentation cusservice;
	
	@Mock
	BookDto dto;

	@Mock
	private BookImple repository;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	final void testGetBookInfo() {
		List<BookInformation> users = new ArrayList<BookInformation>();
		when(repository.getAllBooks()).thenReturn(users);
		List<BookInformation> empList = service.getBookInfo();
		assertEquals(empList.size(), users.size());
	}

	@Test
	final void testGetBookbyId() {
		BookInformation info = new BookInformation();
		when(repository.fetchbyId((long) 1)).thenReturn(info);
		BookInformation bookinfo = service.getBookbyId((long) 1);
		assertEquals(info, bookinfo);
	}

	@Test
	final void testPagewise() {
		List<BookInformation> book = new ArrayList<BookInformation>();
		when(repository.findAllPage(PageRequest.of(0, 2))).thenReturn(book);
		List<BookInformation> list = service.findAllPageBySize(0);
		assertEquals(book.size(), list.size());
	}

	@Test
	final void testUnSort() {
		List<BookInformation> book = new ArrayList<BookInformation>();
		when(repository.findAll()).thenReturn(book);
		List<BookInformation> sort = service.sortGetAllBooks();
		assertEquals(sort.size(), book.size());
	}

	@Test
	final void sorting() {
		List<BookInformation> book = new ArrayList<BookInformation>();
		when(repository.findAll()).thenReturn(book);
		List<BookInformation> sort = service.sorting(false);
		assertEquals(sort.size(), book.size());
	}

	@Test
	final void addBooks() {
		BookDto dto = new BookDto();
		BookInformation book = new BookInformation();
		BookInformation info = new BookInformation();
		info.setAuthorName("Naveen");
		info.setBookDetails("Naveen");
		info.setBookName("Naveen");
		info.setImage("Animal");
		info.setPrice(1000.00);
		info.setQuantity(2);
		when(repository.save(info)).thenReturn(book);
		dto.setAuthorName(book.getAuthorName());
		dto.setBookDetails(book.getBookDetails());
		dto.setBookName(book.getBookName());
		dto.setImage(book.getImage());
		dto.setPrice(book.getPrice());
		dto.setQuantity(book.getQuantity());
		boolean check = service.addBooks(dto);
		assertTrue(check == true);
	}
	
}
