package com.bridgelabz.bookstore.repository;

import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.entity.CartItem;
@Service
public interface ICartRepository {
	CartItem save(CartItem cartItem);
}
