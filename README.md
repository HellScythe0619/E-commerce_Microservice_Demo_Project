# E-commerce Microservice Demo

[English](#english) | [中文](#chinese)

<a name="english"></a>
# English Version

This is a demo e-commerce microservice project built with Spring Boot, demonstrating basic order processing and product inventory management.

## Tech Stack

- Spring Boot 2.7.18
- Spring Data JPA
- Spring Data Redis (Caching)
- Apache Kafka (Message Queue)
- PostgreSQL Database
- Spring Security
- JWT Authentication
- Lombok
- Maven

## Features

- Product Management
  - Product information query
  - Redis caching support
- Shopping Cart
  - Add/Remove items
  - View cart
- Order Processing
  - Create orders from cart
  - Inventory check
  - Kafka event notification
- Payment Integration
  - Basic payment processing
  - Transaction tracking

## Requirements

- JDK 1.8+
- Maven 3.6+
- Redis Server
- Kafka 2.13-3.6.1
- ZooKeeper
- PostgreSQL

## Installation

1. Clone the project:
```bash
git clone [repository-url]
```

2. Configure Kafka:
   - Create data directories:
   ```bash
   mkdir %KAFKA_HOME%/zookeeper-data
   mkdir %KAFKA_HOME%/kafka-logs
   ```
   - Start ZooKeeper:
   ```bash
   .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
   ```
   - Start Kafka:
   ```bash
   .\bin\windows\kafka-server-start.bat .\config\server.properties
   ```

3. Start Redis Server

4. Configure PostgreSQL:
   - Create database named 'ecommerce'
   - Update application.yml with your database credentials

5. Run the application:
```bash
mvn spring-boot:run
```

## API Endpoints

### Product API
- Get product:
```bash
GET /api/products/{id}
```

### Cart API
- Add to cart:
```bash
POST /api/cart?userId={userId}
Content-Type: application/json
{
    "productId": 1,
    "quantity": 2
}
```
- View cart:
```bash
GET /api/cart/{userId}
```

### Order API
- Create order:
```bash
POST /api/orders/{userId}
```

## Test Data

The system will automatically load the following test data:
- iPhone 14 (ID: 1, Price: $999.99, Stock: 100)
- MacBook Pro (ID: 2, Price: $1999.99, Stock: 50)

## Development Notes

1. Redis is used for product information caching
2. Kafka handles order events
3. JPA is used for database operations
4. Basic inventory management logic implemented
5. Spring Security with JWT authentication

---

<a name="chinese"></a>
# 中文版本

這是一個使用 Spring Boot 建立的電子商務微服務示例專案，展示了基本的訂單處理和產品庫存管理功能。

## 相關技術

- Spring Boot 2.7.18
- Spring Data JPA
- Spring Data Redis (快取)
- Apache Kafka (消息隊列)
- PostgreSQL 數據庫
- Spring Security
- JWT 認證
- Lombok
- Maven

## 功能特點

- 產品管理
  - 產品信息查詢
  - Redis 快取支持
- 購物車
  - 添加/移除商品
  - 查看購物車
- 訂單處理
  - 從購物車創建訂單
  - 庫存檢查
  - Kafka 事件通知
- 支付整合
  - 基本支付處理
  - 交易追蹤

## 環境要求

- JDK 1.8+
- Maven 3.6+
- Redis Server
- Kafka (任一版本，建議 3.6.1)
- ZooKeeper (Kafka 內建)
- PostgreSQL

## 安裝步驟

1. 克隆專案：
```bash
git clone [repository-url]
```

2. 配置 Kafka：
   - 創建數據目錄： (資料夾路徑請自行修改，例如 D:/kafka_2.13-3.6.1/)
   ```bash
   mkdir %KAFKA_HOME%/zookeeper-data
   mkdir %KAFKA_HOME%/kafka-logs
   ```
   - 至 %KAFKA_HOME% 資料夾 啟動 ZooKeeper：
   ```bash
   .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
   ```
   - 至 %KAFKA_HOME% 資料夾 啟動 Kafka：
   ```bash
   .\bin\windows\kafka-server-start.bat .\config\server.properties
   ```

3. 啟動 Redis Server

4. 配置 PostgreSQL：
   - 創建名為 'ecommerce' 的數據庫
   - 在 application.yml 中更新數據庫連接信息

5. 運行應用：
```bash
mvn spring-boot:run
```

## API 端點

### 產品 API
- 獲取產品：
```bash
GET /api/products/{id}
```

### 購物車 API
- 添加到購物車：
```bash
POST /api/cart?userId={userId}
Content-Type: application/json
{
    "productId": 1,
    "quantity": 2
}
```
- 查看購物車：
```bash
GET /api/cart/{userId}
```

### 訂單 API
- 創建訂單：
```bash
POST /api/orders/{userId}
```

## Database
專案使用 H2 Database，可通過以下方式訪問 H2 控制台：
- URL: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:ecommercedb
- Username: sa
- Password: [空白 不用填寫]

## 測試數據

系統啟動時會自動載入以下測試數據：
- iPhone 14 (ID: 1, 價格: $999.99, 庫存: 100)
- MacBook Pro (ID: 2, 價格: $1999.99, 庫存: 50)

## 開發說明

1. Redis 用於快取產品信息
2. Kafka 用於處理訂單事件
3. 使用 JPA 進行數據庫操作
4. 實現了基本的庫存管理邏輯
5. Spring Security 配合 JWT 實現認證

## 注意事項

1. 確保 Kafka 和 ZooKeeper 正確配置並運行
2. Redis 服務必須可用
3. 所有外部服務（Kafka、Redis）必須在應用啟動前運行
4. PostgreSQL 數據庫必須已創建
5. 所有外部服務必須在應用啟動前運行