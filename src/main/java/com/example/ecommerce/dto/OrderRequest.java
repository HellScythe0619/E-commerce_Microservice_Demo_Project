package com.example.ecommerce.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private Long productId;
    private Integer quantity;
} 