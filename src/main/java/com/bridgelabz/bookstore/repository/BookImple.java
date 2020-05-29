package com.bridgelabz.bookstore.repository;

import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.Book;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
@Repository
public interface BookImple extends JpaRepository<Book, Long> {
	
	@Query("from Book where book_id=:id")
	Book fetchbyId(Long id);
	
	@Query( value = "select * from book", nativeQuery = true)
    List<Book> findAllPage(Pageable pageable);
	
	@Query( value = "select * from bookinfo", nativeQuery = true)
    List<Book> getAllBooks();

	@Query("from Book where book_id=:id ")
	List<Book> fetchbyIdList(Long id);
	
	@Modifying
	@Query("delete from Book where book_id=:id")
	void deleteByBookId(long id);
	
	@Query( value = "select * from bookinfo where is_approved=true", nativeQuery = true)
    List<Book> getAllApprovedBooks();
	

	
	@Modifying
	@Query("update from BookInformation set status=:status where book_id=:id")
	int updateBookStatusByBookId(String status,long id);

	@Query( value = "select * from bookinfo where status='approved' or status='OnHold'", nativeQuery = true)
	List<Book> getAllApprovedOnHoldBooks();
	
	@Query( value = "select * from bookinfo where status='rejected'", nativeQuery = true)
	List<Book> getAllRejectedBooks();

}