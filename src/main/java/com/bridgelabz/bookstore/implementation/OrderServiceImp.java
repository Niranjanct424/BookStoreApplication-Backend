package com.bridgelabz.bookstore.implementation;

import java.util.ArrayList;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Order;
import com.bridgelabz.bookstore.entity.Quantity;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.service.IOrderServices;
import com.bridgelabz.bookstore.util.RandomNumberGenerator;
@Service
public class OrderServiceImp implements IOrderServices{
     @Autowired
     private UserRepository userRepo;
     @Autowired
     private RandomNumberGenerator randomNumber;
		@Transactional
		@Override
		public Order confrimOrder(String token) {
			Long id = jwt.decodeToken(token);
			Users userdetails = userRepo.findById(id)
					.orElseThrow(null);

			Order orderDetails = new Order();
			//Random random = new Random();
			ArrayList<Book> list = new ArrayList<>();
			ArrayList<Quantity> quantitydetails = new ArrayList<>();

			userdetails.getCartBooks().forEach((cart) -> {

				cart.getBooksList().forEach(book -> {
					long orderId;
					
					for (Quantity qt : cart.getQuantityOfBook()) {

						Long noOfBooks = book.getNoOfBooks() - qt.getQuantityOfBook();
						book.setNoOfBooks(noOfBooks);
						Book bb = bookRepository.save(book);

						try {
							list.add(bb);
							orderId = random.nextInt(1000000);
							if (orderId < 0) {
								orderId = orderId * -1;
							}
							quantitydetails.add(qt);
							orderDetails.setOrderId(orderId);
							orderDetails.setQuantityOfBooks(quantitydetails);
							orderDetails.setOrderPlaceTime(LocalDateTime.now());
							orderDetails.setBooksList(list);

						} catch (Exception e) {
							throw new UserException(401, env.getProperty("701"));
						}

					}
				});
	}

}
