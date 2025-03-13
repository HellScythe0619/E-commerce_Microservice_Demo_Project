package com.example.ecommerce.service;

import com.example.ecommerce.model.*;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CartService cartService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    public Order createOrderFromCart(User user) {
        List<CartItem> cartItems = cartService.getUserCart(user);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("購物車是空的");
        }

        Order order = new Order();
        order.setUser(user);
        order.setStatus("PENDING");
        order.setPaymentStatus("UNPAID");
        order.setCreatedAt(LocalDateTime.now());
        
        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            
            // 檢查庫存
            if (product.getStock() < cartItem.getQuantity()) {
                throw new RuntimeException("產品 " + product.getName() + " 庫存不足");
            }

            // 創建訂單項
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(BigDecimal.valueOf(product.getPrice()));
            orderItems.add(orderItem);

            // 計算總金額
            totalAmount = totalAmount.add(
                BigDecimal.valueOf(product.getPrice()).multiply(BigDecimal.valueOf(cartItem.getQuantity()))
            );

            // 更新庫存
            product.setStock(product.getStock() - cartItem.getQuantity());
            productRepository.save(product);
        }

        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);
        
        Order savedOrder = orderRepository.save(order);
        
        // 清空購物車
        cartService.clearCart(user);
        
        // 發送訂單創建事件到Kafka
        kafkaTemplate.send("order-events", 
            String.format("Order created: %d, Total Amount: %s", 
                savedOrder.getId(), savedOrder.getTotalAmount()));
        
        return savedOrder;
    }
} 