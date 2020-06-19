package com.bridgelabz.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.ReviewAndRating;

@Repository
public interface ReviewRatingRepository extends JpaRepository<ReviewAndRating, Long>{
	
	@Query( value = "select * from review_and_rating where ", nativeQuery = true)
    List<ReviewAndRating> getreviews(Long id);
	
}
