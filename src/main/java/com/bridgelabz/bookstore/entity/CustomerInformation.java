package com.bridgelabz.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customerInfo")
public class CustomerInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	@Column(name = " customer_name")
	private String Name;
	
	@Column(name = "customer_phonenumber")
	private long Phonenumber;
	@OneToOne
	private Address Home;
	@OneToOne
	private Address Work;
	@OneToOne
	private Address Others;
	@OneToOne( mappedBy = "userId")
	private CartInformation cartId;
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public long getPhonenumber() {
		return Phonenumber;
	}

	public void setPhonenumber(long phonenumber) {
		Phonenumber = phonenumber;
	}

	public Address getHome() {
		return Home;
	}

	public void setHome(Address home) {
		Home = home;
	}

	public Address getWork() {
		return Work;
	}

	public void setWork(Address work) {
		Work = work;
	}

	public Address getOthers() {
		return Others;
	}

	public void setOthers(Address others) {
		Others = others;
	}

	public CartInformation getCartId() {
		return cartId;
	}

	public void setCartId(CartInformation cartId) {
		this.cartId = cartId;
	}

}