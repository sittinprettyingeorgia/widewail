package com.example.widewailinterview.data.review;

import com.example.widewailinterview.data.customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;


@Entity
@Table(name = "Reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String author;
    private Long rating;
    private String type;
    private String source;
    @Column(length = 1000)
    private String content;
    private String reviewDate;
    private String tags;

    @JsonIgnoreProperties("reviews")
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="customerId")
    private Customer customer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(rating, review.rating) &&
                Objects.equals(author, review.author) &&
                Objects.equals(content, review.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, rating, content);
    }
}
