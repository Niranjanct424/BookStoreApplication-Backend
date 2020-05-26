package com.bridgelabz.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.CustomerInformation;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerInformation, Long> {

	@Query( value = "select * from customer_info where customer_name = :name and customer_phonenumber = :phonenumber", nativeQuery = true)
	CustomerInformation getCustomerbyDetails( String name, long phonenumber);
	
	@Query( value = "select * from customer_info where user_id = :customer_id", nativeQuery = true)
	CustomerInformation getCustomerDetailsbyId( long customer_id);
}
