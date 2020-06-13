package com.bridgelabz.bookstore.implementation;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.exception.AdminNotFoundException;
import com.bridgelabz.bookstore.exception.BookNotFoundException;
import com.bridgelabz.bookstore.repository.BookImple;
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

	@Override
	public boolean verifyBook(long bookId, String token) {

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
			Book book = bookRepository.fetchbyId(bookId);
			if (book != null) {
				book.setStatus("approved");

				bookRepository.save(book);
				return true;
			} else {
				throw new BookNotFoundException("Book Not Found");
			}

		} else {
			throw new AdminNotFoundException("Admin Not Found");
		}
	}

	@Override
	public boolean rejectBook(long bookId, String token) {

		long userid = 0;
		Users user = null;
		try {
			userid = jwt.parseJWT(token);
			System.out.println("user id:" + userid);
			user = userRepo.getCustomerDetailsbyId(userid);
			System.out.println("Admin " + user);
			System.out.println("user:" + user);
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

		if (user != null) {
			Book book = bookRepository.fetchbyId(bookId);
			if (book != null) {
				System.out.println("book " + book);
				book.setStatus("rejected");

				bookRepository.save(book);
				return true;
			} else {
				throw new BookNotFoundException("Book Not Found");
			}

		} else {
			throw new AdminNotFoundException("Admin Not Found");
		}

	}

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

}
