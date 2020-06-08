package com.bridgelabz.bookstore.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Order;
import com.bridgelabz.bookstore.entity.Quantity;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.repository.BookImple;
import com.bridgelabz.bookstore.repository.OrderRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.response.EmailData;
import com.bridgelabz.bookstore.service.IOrderServices;
import com.bridgelabz.bookstore.util.EmailProviderService;
import com.bridgelabz.bookstore.util.JwtGenerator;
import com.bridgelabz.bookstore.util.RandomNumberGenerator;

@Service
public class OrderServiceImp implements IOrderServices {
	@Autowired
	private JwtGenerator generate;

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BookImple bookRepository;
	@Autowired
	private RandomNumberGenerator randomOrderNumber;
	@Autowired
	private EmailProviderService em;
	@Autowired
	private EmailData emailData;

	@Autowired
	OrderRepository orderRepository;

//	@Transactional
//	@Override
//	public Order confrimOrder(String token) {
//		Long id = generate.parseJWT(token);
//		Users userdetails = userRepo.findById(id).orElseThrow(null);
//
//		Order orderDetails = new Order();
//		Random random = new Random();
//		ArrayList<Book> list = new ArrayList<>();
//		ArrayList<Quantity> quantitydetails = new ArrayList<>();
//		ArrayList<String> details = new ArrayList<>();
//		userdetails.getCartBooks().forEach((cart) -> {
//
//			cart.getBooksList().forEach(book -> {
//				long orderId;
//				/**
//				 * If order is confrim decreasing the numberOfBooks in BookList
//				 */
//				for (Quantity qt : cart.getQuantityOfBook()) {
//
//					Long noOfBooks = book.getNoOfBooks() - qt.getQuantityOfBook();
//					book.setNoOfBooks(noOfBooks);
//					Book bb = bookRepository.save(book);
//
//					try {
//						list.add(bb);
//						orderId = random.nextInt(1000000);
//						if (orderId < 0) {
//							orderId = orderId * -1;
//						}
//						quantitydetails.add(qt);
//						orderDetails.setOrderId(orderId);
//						orderDetails.setQuantityOfBooks(quantitydetails);
//						orderDetails.setOrderPlaceTime(LocalDateTime.now());
//						orderDetails.setBooksList(list);
//						details.add("orderId:" + orderId + " " + "BookName:" + book.getBookName() + " " + "Quantity:"
//								+ qt.getQuantityOfBook() + " " + "TotalPrice:" + qt.getTotalprice());
//
//					} catch (Exception e) {
//						throw null;// new UserException();
//					}
//
//				}
//			});
//
//		});
//
//		userdetails.getOrderBookDetails().add(orderDetails);
//
//		String data = "";
//		for (String dt : details) {
//			data += dt + "\n";
//		}


     
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
 						//orderDetails.setOrderPlaceTime(LocalDateTime.now());
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
 			data+=dt+"\n";		
 		}
 		

//        
// 		String body="@"+userdetails.getEmail()+" "+"order details"+" "+data;
//		emailData.setEmail(userdetails.getEmail());
//
//		emailData.setSubject("your Order is succefully placed");
//
//		emailData.setBody(body);
//
//		em.sendMail(emailData.getEmail(), emailData.getSubject(), emailData.getBody());

 		
 		userdetails.getCartBooks().clear();
 		//userRepo.save(userdetails);


		userdetails.getCartBooks().clear();

		try {
			userRepo.save(userdetails);
		} catch (Exception e) {
			throw null; // new UserException();
		}
		return orderDetails;

	}

	@Override
	public boolean confirmBooktoOrder(String token, Long bookId) {
		Long id = generate.parseJWT(token);
		Users userdetails = userRepo.findById(id).orElseThrow(null);

		for (Order order : userdetails.getOrderBookDetails()) {
			boolean notExist = order.getBooksList().stream().noneMatch(books -> books.getBookId().equals(bookId));

			if (!notExist) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	@Transactional
	@Override
	public int getCountOfBooks(String token) {
 		Long id = generate.parseJWT(token);
		int countOfBooks = 0;
		Users userdetails = userRepo.findById(id)
				.orElseThrow(null);

		List<Order> books = userdetails.getOrderBookDetails();
		for (Order cart : books) {
			if (!cart.getBooksList().isEmpty()) {

				countOfBooks++;
			}
		}
		return countOfBooks;
	}
	
	
	
	@Transactional
	@Override
	public List<Order> getOrderList(String token) {
		Long id = generate.parseJWT(token);
		Users userdetails = userRepo.findById(id)
				.orElseThrow(null);

		return userdetails.getOrderBookDetails();

	}

//	@Transactional
//	@Override
//	public List<Order> getOrderList(String token) {
//		Long id = generate.parseJWT(token);
//		Users userdetails = userRepo.findById(id).orElseThrow(null);
//
//		return userdetails.getOrderBookDetails();
//
//	}

	@Transactional
	@Override
	public int changeOrderStatus(String orderStatus, Long orderId) {

		int changedOrderStatus = orderRepository.OrderStatus(orderStatus,orderId);
		return changedOrderStatus;
	}

}
