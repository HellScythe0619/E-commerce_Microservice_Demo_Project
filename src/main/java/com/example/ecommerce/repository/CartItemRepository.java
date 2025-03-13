package com.example.ecommerce.repository;

import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByUserAndProduct(User user, Product product);
    List<CartItem> findByUser(User user);
    void deleteByUser(User user);
} 