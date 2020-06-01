package com.bridgelabz.bookstore.implementation;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Cart;
import com.bridgelabz.bookstore.entity.Quantity;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.repository.BookImple;
import com.bridgelabz.bookstore.repository.IUserRepository;
import com.bridgelabz.bookstore.response.MailResponse;
import com.bridgelabz.bookstore.service.ICartService;
import com.bridgelabz.bookstore.util.JwtGenerator;

@Service
public class CartServiceImplimentation implements ICartService{
	@Autowired
	private JwtGenerator generate;

	@Autowired
	private MailResponse response;
	@Autowired
	private IUserRepository repository;
	@Autowired
	private BookImple bookRepository;
	Users user=new Users();
	Cart cart =new Cart();
	 Quantity quanty=new Quantity();
	@Override
	public List<Cart> addBooktoCart(String token, long bookId) {
		try {
			Long id = (long) generate.parseJWT(token);
			 user= repository.getUserById(id);
			if(user!=null) {
		    // this book as to be fetched from approved by adimin fetchid should fetch approved books
			Book book=bookRepository.fetchbyId(bookId);
			
			List<Book> books = null; //intialization of book list ftch all book and checking storing in list of books
			for (Cart cart : user.getCartBooks()) {
				books = cart.getBooksList();
			}
			//checking for book is null ,adding the book
			if(book==null) {
			   List<Book> booklist=new ArrayList<>();
			   List<Quantity> quantityList=new ArrayList<Quantity>();
			   booklist.add(book);
				cart.setBooksList(booklist);
				quanty.setQuantityOfBook(1);
				quanty.setQuantityOfBook(1);
				quanty.setTotalprice(100.00);//at present hard coded later bring from book dto
				quantityList.add(quanty);
				cart.setQuantityOfBook(quantityList);
				user.getCartBooks().add(cart);//adding all cart details with product details we are adding to user
				Users userdetails=repository.save(user);
			   return userdetails.getCartBooks();
			}
			}
			} catch (JWTVerificationException | IllegalArgumentException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	


}
