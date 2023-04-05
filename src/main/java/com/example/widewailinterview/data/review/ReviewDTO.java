package com.example.widewailinterview.data.review;

import com.example.widewailinterview.data.paging.Paging;
import lombok.Data;

import java.util.List;

@Data
public class ReviewDTO {
    private Paging paging;
    private List<Review> reviews;
}
