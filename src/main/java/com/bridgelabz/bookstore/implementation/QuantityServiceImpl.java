package com.bridgelabz.bookstore.implementation;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.QuantityEntity;
import com.bridgelabz.bookstore.repository.BookImple;
import com.bridgelabz.bookstore.repository.QuantityRepository;
import com.bridgelabz.bookstore.service.QuantityService;

@Service
public class QuantityServiceImpl implements QuantityService{

	@Autowired
	private BookImple bookrepo;
	@Autowired
	private QuantityRepository repo;
	


	@Transactional
	@Override
	public void addQuantity(long bookId,int Quantity) 
	{
	Book book=bookrepo.fetchbyId(bookId);
	QuantityEntity en = new QuantityEntity();
	if(book!=null)
	{
		en.setBookquantity(book);
		en.setQuantity(Quantity);
	    repo.create(Quantity,book.getBookId());
       
	    bookrepo.save(book);
	}
	
	}
	
	

	
}
		
		
	


