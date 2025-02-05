package com.example.ecommerce.controller;

import com.example.ecommerce.dto.OrderRequest;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.OrderService;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EcommerceController {
    private final ProductService productService;
    private final OrderService orderService;

    @PostMapping("/orders")
    public Order createOrder(@RequestBody OrderRequest request) {
        return orderService.createOrder(request.getProductId(), request.getQuantity());
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }
} 