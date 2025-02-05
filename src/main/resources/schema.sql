-- 產品表
CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    price DOUBLE,
    stock INTEGER
);

-- 訂單表
CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT,
    quantity INTEGER,
    status VARCHAR(50),
    FOREIGN KEY (product_id) REFERENCES product(id)
); 