package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.entity.CartItem;

public interface ICartService {
 List<CartItem> addBooktoCart(String token,long bookId);
 
}
