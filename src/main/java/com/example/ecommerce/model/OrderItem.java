package com.example.ecommerce.model;

import javax.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Order order;
    
    @ManyToOne
    private Product product;
    
    private Integer quantity;
    private BigDecimal price;
} 