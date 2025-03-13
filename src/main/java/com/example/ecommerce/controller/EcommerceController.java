package com.example.ecommerce.controller;

import com.example.ecommerce.dto.OrderRequest;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.UserService;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EcommerceController {
    private final ProductService productService;
    private final OrderService orderService;
    private final CartService cartService;
    private final UserService userService;

    @PostMapping("/cart")
    public CartItem addToCart(@RequestParam Long userId, @RequestBody OrderRequest request) {
        User user = userService.findById(userId);
        return cartService.addToCart(user, request.getProductId(), request.getQuantity());
    }

    @GetMapping("/cart/{userId}")
    public List<CartItem> getCart(@PathVariable Long userId) {
        User user = userService.findById(userId);
        return cartService.getUserCart(user);
    }

    @PostMapping("/orders/{userId}")
    public Order createOrder(@PathVariable Long userId) {
        User user = userService.findById(userId);
        return orderService.createOrderFromCart(user);
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }
} 