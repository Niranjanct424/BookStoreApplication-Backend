package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.entity.Cart;

public interface ICartService {
 List<Cart> addBooktoCart(String token,long bookId);
 
}
