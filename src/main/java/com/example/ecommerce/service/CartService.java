package com.example.ecommerce.service;

import com.example.ecommerce.model.*;
import com.example.ecommerce.repository.CartItemRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartItemRepository cartItemRepository;
    private final ProductService productService;
    
    public CartItem addToCart(User user, Long productId, Integer quantity) {
        Product product = productService.getProduct(productId);
        
        CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product)
            .orElse(new CartItem());
            
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(cartItem.getQuantity() == null ? 
            quantity : cartItem.getQuantity() + quantity);
            
        return cartItemRepository.save(cartItem);
    }
    
    public List<CartItem> getUserCart(User user) {
        return cartItemRepository.findByUser(user);
    }
    
    public void clearCart(User user) {
        cartItemRepository.deleteByUser(user);
    }
} 