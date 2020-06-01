package com.bridgelabz.bookstore.dto;

import lombok.Data;

@Data
public class CartDto {

	private Long quantityId;
	private Long quantityOfBook;
	private Double eachPrice;

	//@ApiModelProperty(notes = "The TotalPrice of the Book", required = true)// for swagger property mention...
}
