package com.bridgelabz.bookstore.servicelayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.implementation.BookServiceImplementation;
import com.bridgelabz.bookstore.implementation.CartServiceImplimentation;
import com.bridgelabz.bookstore.repository.BookImple;
import com.bridgelabz.bookstore.repository.BookInterface;
import com.bridgelabz.bookstore.repository.CustomerRepository;
import com.bridgelabz.bookstore.repository.IUserRepository;
import com.bridgelabz.bookstore.util.JwtGenerator;

@RunWith(MockitoJUnitRunner.class)
class BookServiceImplementationTests {

	@InjectMocks
	BookServiceImplementation bookService;

	@Mock
	CartServiceImplimentation cusservice;
	
	@Mock
	BookDto dto;

	@Mock
	JwtGenerator jwt;

	@Mock
	BookInterface bookRepo;
	
	@Mock
	private BookImple repository;
	
	@Mock
	CustomerRepository userRepo;
	
	@Mock
	private IUserRepository userRepository;

	private MockMvc mockMvc;
	
	@Mock
	private ModelMapper modelMapper = new ModelMapper();
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(bookService).build();
	}

//	@Test
//	final void testGetBookInfo() {
//		List<Book> users = new ArrayList<Book>();
//		when(repository.getAllBooks()).thenReturn(users);
//		List<Book> empList = bookService.getBookInfo();
//		assertEquals(empList.size(), users.size());
//	}

	@Test
	final void testGetBookbyId() {
		Book info = new Book();
		when(repository.fetchbyId((long) 1)).thenReturn(info);
		Book bookinfo = bookService.getBookbyId((long) 1);
		assertEquals(info, bookinfo);
	}

	@Test
	final void testPagewise() {
		List<Book> book = new ArrayList<Book>();
		when(repository.findAllPage(PageRequest.of(0, 2))).thenReturn(book);
		List<Book> list = bookService.findAllPageBySize(0);
		assertEquals(book.size(), list.size());
	}

	@Test
	final void testUnSort() {
		List<Book> book = new ArrayList<Book>();
		when(repository.findAll()).thenReturn(book);
		List<Book> sort = bookService.sortGetAllBooks();
		assertEquals(sort.size(), book.size());
	}

	@Test
	final void sorting() {
		List<Book> book = new ArrayList<Book>();
		when(repository.findAll()).thenReturn(book);
		List<Book> sort = bookService.sorting(false);
		assertEquals(sort.size(), book.size());
	}

	@Test
	final void add_Books_Test() {
		String token="validToken";
		long userId=1L;
		Mockito.when(jwt.parseJWT(token)).thenReturn(userId);
		Users user=new Users();
		user.setUserId((long) 1);
		user.setName("brijesh");
		user.setMobileNumber(7259866545L);
		user.setAddress(null);
		user.setCartBooks(null);
		user.setCreatedDate(null);
		user.setRole("seller");
		
		Mockito.when(userRepository.getUserById((long) 1)).thenReturn(user);
		
		BookDto dto = new BookDto();
		Book book = new Book();
		Book info = new Book();
		info.setAuthorName("Amish");
		info.setBookDetails("Shiva Triology");
		info.setBookName("Ram");
		info.setImage("vayuputra.jpg");
		info.setPrice(1000.00);
		info.setNoOfBooks(20L);
		
		Mockito.when(repository.fetchbyBookName(Mockito.anyString()) ).thenReturn(info);
		
		Mockito.when(modelMapper.map(Mockito.any() ,Mockito.any())).thenReturn(info);
		
		when(repository.save(info)).thenReturn(book);
		
		dto.setAuthorName(book.getAuthorName());
		dto.setBookDetails(book.getBookDetails());
		dto.setBookName(book.getBookName());
		dto.setImage(book.getImage());
		dto.setPrice(book.getPrice());
		dto.setNoOfBooks(book.getNoOfBooks());
		boolean check = bookService.addBooks("vayuputra.jpg", dto, token);
		assertTrue(check == true);
	}
	
	
	
	
	
}
