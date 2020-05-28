
package com.bridgelabz.bookstore.entity;

import java.time.LocalDateTime;
import java.util.List;

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
		@OneToOne
		private Address Home;
		@OneToOne
		private Address Work;
		@OneToOne
		private Address Others;
		@OneToMany
		@JoinColumn(name="userId")
		private List<Cart> cartBooks;
		
		public long getUserId() {
			return userId;
		}
		public void setUserId(long userId) {
			this.userId = userId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Long getMobileNumber() {
			return mobileNumber;
		}
		public void setMobileNumber(Long mobileNumber) {
			this.mobileNumber = mobileNumber;
		}
		public LocalDateTime getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(LocalDateTime createdDate) {
			this.createdDate = createdDate;
		}
		public boolean isVerified() {
			return isVerified;
		}
		public void setVerified(boolean isVerified) {
			this.isVerified = isVerified;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
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
	
		@Override
		public String toString() {
			return "UserInformation [userId=" + userId + ", name=" + name + ", email=" + email + ", password="
					+ password + ", mobileNumber=" + mobileNumber + ", createdDate=" + createdDate + ", isVerified="
					+ isVerified + ", role=" + role + ", Home=" + Home + ", Work=" + Work + ", Others=" + Others
					+ ", cartbooks=" +cartBooks+ "]";
		}	
		
		
}
