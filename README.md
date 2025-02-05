# E-commerce Microservice Demo

這是一個使用 Spring Boot 建立的電子商務微服務示例專案，展示了基本的訂單處理和產品庫存管理功能。

## 相關技術

- Spring Boot 2.7.18
- Spring Data JPA
- Spring Data Redis (快取)
- Apache Kafka (消息隊列)
- H2 Database (內存數據庫)
- Lombok
- Maven

## 功能特點

- 產品管理
  - 產品信息查詢
  - Redis 快取支持
- 訂單處理
  - 創建訂單
  - 庫存檢查
  - Kafka 事件通知

## 環境要求

- JDK 1.8+
- Maven 3.6+
- Redis Server
- Kafka (任一版本，建議 3.6.1)
- ZooKeeper (Kafka 內建)

## 安裝步驟

1. Clone 專案：
   ```bash
   git clone https://github.com/your-repo/ecommerce-microservice.git
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

4. 運行應用：
   ```bash
   mvn spring-boot:run
   ```

## API 端點

### 產品 API
- 獲取產品信息：
```bash
POST /api/orders
Content-Type: application/json
{
    "productId": 1,
    "quantity": 2
}
```

## 數據庫

專案使用 H2 內存數據庫，可通過以下方式訪問 H2 控制台：
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

## 注意事項

1. 確保 Kafka 和 ZooKeeper 正確配置並運行
2. Redis 服務必須可用
3. 所有外部服務（Kafka、Redis）必須在應用啟動前運行