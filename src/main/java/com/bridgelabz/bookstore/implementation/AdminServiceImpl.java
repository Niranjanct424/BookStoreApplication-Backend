package com.bridgelabz.bookstore.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.exception.AdminNotFoundException;
import com.bridgelabz.bookstore.exception.BookNotFoundException;
import com.bridgelabz.bookstore.repository.BookImple;
import com.bridgelabz.bookstore.repository.BookInterface;
import com.bridgelabz.bookstore.repository.CustomerRepository;
import com.bridgelabz.bookstore.service.IAdminService;
import com.bridgelabz.bookstore.util.JwtGenerator;

@Service
public class AdminServiceImpl implements IAdminService {

//	@Autowired
//	private IOrderStatusRepository orderStatusRepo;
//
	@Autowired
	CustomerRepository userRepo;

	@Autowired
	JwtGenerator jwt;

	@Autowired
	private BookImple bookRepository;
	
	@Autowired
	BookInterface bookRepo;

	@Override
	public boolean verifyBook(long bookId,String staus, String token) {

		long userid = 0;
		Users user = null;
			userid = jwt.parseJWT(token);
			System.out.println("user id:" + userid);
			user = userRepo.getCustomerDetailsbyId(userid);
			System.out.println("user:" + user);
	
		if (user != null) {
			Book book = bookRepo.findByBookId(bookId);
			System.out.println("bookinfo "+book);
			
			if (book != null) {
				book.setStatus(staus);

				bookRepo.save(book);
				return true;
				
			} else {
				throw new BookNotFoundException("Book Not Found");
			}

		} else {
			throw new AdminNotFoundException("Admin Not Found");
		}
	}

<<<<<<< HEAD
//	@Override
//	public boolean orderStatus(long orderId, String token) {

//		long userid = 0;
//		try {
//			userid = jwt.parseJWT(token);
//		} catch (JWTVerificationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		Users user = userRepo.getCustomerDetailsbyId(userid);
//
//		if (user != null) {
//
//			OrderStatus status = orderStatusRepo.fetchbyId(orderId);
//			status.setIspacked(true);
//			status.setShipped(true);
//			status.setDelivered(true);
//			orderStatusRepo.save(status);
//			return true;
//
//		} else {
//			throw new AdminNotFoundException("Admin Not Found");
//		}
//
//	}

	@Override
	public List<Book> getUnVerifiedBooks(String token) {

		long userid = 0;
		Users user = null;
		try {
			userid = jwt.parseJWT(token);
			System.out.println("user id:" + userid);
			user = userRepo.getCustomerDetailsbyId(userid);
			System.out.println("user:" + user);
		} catch (JWTVerificationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if (user != null) {
			return bookRepository.getAllonHoldBooks();

		} else {
			throw new BookNotFoundException("Admin Not Found cant get unverified books");
		}

	}

	@Override
	public List<Book> rejectedBooks(String token) {
		
		long userid = 0;
		Users user = null;
		try {
			userid = jwt.parseJWT(token);
			System.out.println("user id:" + userid);
			user = userRepo.getCustomerDetailsbyId(userid);
			System.out.println("user:" + user);
		} catch (JWTVerificationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if (user != null) {
			return bookRepository.getAllRejectedBooks();

		} else {
			throw new BookNotFoundException("Admin Not Found cant get unverified books");
		}

		
	}

	@Override
	public List<Book> getAllApprovedBooks(String token) {
		return bookRepository.getApprovedBooks();
	}
=======
		@Override
	public List<Book> getBooksByStatus(String status) {
		
		return bookRepo.findByStatus(status);
	}


>>>>>>> 244f51500a674a8c535845c8ccc2d121f97168dd

}
