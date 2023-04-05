package com.example.widewailinterview.data.paging;

import lombok.Data;

@Data
public class Paging {
    private long page;
    private long size;
    private long totalPages;
}
