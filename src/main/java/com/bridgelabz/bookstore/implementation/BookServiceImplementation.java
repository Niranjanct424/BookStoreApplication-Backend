package com.bridgelabz.bookstore.implementation;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.dto.EditBookDto;
import com.bridgelabz.bookstore.entity.BookInformation;
import com.bridgelabz.bookstore.entity.CartInformation;
import com.bridgelabz.bookstore.repository.AddressRepository;
import com.bridgelabz.bookstore.repository.BookImple;
import com.bridgelabz.bookstore.repository.CartImple;
import com.bridgelabz.bookstore.repository.CustomerRepository;
import com.bridgelabz.bookstore.service.IBookService;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

@Service
public class BookServiceImplementation implements IBookService {
	private BookInformation bookinformation = new BookInformation();
	private CartInformation cartinformation = new CartInformation();
	private ModelMapper modelMapper = new ModelMapper();
//	@Autowired
//	private ModelMapper modelMapper;
//	@Autowired
//	private IBook repository;
	@Autowired
	private BookImple repository;

	@Autowired
	CustomerRepository customerrep;
	
	@Autowired
	AddressRepository addrepository;
	
	@Autowired
	private CartImple cartrepository;

	@Transactional
	@Override
	public boolean addBooks(BookDto information) {
		bookinformation = modelMapper.map(information, BookInformation.class);
		bookinformation.setBookName(information.getBookName());
		bookinformation.setAuthorName(information.getAuthorName());
		bookinformation.setPrice(information.getPrice());
		bookinformation.setQuantity(information.getQuantity());
		bookinformation.setCreatedDateAndTime(LocalDateTime.now());
		bookinformation.setStatus("inhold");
		repository.save(bookinformation);
		return true;
	}

	@Transactional
	@Override
	public List<BookInformation> getBookInfo() 
	{
		List<BookInformation> users = repository.getAllBooks();
		return users;
	}
	
	

	@Transactional
	@Override
	public boolean addandupdatecart(Long userId, int quantity, Long bookId) {
		BookInformation book = repository.fetchbyId(bookId);
		CartInformation cart = cartrepository.fetchbyId(bookId);
		if (cart != null) {
//			cart.getQuantity()
			int updatedquantity =   quantity;
			System.out.println(updatedquantity);
			if (book.getQuantity() >= updatedquantity) {

				cartrepository.verifyTheUser(updatedquantity, bookId);
				return true;
			} else
				return false;
		} else if (book.getQuantity() >= quantity) {
//			cartinformation.setUserId(userId);
//			cartinformation.setQuantity(quantity);
//			cartinformation.setBookId(bookId);
			cartrepository.save(cartinformation);
			return true;
		}
		return false;

	}

	public double getOriginalPrice(double price, int quantity) {
		long result = (long) (price / quantity);
		return result;
	}

	@Override
	public BookInformation getTotalPriceofBook(long bookId, int quantity) {
		BookInformation bookinfo = repository.fetchbyId(bookId);
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
		cartrepository.deletebyId(bookId);
	}

	@Transactional
	@Override
	public List<BookInformation> sortGetAllBooks() {
		List<BookInformation> list = repository.findAll();
		list.sort((BookInformation book1, BookInformation book2) -> book1.getCreatedDateAndTime().compareTo(book2.getCreatedDateAndTime()));
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
	public List<BookInformation> sorting(boolean value){
		List<BookInformation> list = repository.findAll();
		if(value==true) {
		list.sort((BookInformation book1, BookInformation book2) -> book1.getPrice().compareTo(book2.getPrice()));
		return list;
		}
		else {
			list.sort((BookInformation book1, BookInformation book2) -> book1.getPrice().compareTo(book2.getPrice()));
			Collections.reverse(list);
			return list;
		}
	}

	@Override
	public List<BookInformation> findAllPageBySize(int pagenumber) {
		long count = repository.count();
		int pageSize = 2;
		int pages = (int) ((count / pageSize));
		int i = pagenumber; // i should start with zero or 0...
		while (i <= pages) {
			List<BookInformation> list = repository.findAllPage(PageRequest.of(i, pageSize));
			i++;
			return list;
		}
		return null;
	}

	@Override
	public BookInformation getBookbyId(long bookId) {
	           BookInformation info	= repository.fetchbyId(bookId);
	           if( info != null) {
	        	   return info;
	           }
		return null;
	}

	@Override
	public boolean editBook(EditBookDto information) {
		BookInformation info =repository.fetchbyId(information.getBookId());
		if(info!=null) {
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
		return false;
	}

	@Transactional
	@Override
	public boolean deleteBook(long bookId) {
		BookInformation info =repository.fetchbyId(bookId);
		if(info!=null) {
			repository.deleteByBookId(bookId);
			return true;
		}
		return false;
	}

	@Override
	public List<BookInformation> getAllAprovedBooks() {
		List<BookInformation> approvedBooks=repository.getAllApprovedBooks();
		return approvedBooks;
	}
	
	@Transactional
	@Override
	public boolean editStatus(long bookId, String status) {
		BookInformation info =repository.fetchbyId(bookId);
		if(info != null) {
			repository.updateBookStatusByBookId(status, bookId);
			return true;
		}
		return false;
	}

	
	

}
