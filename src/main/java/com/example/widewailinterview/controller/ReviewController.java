package com.example.widewailinterview.controller;

import com.example.widewailinterview.data.review.Review;
import com.example.widewailinterview.repo.ReviewRepository;
import com.example.widewailinterview.service.ReviewService;
import jakarta.validation.constraints.Pattern;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Service
@RequestMapping("/reviews")
@Validated
@Log4j2
public class ReviewController extends BaseController {
    private ReviewService service;
    private static final String UUID_PATTERN = "^[a-fA-F0-9]{8}(-[a-fA-F0-9]{4}){3}-[a-fA-F0-9]{12}$";

    ReviewController(ReviewService service){
        this.service = service;
    }


//    @GetMapping(value = "", produces="application/json")
//    public ResponseEntity<Object> searchReviews() {
//        try {
//            Review result = this.service.getReviewById(id).orElseThrow(() -> new Exception(NOT_FOUND));
//            return handleResponse(result);
//        }catch (Exception e){
//            return handleError(e);
//        }
//    }

    @GetMapping(value = "/{id}", produces="application/json")
    public ResponseEntity<Object> getReviewById(@PathVariable @Pattern(regexp=UUID_PATTERN, message=INVALID_UUID) String id) {
        try {
            Review result = this.service.getReviewById(id).orElseThrow(() -> new Exception(NOT_FOUND));
            return handleResponse(result);
        } catch (Exception e){
            return handleError(e);
        }
    }

    @DeleteMapping(value = "/{id}", produces="application/json")
    public ResponseEntity<Object> deleteReviewById(@PathVariable @Pattern(regexp=UUID_PATTERN, message=INVALID_UUID) String id) {
        try {
            this.service.deleteReviewById(id);
            return handleResponse(null);
        }catch (Exception e){
            return handleError(e);
        }
    }
}
