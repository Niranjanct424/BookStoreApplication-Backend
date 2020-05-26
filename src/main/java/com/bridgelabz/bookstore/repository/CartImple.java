package com.bridgelabz.bookstore.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.CartInformation;

@Repository
public interface CartImple extends JpaRepository<CartInformation, Long> {
	@Modifying
	@Query("DELETE FROM CartInformation WHERE book_id=:id")
	void deletebyId(Long id);

	@Query("from CartInformation where book_id=:id ")
	CartInformation fetchbyId(Long id);

	@Modifying
	@Transactional
	@Query(value = " update cartinfo set quantity=:qunt where book_id =:id ", nativeQuery = true)
	void verifyTheUser(final int qunt, final Long id);

	@Query(value = "select * from cartinfo where cart_id=:id ", nativeQuery = true)
	CartInformation findCartbyId(long id);
	
	@Query(value = "select * from cartinfo where cart_id=:id and book_id = :bookId ", nativeQuery = true)
	CartInformation cartbyId(long id, long bookId);
	
	
	
}