package com.bridgelabz.bookstore.controllayer;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.bridgelabz.bookstore.controller.BookStoreController;
import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.service.IBookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
class BookStoreControllerTests {

	@InjectMocks
	BookStoreController controller;

	@Mock
	IBookService service;
	
	
	MockMvc mockMvc;
	
	private static final String ADD_BOOK_URI = "/books/{imageName}";
	private static final String DELETE_BOOK_URI="/books/{bookId}";
	private static final String GET_ALL_BOOKS_URI="/books/";
	private static final String EDIT_BOOKS_URI="/books/{bookId}";
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	final void testGetBookById() throws Exception {
		Book book = new Book();
		ObjectMapper object = new ObjectMapper();
		String bookdto = object.writeValueAsString(book);
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/books/"+3L).content(bookdto).contentType(MediaType.APPLICATION_JSON);
		ResultActions resultAction = mockMvc.perform(request);
		MvcResult result = resultAction.andReturn();
        Assert.assertTrue(HttpStatus.OK.value() ==  result.getResponse().getStatus());
	}
	
	@Test
	final void testGetBookPagewise() throws Exception { 
		Book book = new Book();
		ObjectMapper object = new ObjectMapper();
		String bookdto = object.writeValueAsString(book);
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/books/pagewise/"+1L).content(bookdto).contentType(MediaType.APPLICATION_JSON);
		ResultActions resultAction = mockMvc.perform(request);
		MvcResult result = resultAction.andReturn();
        Assert.assertTrue(HttpStatus.ACCEPTED.value() ==  result.getResponse().getStatus());	
	}
	
	@Test 
	final void testUnSorting() throws Exception {
		List<Book> book = new ArrayList<Book>();
		ObjectMapper object = new ObjectMapper();
		String bookdto = object.writeValueAsString(book);
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/books/unsorting").content(bookdto).contentType(MediaType.APPLICATION_JSON);
		ResultActions resultAction = mockMvc.perform(request);
		MvcResult result = resultAction.andReturn();
		Assert.assertTrue(HttpStatus.OK.value() ==  result.getResponse().getStatus());
	}
	
	@Test
	final void add_Book_Test() throws Exception {
		BookDto info = new BookDto();
		info.setAuthorName("Amish");
		info.setBookDetails("Ram Chandra Series");
		info.setBookName("Ram");
		info.setImage("ram.jpg");
		info.setPrice(1000.00);
		info.setNoOfBooks((long) 2);
		
		
		ObjectMapper object = new ObjectMapper();
		String bookdto = object.writeValueAsString(info);
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(ADD_BOOK_URI,"ramjpg")
				.content(bookdto).contentType(MediaType.APPLICATION_JSON)
				.header("token", "validToken");
		
		Mockito.when(service.addBooks(Mockito.anyString(),Mockito.any(), Mockito.anyString())).thenReturn(true);
		
		
		ResultActions resultAction = mockMvc.perform(request);
		MvcResult result = resultAction.andReturn();
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.CREATED.value());
	}
	
	@Test
	final void delete_Book_Test() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(DELETE_BOOK_URI,1L)
				.contentType(MediaType.APPLICATION_JSON)
				.header("token", "validToken");
		
		Mockito.when(service.deleteBook(Mockito.anyLong(), Mockito.anyString())).thenReturn(true);
		
		ResultActions resultAction = mockMvc.perform(request);
		MvcResult result = resultAction.andReturn();
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.ACCEPTED.value());
	}
	
	@Test
	final void get_Books_Test() throws Exception {
		Book book=new Book();
		
		book.setBookName("book1");
		book.setAuthorName("amit");
		book.setBookDetails("Some book");
		book.setImage("sita.jpg");
		book.setNoOfBooks(20L);
		book.setPrice(200.00);
		book.setStatus("OnHold");
		book.setCreatedDateAndTime(null);
		book.setUpdatedDateAndTime(null);
		
		
		Book book1=new Book();
		
		book.setBookName("book2");
		book.setAuthorName("sadhguru");
		book.setBookDetails("Some book1");
		book.setImage("death.jpg");
		book.setNoOfBooks(23L);
		book.setPrice(204.00);
		book.setStatus("OnHold");
		book.setCreatedDateAndTime(null);
		book.setUpdatedDateAndTime(null);
		
		
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(book);
		bookList.add(book1);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(GET_ALL_BOOKS_URI)
				.header("token", "validToken")
				.contentType(MediaType.APPLICATION_JSON);
		
		Mockito.when(service.getBookInfo( Mockito.anyString())).thenReturn(bookList);
		
		ResultActions resultAction = mockMvc.perform(requestBuilder);
		MvcResult result = resultAction.andReturn();
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.ACCEPTED.value());
				
	}
	
	@Test
	final void edit_Book_Test() throws Exception {
		BookDto info = new BookDto();
		info.setAuthorName("Amish");
		info.setBookDetails("Ram Chandra Series");
		info.setBookName("Ram");
		info.setImage("ram.jpg");
		info.setPrice(1000.00);
		info.setNoOfBooks((long) 2);
		
		
		ObjectMapper object = new ObjectMapper();
		String bookdto = object.writeValueAsString(info);
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(EDIT_BOOKS_URI,1L)
				
				.content(bookdto).contentType(MediaType.APPLICATION_JSON)
				.header("token", "validToken");
		
		Mockito.when(service.editBook(Mockito.anyLong(),Mockito.any(), Mockito.anyString())).thenReturn(true);
		
		
		ResultActions resultAction = mockMvc.perform(request);
		MvcResult result = resultAction.andReturn();
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.CREATED.value());
	}
	
}
