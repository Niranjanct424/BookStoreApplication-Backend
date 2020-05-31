package com.bridgelabz.bookstore.implementation;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.CartItem;
import com.bridgelabz.bookstore.entity.Quantity;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.repository.BookImple;
import com.bridgelabz.bookstore.repository.QuantityRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.service.ICartService;
import com.bridgelabz.bookstore.util.JwtGenerator;

@Service
public class CartServiceImplimentation implements ICartService{
	@Autowired
	private JwtGenerator generate;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BookImple bookRepository;
	@Autowired
	private QuantityRepository quantityRepository;
	Users user=new Users();
	@Transactional
	@Override
	public List<CartItem> addBooktoCart(String token, long bookId) {
		Long id;
		try {
			id = (long) generate.parseJWT(token);
		
		
		Users user = userRepository.findById(id).orElse(null);

		Book book = bookRepository.findById(bookId).orElse(null);
		/**
		 * Getting the bookList
		 */
		List<Book> books = null;
		for (CartItem d : user.getCartBooks()) {
			books = d.getBooksList();
		}
		/**
		 * For the first time adding the book the cartList
		 */
		
		if (books == null) {
			Users userdetails = this.cartbooks(book,user);
			return userRepository.save(userdetails).getCartBooks();
		}
		/**
		 * Checking whether book is already present r not
	     */
		
		Optional<Book> cartbook = books.stream().filter(t -> t.getBookId() == bookId).findFirst();

		if (cartbook.isPresent()) {
			throw null;
		} else {
			Users userdetails = this.cartbooks(book,user);
			return userRepository.save(userdetails).getCartBooks();
		}
		
		} catch (JWTVerificationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;

	}

	
	public Users cartbooks(Book book,Users user) {
		long quantity=1;
        CartItem cart =new CartItem();
		Quantity qunatityofbook = new Quantity();
		
		ArrayList<Book> booklist = new ArrayList<>();
		ArrayList<Quantity> quantitydetails = new ArrayList<Quantity>();
		/**
		 * adding the book details
		 */
		booklist.add(book);
		cart.setCreatedTime(LocalDateTime.now());
		cart.setBooksList(booklist);
		/**
		 * adding the quantity to the book
		 */
		qunatityofbook.setQuantityOfBook(quantity);
		qunatityofbook.setTotalprice(book.getPrice());
		quantitydetails.add(qunatityofbook);
		
		cart.setQuantityOfBook(quantitydetails);
		/**
		 * saving the complete cart in user
		 */
		user.getCartBooks().add(cart);
		return user;
	}


	@Transactional
	@Override
	public List<CartItem> getBooksfromCart(String token) {
		Long id;
		try {
			id = (long) generate.parseJWT(token);
		Users user = userRepository.findById(id).orElse(null);//orElseThrow(() -> new UserException()));
		List<CartItem> cartBooks = user.getCartBooks();
		return cartBooks;
		} catch (JWTVerificationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	@Transactional
	@Override
	public boolean removeBooksFromCart(String token, Long bookId) {
		Long id;
		try {
			id = (long) generate.parseJWT(token);
			Users user = userRepository.findById(id).orElse(null);
			Book book = bookRepository.findById(bookId).orElse(null);
			Quantity quantity = quantityRepository.findById(id).orElseThrow(null);
			for (CartItem cartt : user.getCartBooks()) {
				boolean exitsBookInCart = cartt.getBooksList().stream()
						.noneMatch(books -> books.getBookId().equals(bookId));
				if (!exitsBookInCart) {
					userRepository.save(user);
					cartt.getQuantityOfBook().remove(quantity);
					cartt.getBooksList().remove(book);
					cartt.getQuantityOfBook().clear();
					boolean users = userRepository.save(user).getCartBooks()
					!= null ? true : false;
					if (user != null) {
						return users;
					}
				}
			}
			return false;
		} catch (JWTVerificationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	@Transactional
	@Override
	public int getCountOfBooks(String token) {
		Long id;
		try {
			id = (long) generate.parseJWT(token);
         int countOfBooks=0;
		Users user = userRepository.findById(id).orElseThrow(null);
		List<CartItem> cartBooks = user.getCartBooks();
         for(CartItem cart:cartBooks) {
        	 if(!cart.getBooksList().isEmpty()) {
        		 countOfBooks++;
        	 }
         }
		return countOfBooks;
		} catch (JWTVerificationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return 0;
	}


	@Transactional
	@Override
	public CartItem IncreaseBooksQuantityInCart(String token, Long bookId, CartDto bookQuantityDetails) {
		Long id;
		try {
			id = (long) generate.parseJWT(token);
			Long quantityId = bookQuantityDetails.getQuantityId();
			Long quantity = bookQuantityDetails.getQuantityOfBook();
			// () -> new UserException())
			Users user = userRepository.findById(id).orElseThrow(null);
			Book book = bookRepository.findById(bookId).orElseThrow(null);
			double totalprice = book.getPrice() * (quantity + 1);
			boolean notExist = false;
			for (CartItem cartt : user.getCartBooks()) {
				if (!cartt.getBooksList().isEmpty()) {
					notExist = cartt.getBooksList().stream().noneMatch(books -> books.getBookId().equals(bookId));
					if (!notExist) {
						Quantity quantityDetails = quantityRepository.findById(quantityId).orElseThrow(null);
						quantityDetails.setQuantityOfBook(quantity + 1);
						quantityDetails.setTotalprice(totalprice);
						quantityRepository.save(quantityDetails);
						return cartt;	}}
			}

			return null;
		} catch (JWTVerificationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Transactional
	@Override
	public CartItem descreaseBooksQuantityInCart(String token, Long bookId, CartDto bookQuantityDetails) {
		Long id;
		try {
			id = (long) generate.parseJWT(token);
		Long quantityId = bookQuantityDetails.getQuantityId();
		Long quantity = bookQuantityDetails.getQuantityOfBook();

		Users user = userRepository.findById(id).orElseThrow(null);
		Book book = bookRepository.findById(bookId).orElseThrow(null);
		double totalprice=book.getPrice()*(quantity-1);
		boolean notExist = false;
		for (CartItem cartt : user.getCartBooks()) {
			if(!cartt.getBooksList().isEmpty()) {	
			 notExist = cartt.getBooksList().stream()
					.noneMatch(books -> books.getBookId().equals(bookId));
			 if (!notExist) {

					Quantity quantityDetails = quantityRepository.findById(quantityId).orElseThrow(null);
					quantityDetails.setQuantityOfBook(quantity-1);
					quantityDetails.setTotalprice(totalprice);
					quantityRepository.save(quantityDetails);
					return cartt;
			
					}

		       }
		}

		return null;
		} catch (JWTVerificationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;}
	
	
	
}
