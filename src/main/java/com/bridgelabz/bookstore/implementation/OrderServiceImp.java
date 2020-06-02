package com.bridgelabz.bookstore.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Order;
import com.bridgelabz.bookstore.entity.Quantity;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.repository.BookImple;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.service.IOrderServices;
import com.bridgelabz.bookstore.util.JwtGenerator;
import com.bridgelabz.bookstore.util.RandomNumberGenerator;
@Service
public class OrderServiceImp implements IOrderServices{
	@Autowired
	private JwtGenerator generate;
     @Autowired
     private UserRepository userRepo;
     @Autowired
 	private BookImple bookRepository;
     @Autowired
     private RandomNumberGenerator randomNumber;  
     @Transactional
 	@Override
 	public Order confrimOrder(String token) {
 		Long id = generate.parseJWT(token);
 		Users userdetails = userRepo.findById(id)
 				.orElseThrow(null);

 		Order orderDetails = new Order();
 		Random random = new Random();
 		ArrayList<Book> list = new ArrayList<>();
 		ArrayList<Quantity> quantitydetails = new ArrayList<>();
 		ArrayList<String> details = new ArrayList<>();
 		userdetails.getCartBooks().forEach((cart) -> {

 			cart.getBooksList().forEach(book -> {
 				long orderId;
 				/**
 				 * If order is confrim decreasing the numberOfBooks in BookList
 				 */
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
 						details.add("orderId:"+orderId+" "+"BookName:"+book.getBookName()+
 					    " "+"Quantity:"+qt.getQuantityOfBook()+" "+"TotalPrice:"+qt.getTotalprice());
                         
 					} catch (Exception e) {
 						throw  null ;//new UserException();
 					}
 
 				}
 			});

 		});
 		
 		userdetails.getOrderBookDetails().add(orderDetails);
 		
 		String data = "";
 		for(String dt:details) {
 			data+=dt+"\n"+"=========>"+"\n";		
 		}
 		
 		this.sendMail(userdetails, data);
 		
 		userdetails.getCartBooks().clear();

 		try {
 			userRepo.save(userdetails);
 		} catch (Exception e) {
 			throw null ; // new UserException();
 		}
 		return orderDetails;

 	}


}
