package com.example.ecommerce.service;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public Order createOrder(Long productId, Integer quantity) {
        // 檢查產品是否存在
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        
        // 檢查庫存
        if (product.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock for product: " + productId);
        }

        Order order = new Order();
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setStatus("PENDING");
        
        Order savedOrder = orderRepository.save(order);
        
        // 更新庫存
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
        
        // 發送訂單創建事件到Kafka
        try {
            String message = String.format("Order created: %d, Product: %d, Quantity: %d",
                savedOrder.getId(), productId, quantity);
            kafkaTemplate.send("order-events", message)
                .addCallback(
                    result -> {
                        System.out.println("訊息發送成功: " + message);
                    },
                    ex -> {
                        System.err.println("訊息發送失敗: " + ex.getMessage());
                        // 這裡可以添加重試邏輯或其他錯誤處理
                    }
                );
        } catch (Exception e) {
            System.err.println("Kafka發送異常: " + e.getMessage());
            // 可以選擇是否要拋出異常或者只是記錄日誌
            // throw new RuntimeException("訂單消息發送失敗", e);
        }
        
        return savedOrder;
    }
} 