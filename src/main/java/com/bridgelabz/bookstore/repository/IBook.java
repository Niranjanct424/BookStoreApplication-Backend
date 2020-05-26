package com.bridgelabz.bookstore.repository;

import java.util.List;

import com.bridgelabz.bookstore.entity.BookInformation;

public interface IBook {

	BookInformation save(BookInformation bookinformation);

	List<BookInformation> getUsers();

}
