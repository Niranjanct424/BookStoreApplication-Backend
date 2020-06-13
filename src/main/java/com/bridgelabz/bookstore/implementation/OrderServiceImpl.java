package com.bridgelabz.bookstore.implementation;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Order;
import com.bridgelabz.bookstore.entity.Quantity;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.exception.UserException;
import com.bridgelabz.bookstore.repository.BookImple;
import com.bridgelabz.bookstore.repository.implementation.UserRepositoryImplementation;
import com.bridgelabz.bookstore.service.OrderService;
import com.bridgelabz.bookstore.util.JwtGenerator;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private UserRepositoryImplementation userRepository;

	@Autowired
	private BookImple bookRepository;

	@Autowired
	private JwtGenerator jwt;

	@Autowired
	private Environment env;

	@Transactional
	@Override
	public List<Order> getOrderList(String token) {
		Long id;
		Users userdetails = null;
		try {
			id = jwt.parseJWT(token);
			userdetails = userRepository.getUserById(id);
		} catch (JWTVerificationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if (userdetails != null) {
			return userdetails.getOrderBookDetails();
		} else {
			throw new UserException(400, "Orders not found for user");
		}

	}

	@Transactional
	@Override
	public int getCountOfBooks(String token) {
		Long id;
		Users userdetails = null;
		try {
			id = jwt.parseJWT(token);
			userdetails = userRepository.getUserById(id);
		} catch (JWTVerificationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		int countOfBooks = 0;

		List<Order> orders = userdetails.getOrderBookDetails();
		for (Order cart : orders) {
			if (!cart.getBooksList().isEmpty()) {

				countOfBooks++;
			}
		}
		return countOfBooks;
	}

	@Transactional
	@Override
	public Order getOrderConfrim(String token) {
		Long id;
		Users userdetails = null;
		try {
			id = jwt.parseJWT(token);
			userdetails = userRepository.getUserById(id);
		} catch (JWTVerificationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		Order orderDetails = new Order();
		Random random = new Random();
		ArrayList<Book> list = new ArrayList<>();
		
//		  adding the books from cartlist to orderlist by generating the OrderId

		userdetails.getCartBooks().forEach((cart) -> {

			cart.getBooksList().forEach(book -> {
				long orderId;

//				  If order is confrim decreasing the numberOfBooks in BookList

				for (Quantity qt : cart.getQuantityOfBook()) {

					int noOfBooks = (int) (book.getQuantity() - qt.getQuantityOfBook());
					book.setQuantity(noOfBooks);
					Book bb = bookRepository.save(book);

					try {
						list.add(bb);
						orderId = random.nextInt(1000000);
						if (orderId < 0) {
							orderId = orderId * -1;
						}
						orderDetails.setOrderId(orderId);
						orderDetails.setTotalPrice(qt.getTotalprice());
						orderDetails.setOrderPlaceTime(LocalDateTime.now());
						orderDetails.setBooksList(list);

					} catch (Exception e) {
						throw new UserException(401, "user funtion");
					}

				}
			});

		});
		userdetails.getOrderBookDetails().add(orderDetails);
		/**
		 * clearing the cart after added to the order list
		 */
		userdetails.getCartBooks().clear();
		try {
			userRepository.save(userdetails);
		} catch (Exception e) {
			throw new UserException(401, "user not found");
		}
		return orderDetails;
	}

	/**
	 * If order is confrim decreasing the numberOfBooks in BookList
	 */
}
