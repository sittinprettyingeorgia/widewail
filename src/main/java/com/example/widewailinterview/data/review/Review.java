package com.example.widewailinterview.data.review;

import com.example.widewailinterview.data.customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String author;
    private Long rating;
    private String type;
    private String source;
    private String content;
    private String reviewDate;
    private String tags;

    @JsonIgnoreProperties("reviews")
    @ManyToOne
    @JoinColumn(name="customerId")
    private Customer customer;
}
