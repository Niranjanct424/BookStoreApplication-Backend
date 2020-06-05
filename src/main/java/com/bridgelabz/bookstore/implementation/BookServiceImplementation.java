package com.bridgelabz.bookstore.implementation;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.dto.EditBookDto;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.exception.BookAlreadyExist;
import com.bridgelabz.bookstore.exception.UserException;
import com.bridgelabz.bookstore.repository.AddressRepository;
import com.bridgelabz.bookstore.repository.BookImple;
import com.bridgelabz.bookstore.repository.IUserRepository;
import com.bridgelabz.bookstore.service.IBookService;
import com.bridgelabz.bookstore.util.JwtGenerator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookServiceImplementation implements IBookService {
	private Book bookinformation = new Book();
	private ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private BookImple repository;

	@Autowired
	private IUserRepository userRepository;
	@Autowired
	AddressRepository addrepository;

	@Autowired
	private JwtGenerator generate;

	@Transactional
	@Override
	public boolean addBooks(BookDto information, String token) {
		Long id;

		id = (long) generate.parseJWT(token);
		Users userInfo = userRepository.getUserById(id);
		if (userInfo != null) {
			String userRole = userInfo.getRole();
			System.out.println("actual Role is " + userRole);
			String fetchRole = userRole;

			if (fetchRole.equals("seller") || userRole.equals("admin")) {
				Book book = repository.fetchbyBookName(information.getBookName());
				System.out.println("Book name " + information.getBookName());

				if (book == null) {
					bookinformation = modelMapper.map(information, Book.class);
					bookinformation.setBookName(information.getBookName());
					bookinformation.setAuthorName(information.getAuthorName());
					bookinformation.setPrice(information.getPrice());
					bookinformation.setPrice(information.getPrice());
					bookinformation.setStatus("OnHold");

					bookinformation.setNoOfBooks(information.getNoOfBooks());

					bookinformation.setCreatedDateAndTime(LocalDateTime.now());

					repository.save(bookinformation);
					return true;
				} else {
					throw new BookAlreadyExist("Book is already exist Exception..");
				}
			} else {
				throw new UserException("Your are not Authorized User");
			}
		} else {
			throw new UserException("User doesn't exist");
		}

	}

	@Transactional
	@Override
	public List<Book> getBookInfo(String token) {
		Long id;

		id = (long) generate.parseJWT(token);
		Users userInfo = userRepository.getUserById(id);
		if (userInfo != null) {
			List<Book> users = repository.getAllBooks();
			return users;
		} else {
			throw new UserException("User doesn't exist");
		}

	}

	public double getOriginalPrice(double price, long quantity) {
		long result = (long) (price / quantity);
		return result;
	}

	@Override
	public Book getTotalPriceofBook(long bookId, long quantity) {
		Book bookinfo = repository.fetchbyId(bookId);
		double Price = bookinfo.getPrice();

		long Quantity = quantity;

		if (Quantity <= bookinfo.getNoOfBooks() || Quantity >= bookinfo.getNoOfBooks()) {
			if (bookinfo != null && quantity > 0) {
				double price = getOriginalPrice(Price, bookinfo.getNoOfBooks());
				double totalPrice = (price * Quantity);
				bookinfo.setNoOfBooks(quantity);

				bookinfo.setNoOfBooks(quantity);

				bookinfo.setPrice(totalPrice);
				repository.save(bookinfo);
				return bookinfo;
			} else if (bookinfo != null && quantity < 1) {
				double price = getOriginalPrice(Price, bookinfo.getNoOfBooks());
				double totalPrice = (price * 1);
				bookinfo.setNoOfBooks(quantity);
				bookinfo.setPrice(totalPrice);
				repository.save(bookinfo);
				return bookinfo;
			}
		}
		return null;
	}

	@Transactional
	@Override
	public List<Book> sortGetAllBooks() {
		List<Book> list = repository.findAll();
		list.sort((Book book1, Book book2) -> book1.getCreatedDateAndTime().compareTo(book2.getCreatedDateAndTime()));
		return list;
	}

	@Override
	public List<Book> sorting(boolean value) {
		List<Book> list = repository.findAll();
		if (value == true) {
			list.sort((Book book1, Book book2) -> book1.getPrice().compareTo(book2.getPrice()));
			return list;
		} else {
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
		Book info = repository.fetchbyId(bookId);
		if (info != null) {
			return info;
		}
		return null;
	}

	@Override
	public boolean editBook(long bookId, EditBookDto information, String token) {

		Long id;

		id = (long) generate.parseJWT(token);
		Users userInfo = userRepository.getUserById(id);
		if (userInfo != null) {
			String userRole = userInfo.getRole();
			System.out.println("actual Role is " + userRole);
			String fetchRole = userRole;

			if (fetchRole.equals("seller") || userRole.equals("admin")) {
				Book info = repository.fetchbyId(bookId);
				if (info != null) {
					info.setBookId(bookId);
					info.setBookName(information.getBookName());
					info.setNoOfBooks(information.getNoOfBooks());
					info.setPrice(information.getPrice());
					info.setAuthorName(information.getAuthorName());
					info.setBookDetails(information.getBookDetails());
					// info.setImage(information.getImage());
					info.setUpdatedDateAndTime(information.getUpdatedAt());
					repository.save(info);
					return true;
				}
			}
		} else {
			throw new UserException("User doesn't exist");
		}

		return false;
	}

	@Transactional
	@Override
	public boolean deleteBook(long bookId, String token) {
		Long id;

		id = (long) generate.parseJWT(token);
		Users userInfo = userRepository.getUserById(id);
		if (userInfo != null) {
			String userRole = userInfo.getRole();
			System.out.println("actual Role is " + userRole);
			log.info("Actual ");
			String fetchRole = userRole;

			if (fetchRole.equals("seller") || userRole.equals("admin")) {
				Book info = repository.fetchbyId(bookId);
				if (info != null) {
					repository.deleteByBookId(bookId);
					return true;
				}
			} else {
				throw new UserException("Your are not Authorized User");
			}
		} else {
			throw new UserException("User doesn't exist");
		}

		return false;
	}

//	@Override
//	public List<Book> getAllAprovedBooks(String token) 
//	{
//		Long id;
//		
//			id = (long) generate.parseJWT(token);
//			Users userInfo = userRepository.getUserById(id);
//			if(userInfo != null) 
//			{
//				List<Book> approvedBooks=repository.getAllApprovedBooks();
//				return approvedBooks;
//			}
//			else 
//			{
//				throw new UserException("User doesn't exist");
//			}
//	
//	}

	@Transactional
	@Override
	public boolean editBookStatus(long bookId, String status, String token) {
		Long id;

		id = (long) generate.parseJWT(token);
		Users userInfo = userRepository.getUserById(id);
		log.info("");
		if (userInfo != null) {
			Book info = repository.fetchbyId(bookId);
			if (info != null) {
				repository.updateBookStatusByBookId(status, bookId);
				return true;
			}
		} else {
			throw new UserException("User doesn't exist");
		}

		return false;
	}

	@Transactional
	@Override
	public List<Book> getAllOnHoldBooks(String token) {
		Long id;

		id = (long) generate.parseJWT(token);
		Users userInfo = userRepository.getUserById(id);
		if (userInfo != null) {
			List<Book> approvedOnHoldBooks = repository.getAllOnHoldBooks();
			return approvedOnHoldBooks;
		} else {
			throw new UserException("User doesn't exist");
		}

	}

	public List<Book> getAllRejectedBooks(String token) {
		Long id;

		id = (long) generate.parseJWT(token);
		Users userInfo = userRepository.getUserById(id);
		if (userInfo != null) {
			List<Book> rejectedBooks = repository.getAllRejectedBooks();
			return rejectedBooks;
		} else {
			throw new UserException("User doesn't exist");
		}

	}

	/**
	 * This controller is for getting 12 approval books per page! it can search book
	 * based on there autherName it can sort the book by anything like price,
	 * book_name, book_id etc, it can order the book both asc and desc order default
	 * will be desc order it can return the book based on there passing url
	 * paramater
	 * 
	 * @param searchByBooKName example (" ", book name, raju, etc)
	 * @param page             Example (" ", 1,2,3,4.........)
	 * @param sortBy           example (" ", book_id, price, created_date_and_time
	 *                         etc)
	 * @param order            (" ", asc,desc,)
	 * @return 12 books and number of page and everything
	 */
	@Override
	public Page<Book> getBookAproval(Optional<String> searchBy, Optional<Integer> page, Optional<String> sortBy,
			Optional<String> order) {
		if (order.equals(Optional.ofNullable("asc"))) {
			return repository.findByBookName(searchBy.orElse("_"),
					PageRequest.of(page.orElse(0), 12, Sort.Direction.ASC, sortBy.orElse("book_id")));
		} else {
			return repository.findByBookName(searchBy.orElse("_"),
					PageRequest.of(page.orElse(0), 12, Sort.Direction.DESC, sortBy.orElse("book_id")));
		}
	}

	@Override
	public List<Book> getAllAprovedBook() {
		List<Book> approvedBooks = repository.getAllApprovedBooks();
		return approvedBooks;
	}

	@Transactional
	@Override
	public boolean uploadBookImage(long bookId, String imageName, String token) {
		Long id;

		id = (long) generate.parseJWT(token);
		Users userInfo = userRepository.getUserById(id);
		if (userInfo != null) {
			String userRole = userInfo.getRole();
			System.out.println("actual Role is " + userRole);
			String fetchRole = userRole;

			if (fetchRole.equals("seller") || userRole.equals("admin")) {
				Book info = repository.fetchbyId(bookId);
				if (info != null) {
					info.setImage(imageName);
					repository.save(info);
					return true;
				}
			}
		} else {
			throw new UserException("User doesn't exist");
		}

		return false;
	}

}
