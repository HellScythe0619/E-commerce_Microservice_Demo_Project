package com.example.ecommerce.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private User user;
    
    @ManyToOne
    private Product product;
    
    private Integer quantity;
} 