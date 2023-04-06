package com.example.widewailinterview.service;

import com.example.widewailinterview.component.ReviewRestTemplate;
import com.example.widewailinterview.data.customer.Customer;
import com.example.widewailinterview.data.review.Review;
import com.example.widewailinterview.repo.ReviewRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
@Log4j2
public class ReviewService {
    private final ReviewRepository repo;
    private final ReviewRestTemplate rest;
    private final EntityManager entityManager;

    public ReviewService(ReviewRepository repo, ReviewRestTemplate rest, EntityManager entityManager){
        this.repo = repo;
        this.rest = rest;
        this.entityManager = entityManager;
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

    @Transactional
    public void importData() throws Exception {
        // This task will run only once at application startup
        List<Review> data = rest.getNewApiReviews();
        List<Review> reviews = data.stream().map(r -> {

            Customer newCustomer = r.getCustomer();
            newCustomer.setId(UUID.randomUUID().toString());
            newCustomer = entityManager.merge(newCustomer);

            return new Review (r.getId(), r.getAuthor(), r.getRating(), r.getType(), r.getSource(),
                    r.getContent(), r.getReviewDate(), r.getTags(), newCustomer);
        }).toList();
        reviews.forEach(System.out::println);

        this.repo.saveAll(reviews);
        log.info("successfully retrieved and saved new reviews");
    }

    public List<Review> findByPropertyValue(String author, String rating, String type, String source, String reviewDate, String tag, EntityManager entityManager) {
        Set<Review> reviewsThatMatchSearch = new LinkedHashSet<>();

        if (canConvert(author, String.class)) {
            // Author is convertible to String
            reviewsThatMatchSearch.addAll(this.repo.findByAuthorContainingIgnoreCase(author));
        }
        if (canConvert(rating, Long.class)) {
            // Rating is convertible to Long
            reviewsThatMatchSearch.addAll(this.repo.findByRating(Long.parseLong(rating)));
        }
        if (canConvert(reviewDate, String.class)) {
            // Review date is convertible to String
            reviewsThatMatchSearch.addAll(this.repo.findByReviewDate(reviewDate));
        }
        if (canConvert(type, String.class)) {
            // Author is convertible to String
            reviewsThatMatchSearch.addAll(this.repo.findByTypeContainingIgnoreCase(type));
        }
        if (canConvert(source, String.class)) {
            // Source is convertible to String
            reviewsThatMatchSearch.addAll(this.repo.findBySourceContainingIgnoreCase(source));
        }
        if (canConvert(reviewDate, String.class)) {
            // Review date is convertible to LocalDateTime
            reviewsThatMatchSearch.addAll(this.repo.findByTagsContainingIgnoreCase(tag));
        }


        return new ArrayList<>(reviewsThatMatchSearch);
    }

    private boolean canConvert(String fieldValue, Class<?> fieldType) {
        try {
            if (fieldValue == null) {
                return false;
            } else {
                // Try to convert the string value to the field type
                Object convertedValue = convertStringToType(fieldValue, fieldType);
                return convertedValue != null;
            }
        } catch (Exception e) {
            // Conversion failed
            return false;
        }
    }

    private Object convertStringToType(String value, Class<?> type) {
        if (type == String.class) {
            return value;
        } else if (type == Integer.class || type == int.class) {
            return Integer.parseInt(value);
        } else if (type == Long.class || type == long.class) {
            return Long.parseLong(value);
        } else if (type == LocalDateTime.class) {
            return LocalDateTime.parse(value, DateTimeFormatter.ISO_DATE_TIME);
        } else {
            throw new UnsupportedOperationException("Unsupported field type: " + type);
        }
    }
}
