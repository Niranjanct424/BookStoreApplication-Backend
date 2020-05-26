package com.bridgelabz.bookstore.response;

import com.bridgelabz.bookstore.entity.BookInformation;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class BookResponse {
	BookInformation book;
	private Object obj;

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	int statusCode;
	String response;
	List<BookInformation> bookList;

	public BookInformation getBook() {
		return book;
	}

	public void setBook(BookInformation book) {
		this.book = book;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public List<BookInformation> getBookList() {
		return bookList;
	}

	public void setBookList(List<BookInformation> bookList) {
		this.bookList = bookList;
	}

	public BookResponse() {

	}

	public BookResponse(String response, List<BookInformation> bookList) {
		super();

		this.response = response;
		this.bookList = bookList;
	}

	public BookResponse(String response, Object obj) {
		super();
		this.obj = obj;

		this.response = response;
	}

	public BookResponse(int statusCode, String response) {
		super();
		this.statusCode = statusCode;
		this.response = response;
	}

}