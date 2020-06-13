package com.bridgelabz.bookstore.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.CartItem;
import com.bridgelabz.bookstore.entity.Order;
import com.bridgelabz.bookstore.entity.Quantity;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.exception.UserException;
import com.bridgelabz.bookstore.repository.BookImple;
import com.bridgelabz.bookstore.repository.OrderRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.response.EmailData;
import com.bridgelabz.bookstore.service.ICartService;
import com.bridgelabz.bookstore.service.IOrderServices;
import com.bridgelabz.bookstore.util.EmailProviderService;
import com.bridgelabz.bookstore.util.JwtGenerator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImp implements IOrderServices {
	@Autowired
	private JwtGenerator generate;
	
	@Autowired
	private ICartService cartservice;

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BookImple bookRepository;

	@Autowired
	private EmailProviderService em;
	@Autowired
	private EmailData emailData;

	@Autowired
	OrderRepository orderRepository;

	public Order placeOrder(String token, Long bookId, Long addressId) {
		Long id = generate.parseJWT(token);
		Users userdetails = userRepo.findById(id).get();
		if (userdetails != null) {
			Order orderDetails = new Order();
			Random random = new Random();
			ArrayList<Book> list = new ArrayList<>();
			ArrayList<Quantity> quantitydetails = new ArrayList<>();
			ArrayList<String> details = new ArrayList<>();
			/*
			 * getting user cartbooks to order as a list userdetail.getcatbook return type
			 * is List
			 */
			List<CartItem> cartbook = userdetails.getCartBooks();
			log.info("------------------------------cartbook------1--"+cartbook);
			/*
			 * now iterating specific cart book
			 */
			List<Book> userCartbooks = null;
			for (CartItem userCart : cartbook) {
				// select specific cart book to order
				log.info("-----------------------usercart-------------2--"+userCart);
				userCartbooks = userCart.getBooksList();// getting books from cart
				for (Book book : userCartbooks) {
					log.info("-----------------------book---------------"+book);
					if (book.getBookId().equals(bookId))// specific book to order @path taking input of this api
					{
						/*
						 * decreasing the quantity of book
						 */
						long orderId;
						for (Quantity bookquantity : userCart.getQuantityOfBook()) {
							// List q=userCart.getQuantityOfBook();
							log.info("-----------------------QUantity-------------2--"+bookquantity);
							Long noOfBooks = book.getNoOfBooks() - bookquantity.getQuantityOfBook();
							book.setNoOfBooks(noOfBooks);
							Book bb = bookRepository.save(book);
							try {
								list.add(bb);
								orderId = random.nextInt(1000000);
								if (orderId < 0) {
									orderId = orderId * -1;
								}
								double totalprice = book.getPrice() * (bookquantity.getQuantityOfBook());
								log.info("-----------------------QUantity-------------2--"+bookquantity.getQuantityOfBook());
								orderDetails.setTotalPrice(totalprice);
								quantitydetails.add(bookquantity);
								orderDetails.setOrderId(orderId);
								orderDetails.setQuantityOfBooks(quantitydetails);
								orderDetails.setOrderPlacedTime(LocalDateTime.now());
								orderDetails.setOrderStatus("Pending");
								orderDetails.setAddressId(addressId);
								orderDetails.setBooksList(list);
								details.add("orderId:" + orderId + "\n" + "BookName:" + book.getBookName() + "\n"
										+ "Quantity:" + bookquantity.getQuantityOfBook() + "\n" + "TotalPrice:"
										+ bookquantity.getTotalprice());
							} catch (Exception e) {
								throw new UserException("order Failed");
							}
						}//quantity for
					} // if condition checks id of the books

				} // book iteration--for
			} // cart iterate--for
			userdetails.getOrderBookDetails().add(orderDetails);
			String data = "";
			for(String dt:details) {
				data=data+dt+"\n";	
				log.info("\n "+dt);
			}    
	 		String body="@"+userdetails.getEmail()+" \n"+"order details"+" \n"+data+"\n"+"please rate us below link"+"\n"
	 		+"http://localhost:8081/books/ratingreview";
			emailData.setEmail(userdetails.getEmail());
	
			emailData.setSubject("your Order is succefully placed");
	
			emailData.setBody(body);
	
			em.sendMail(emailData.getEmail(), emailData.getSubject(), emailData.getBody());
			/*
			 * remove specific book from the cart........
			 */

			if (cartservice.removeBooksFromCart(token, bookId)) {
				userRepo.save(userdetails);
				return orderDetails;
			}
		}

		throw new UserException("user not found ");
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
		long id = generate.parseJWT(token);
		Users userdetails = userRepo.findById(id)
				.orElseThrow(null);

		return userdetails.getOrderBookDetails();

	}



	@Transactional
	@Override
	public int changeOrderStatus(String status,long orderId) {

		int changedOrderStatus = orderRepository.OrderStatusdefault(status,orderId);
		return changedOrderStatus;
	}
	




	
	@Override
	public List<Order> getInProgressOrders() {
		List<Order> inProgressOrder = orderRepository.getInProgressOrder();
		return inProgressOrder;
	}

	@Override
	public List<Order> getallOrders() {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	




	
}
