package com.example.ecommerce.model;

import javax.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private User user;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> items;
    
    private BigDecimal totalAmount;
    private String status;
    private String paymentStatus;
    private LocalDateTime createdAt;
    private String paymentMethod;
    private String paymentTransactionId;
} 