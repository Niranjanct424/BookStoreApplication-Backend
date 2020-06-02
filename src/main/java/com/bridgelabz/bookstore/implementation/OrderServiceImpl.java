package com.bridgelabz.bookstore.implementation;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Order;
import com.bridgelabz.bookstore.entity.Quantity;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.exception.AdminNotFoundException;
import com.bridgelabz.bookstore.repository.BookImple;
import com.bridgelabz.bookstore.repository.IUserRepository;
import com.bridgelabz.bookstore.service.IOrderService;
import com.bridgelabz.bookstore.util.JwtGenerator;

public class OrderServiceImpl implements IOrderService{
	
	@Autowired
	private IUserRepository repository;
	
	@Autowired
	private BookImple bookRepository;
	
	@Autowired
	private JwtGenerator generate;

	@Transactional
	@Override
	public Order getOrderConfrim(String token) {
		Long id = null;
		try {
			id = generate.parseJWT(token);
		} catch (JWTVerificationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Users userdetails = repository.getUserById(id);

		Order orderDetails = new Order();
		Random random = new Random();
		ArrayList<Book> list = new ArrayList<>();
		ArrayList<Quantity> quantitydetails = new ArrayList<>();

			userdetails.getCartBooks().forEach((cart) -> {

			cart.getBooksList().forEach(book -> {
				long orderId;
	
				for (Quantity quantity : cart.getQuantityOfBook()) {

					int noOfBooks = (int) (book.getQuantity() - quantity.getQuantityOfBook());
					book.setQuantity(noOfBooks);
					Book b = bookRepository.save(book);

					try {
						list.add(b);
						orderId = random.nextInt(1000000);
						if (orderId < 0) {
							orderId = orderId * -1;
						}
						quantitydetails.add(quantity);
						orderDetails.setOrderId(orderId);
						orderDetails.setOrderPlaceTime(LocalDateTime.now());
						orderDetails.setBooksList(list);

					} catch (Exception e) {
						throw new AdminNotFoundException("Admin Not Found");
					}

				}
			});

		});
		userdetails.getOrders().add(orderDetails);
	
		return orderDetails;

	}

}
