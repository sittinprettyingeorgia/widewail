package com.example.widewailinterview.data.customer;

import com.example.widewailinterview.data.review.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "Customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    private String companyName;

    public List<Review> getReviews() {
        return Optional.ofNullable(reviews).orElseGet(ArrayList::new);
    }
}
