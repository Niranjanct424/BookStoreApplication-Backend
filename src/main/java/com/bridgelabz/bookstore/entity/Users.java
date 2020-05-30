
package com.bridgelabz.bookstore.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Users {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long userId;
		private String name;
		private String email;
		private String password;
		private Long mobileNumber;
		private LocalDateTime createdDate;
		private boolean isVerified;
		private String role;
		
		@OneToMany(cascade = CascadeType.ALL, targetEntity = Address.class)
		@JoinColumn(name = "userId")
		private List<Address> address;
		
		@OneToMany
		@JoinColumn(name="userId")
		private List<Cart> cartBooks;// refactored dont delete this 

		@Override
		public String toString() {
			return "Users [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
					+ ", mobileNumber=" + mobileNumber + ", createdDate=" + createdDate + ", isVerified=" + isVerified
					+ ", role=" + role + ", address=" + address + ", cartBooks=" + cartBooks + "]";
		}
		
	
		
		
}
