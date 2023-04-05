package com.example.widewailinterview.service;

import com.example.widewailinterview.data.review.Review;
import com.example.widewailinterview.repo.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {
    private ReviewRepository repo;

    public ReviewService(ReviewRepository repo){

        this.repo = repo;
    }

    public Optional<Review> getReviewById(String id){
        return this.repo.findById(id);
    }

    public void deleteReviewById(String id){
        this.repo.deleteById(id);
    }

    public Optional<Review> searchReviews(String id){
        return this.repo.findById(id);
    }
}
