package com.example.widewailinterview.repo;

import com.example.widewailinterview.data.review.Review;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends PagingAndSortingRepository<Review, String> {
    Optional<Review> findById(String id);
    void deleteById(String id);
}
