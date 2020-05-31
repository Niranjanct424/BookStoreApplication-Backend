package com.bridgelabz.bookstore.repository.implementation;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.CartItem;
import com.bridgelabz.bookstore.repository.ICartRepository;
@Repository
public class CartRepositoryaImplementation implements ICartRepository {
	@Autowired
	private EntityManager entityManager;
	@Override
	public CartItem save(CartItem cartItem) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(cartItem);
		return cartItem;
	}

}
