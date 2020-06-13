package com.bridgelabz.bookstore.servicelayer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bridgelabz.bookstore.controller.BookStoreController;
import com.bridgelabz.bookstore.controller.CartController;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.CartItem;
import com.bridgelabz.bookstore.implementation.BookServiceImplementation;
import com.bridgelabz.bookstore.implementation.CartServiceImplimentation;
import com.bridgelabz.bookstore.repository.BookImple;

public class AdminServiceImpleTest {
	
	@InjectMocks
	BookStoreController bookStoreController;
	
	@InjectMocks
	CartController cartController;
	
	@Mock
	BookServiceImplementation bookServiceImplementation;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Mock
	private BookImple repository;
	
	Book info;
	
	@Mock
	CartServiceImplimentation cartService;
	
	@Test
	final void bookInfoByIfTest() {
		
		info = new Book();
		
		info.setBookId(1L);
		info.setNoOfBooks((long)118);
		info.setPrice(670.0);
		info.setAuthorName("Abbar");
		info.setBookDetails("bu’l Fath Jalal-ud-din Muhammad Akbar, the third Mughal emperor, is widely regarded as one of the greatest rulers in India’s history");
		info.setImage("Akbar.jpg");
		
		when(bookServiceImplementation.getBookbyId((long)1)).thenReturn(info);
		Book bookinfo = bookServiceImplementation.getBookbyId((long) 1);
		
		System.out.println("Expected Author Name : "+bookinfo.getAuthorName());
		System.out.println("Actual Author Name : "+info.getAuthorName());
		assertEquals(info.getAuthorName(), bookinfo.getAuthorName());
		
		System.out.println("Expected BookDetails : "+info.getBookDetails());
		System.out.println("Actual BookDetails : "+bookinfo.getBookDetails());
		assertEquals(info.getBookDetails(), bookinfo.getBookDetails());
		
		assertEquals(info.getPrice(), bookinfo.getPrice());
		
		assertEquals(info.getNoOfBooks(), bookinfo.getNoOfBooks());
		
		assertEquals(info.getImage(), bookinfo.getImage());
	}
	
	
	CartItem cartItem;
	
	@Test
	final void addBooktoCartTest() {
		
		cartItem = new CartItem();
		cartItem.setCartId(((long)5));
		cartItem.setBooksList((List<Book>) info);
		
		when(cartService.addBooktoCart("token", 5)).thenReturn((List<CartItem>) cartItem);
		
//		cartItem = cartService.addBooktoCart("token", 5);
//		
//		assertEquals("equals ", expecteds, actuals);
		
		
	}
	

	
//	cartId": 4,
//    "booksList": [],
//    "quantityOfBook": [],
//    "createdTime": "2020-06-10T17:15:26"
//  },
//  {
//    "cartId": 5,
//    "booksList": [
//      {
//        "bookId": 1,
//        "bookName": "The Great Mughal Hardcover ",
//        "noOfBooks": 118,
//        "price": 670,
//        "authorName": "Abbar",
//        "bookDetails": "bu’l Fath Jalal-ud-din Muhammad Akbar, the third Mughal emperor, is widely regarded as one of the greatest rulers in India’s history",
//        "createdDateAndTime": "2020-06-07T15:47:53",
//        "updatedDateAndTime": null,
//        "status": "OnHold",
//        "image": "Akbar.jpg",
//        "reviewRating": []
//      }
//    ],
//    "quantityOfBook": [
//      {
//        "quantity_id": 5,
//        "quantityOfBook": 1,
//        "totalprice": 670
//      }
//    ],
//    "createdTime": "2020-06-12T03:59:27.557"
//  }
//],
	
	
	
	
//	
//	"bookId": 1,
//    "bookName": "The Great Mughal Hardcover ",
//    "noOfBooks": 118,
//    "price": 670,
//    "authorName": "Abbar",
//    "bookDetails": "bu’l Fath Jalal-ud-din Muhammad Akbar, the third Mughal emperor, is widely regarded as one of the greatest rulers in India’s history",
//    "createdDateAndTime": "2020-06-07T15:47:53",
//    "updatedDateAndTime": null,
//    "status": "OnHold",
//    "image": "Akbar.jpg",
//    "reviewRating": []
	
	
}
