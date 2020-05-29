package com.bridgelabz.bookstore.implementation;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.dto.EditBookDto;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Cart;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.exception.UserException;
import com.bridgelabz.bookstore.repository.AddressRepository;
import com.bridgelabz.bookstore.repository.BookImple;
import com.bridgelabz.bookstore.repository.CustomerRepository;
import com.bridgelabz.bookstore.repository.IUserRepository;
import com.bridgelabz.bookstore.service.IBookService;
import com.bridgelabz.bookstore.util.JwtGenerator;

@Service
public class BookServiceImplementation implements IBookService {
	private Book bookinformation = new Book();
	private Cart cartinformation = new Cart();
	private ModelMapper modelMapper = new ModelMapper();
//	@Autowired
//	private ModelMapper modelMapper;
//	@Autowired
//	private IBook repository;
	@Autowired
	private BookImple repository;
	
	@Autowired
	private IUserRepository userRepository;

	@Autowired
	CustomerRepository customerrep;
	
	@Autowired
	AddressRepository addrepository;
	
	@Autowired
	private JwtGenerator generate;
//	@Autowired
//	private CartImple cartrepository;

	@Transactional
	@Override
	public boolean addBooks(BookDto information,String token)
	{	
		Long id;
		try 
		{
			id = (long) generate.parseJWT(token);
			Users userInfo = userRepository.getUserById(id);
			if(userInfo != null) 
			{			
				String userRole = userInfo.getRole();
				System.out.println("actual Role is " + userRole);
				String fetchRole = userRole;
				
				if (fetchRole.equals("seller") && !userRole.equals("admin")) 
				{
					bookinformation = modelMapper.map(information, Book.class);
					bookinformation.setBookName(information.getBookName());
					bookinformation.setAuthorName(information.getAuthorName());
					bookinformation.setPrice(information.getPrice());
					bookinformation.setQuantity(information.getQuantity());
					bookinformation.setCreatedDateAndTime(LocalDateTime.now());
					bookinformation.setStatus("OnHold");
					repository.save(bookinformation);
					return true;
				}
				else 
				{
					throw new UserException("Your are not Authorized User");
				}
			}
			else 
			{
				throw new UserException("User doesn't exist");
			}
		} 
		catch (JWTVerificationException | IllegalArgumentException | UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
		return false;	
	}

	@Transactional
	@Override
	public List<Book> getBookInfo() 
	{
		List<Book> users = repository.getAllBooks();
		return users;
	}
	
	

//	@Transactional
//	@Override
//	public boolean addandupdatecart(Long userId, int quantity, Long bookId) {
//		Book book = repository.fetchbyId(bookId);
//		Cart cart = cartrepository.fetchbyId(bookId);
//		if (cart != null) {
////			cart.getQuantity()
//			int updatedquantity =   quantity;
//			System.out.println(updatedquantity);
//			if (book.getQuantity() >= updatedquantity) {
//
//				cartrepository.verifyTheUser(updatedquantity, bookId);
//				return true;
//			} else
//				return false;
//		} else if (book.getQuantity() >= quantity) {
////			cartinformation.setUserId(userId);
////			cartinformation.setQuantity(quantity);
////			cartinformation.setBookId(bookId);
//			cartrepository.save(cartinformation);
//			return true;
//		}
//		return false;
//
//	}

	public double getOriginalPrice(double price, int quantity) {
		long result = (long) (price / quantity);
		return result;
	}

	@Override
	public Book getTotalPriceofBook(long bookId, int quantity) {
		Book bookinfo = repository.fetchbyId(bookId);
		double Price = bookinfo.getPrice();
		int Quantity = quantity;
		if (Quantity <= bookinfo.getQuantity() || Quantity >= bookinfo.getQuantity()) {
			if (bookinfo != null && quantity > 0) {
				double price = getOriginalPrice(Price, bookinfo.getQuantity());
				double totalPrice = (price * Quantity);
				bookinfo.setQuantity(quantity);
				bookinfo.setPrice(totalPrice);
				repository.save(bookinfo);
				return bookinfo;
			} else if (bookinfo != null && quantity < 1) {
				double price = getOriginalPrice(Price, bookinfo.getQuantity());
				double totalPrice = (price * 1);
				bookinfo.setQuantity(quantity);
				bookinfo.setPrice(totalPrice);
				repository.save(bookinfo);
				return bookinfo;
			}
		}
		return null;
	}
	
	@Transactional
	@Override
	public void removefromcart(Long userId, Long bookId) {
		// CartInformation cart =cartrepository.fetchbyId(bookId);
		// System.out.println(cart);
//		cartrepository.deletebyId(bookId);
	}

	@Transactional
	@Override
	public List<Book> sortGetAllBooks() {
		List<Book> list = repository.findAll();
		list.sort((Book book1, Book book2) -> book1.getCreatedDateAndTime().compareTo(book2.getCreatedDateAndTime()));
		return list;
	}

//	@Transactional
//	@Override
//	public boolean addandupdatecart(Long userId, int quantity, Long bookId) {
//		BookInformation book = repository.fetchbyId(bookId);
//		CartInformation cart = cartrepository.fetchbyId(bookId);
//		//Session session=new Session();
//		if (cart != null) {
//			int updatedquantity = cart.getQuantity() + quantity;
//			System.out.println(updatedquantity);
//			if (book.getQuantity() >= updatedquantity) {
//				cartrepository.verifyTheUser(updatedquantity, bookId);
//				return true;
//			} else
//				return false;
//		} else if (book.getQuantity() >= quantity) {
//			cartinformation.setUserId(userId);
//			cartinformation.setQuantity(quantity);
//			cartinformation.setBookId(bookId);
//			cartrepository.save(cartinformation);
//			return true;
//		}
//		return false;
//
//	}
//	@Transactional
//	@Override
//	public String setPurchasingQuantity(Long userId, int quantity, Long bookId) {
//		BookInformation bookid = repository.fetchbyId(bookId);
//		System.out.println("bookid"+bookId);
//		if(bookid.getQuantity()>=quantity) {
//			cartinformation.setQuantity(bookid.getQuantity()-quantity);
//			System.out.println(bookid.getQuantity()-quantity);
//			cartrepository.save(cartinformation);
//		}else {
//		}
//		return "";
//
//	}

//}
	
	@Override
	public List<Book> sorting(boolean value){
		List<Book> list = repository.findAll();
		if(value==true) {
		list.sort((Book book1, Book book2) -> book1.getPrice().compareTo(book2.getPrice()));
		return list;
		}
		else {
			list.sort((Book book1, Book book2) -> book1.getPrice().compareTo(book2.getPrice()));
			Collections.reverse(list);
			return list;
		}
	}

	@Override
	public List<Book> findAllPageBySize(int pagenumber) {
		long count = repository.count();
		int pageSize = 2;
		int pages = (int) ((count / pageSize));
		int i = pagenumber; // i should start with zero or 0...
		while (i <= pages) {
			List<Book> list = repository.findAllPage(PageRequest.of(i, pageSize));
			i++;
			return list;
		}
		return null;
	}

	@Override
	public Book getBookbyId(long bookId) {
	           Book info	= repository.fetchbyId(bookId);
	           if( info != null) {
	        	   return info;
	           }
		return null;
	}

	@Override
	public boolean editBook(EditBookDto information,String token) {
		
		Long id;
		try 
		{
			id = (long) generate.parseJWT(token);
			Users userInfo = userRepository.getUserById(id);
			if(userInfo != null) 
			{			
				String userRole = userInfo.getRole();
				System.out.println("actual Role is " + userRole);
				String fetchRole = userRole;
				
				if (fetchRole.equals("seller") && !userRole.equals("admin")) 
				{
					Book info =repository.fetchbyId(information.getBookId());
					if(info!=null) 
					{
						info.setBookId(information.getBookId());
						info.setBookName(information.getBookName());
						info.setQuantity(information.getQuantity());
						info.setPrice(information.getPrice());
						info.setAuthorName(information.getAuthorName());
						info.setBookDetails(information.getBookDetails());
						info.setImage(information.getImage());
						info.setUpdatedDateAndTime(information.getUpdatedAt());
						repository.save(info);
						return true;
					}
				}
				else 
				{
					throw new UserException("Your are not Authorized User");
				}
			}
			else 
			{
				throw new UserException("User doesn't exist");
			}
		} 
		catch (JWTVerificationException | IllegalArgumentException | UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Transactional
	@Override
	public boolean deleteBook(long bookId,String token) {
		Long id;
		try 
		{
			id = (long) generate.parseJWT(token);
			Users userInfo = userRepository.getUserById(id);
			if(userInfo != null) 
			{			
				String userRole = userInfo.getRole();
				System.out.println("actual Role is " + userRole);
				String fetchRole = userRole;
				
				if (fetchRole.equals("seller") && !userRole.equals("admin")) 
				{
					Book info =repository.fetchbyId(bookId);
					if(info!=null)
					{
						repository.deleteByBookId(bookId);
						return true;
					}
				}
				else 
				{
					throw new UserException("Your are not Authorized User");
				}
			}
			else 
			{
				throw new UserException("User doesn't exist");
			}
		} 
		catch (JWTVerificationException | IllegalArgumentException | UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public List<Book> getAllAprovedBooks() {
		List<Book> approvedBooks=repository.getAllApprovedBooks();
		return approvedBooks;
	}
	
	@Transactional
	@Override
	public boolean editStatus(long bookId, String status) {
		Book info =repository.fetchbyId(bookId);
		if(info != null) {
			repository.updateBookStatusByBookId(status, bookId);
			return true;
		}
		return false;
	}
	@Transactional
	@Override
	public List<Book> getAllApprovedAndOnHoldBooks() {
		List<Book> approvedOnHoldBooks=repository.getAllApprovedOnHoldBooks();
		return approvedOnHoldBooks;
	}

	@Override

	public boolean addandupdatecart(Long userId, int quantity, Long bookId) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Book> getAllRejectedBooks() {
		List<Book> rejectedBooks=repository.getAllRejectedBooks();
		return rejectedBooks;
	}

	

	

}
