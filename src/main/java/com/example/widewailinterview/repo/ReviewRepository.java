package com.example.widewailinterview.repo;

import com.example.widewailinterview.data.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

    Optional<Review> findById(String id);
    void deleteById(String id);
    List<Review> findByAuthorContainingIgnoreCase(String author);
    List<Review> findByRating(Long rating);
    List<Review> findByTypeContainingIgnoreCase(String type);
    List<Review> findBySourceContainingIgnoreCase(String source);
    List<Review> findByReviewDate(String reviewDate);
    List<Review> findByTagsContainingIgnoreCase(String tag);
}

