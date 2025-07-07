# ⚡ HFT Order Simulator – Spring Boot Backend 

This is a simple backend microservice to simulate order creation and management for a High-Frequency Trading (HFT) system. It is designed for internal testing of trading pipelines. Data is stored in-memory, and the system is built following Clean Architecture and SOLID principles.

---

## ✨ Features

- Create new trading orders (BUY/SELL)
- Retrieve all orders or a specific order by ID
- Cancel orders (only if status is PENDING)
- Simulate random execution of pending orders
- In-memory data storage (no external DB required)
- Clean structure, proper exception handling, and logging


---

## 📁 Project Structure


```
order-demo/
├── src/main/java/com/equix/
│   │  │              └── order_demo/
│   │  │                  ├── OrderDemoApplication.java
│   │  │                  ├── config/
│   │  │                  │   ├── SecurityConfig.java
│   │  │                  │   └── SwaggerConfig.java
│   │  │                  ├── controller/
│   │  │                  │   ├── OrderController.java
│   │  │                  │   ├── advice/
│   │  │                  │   │   └── GlobalExceptionHandler.java
│   │  │                  │   └── dto/
│   │  │                  │       └── req/
│   │  │                  │           └── CreateOrderRequest.java
│   │  │                  ├── exception/
│   │  │                  │   ├── InvalidOrderStatusException.java
│   │  │                  │   └── OrderNotFoundException.java
│   │  │                  ├── model/
│   │  │                  │   ├── Order.java
│   │  │                  │   └── enums/
│   │  │                  │       ├── OrderSide.java
│   │  │                  │       └── OrderStatus.java
│   │  │                  ├── repository/
│   │  │                  │   ├── OrderRepository.java
│   │  │                  │   └── impl/
│   │  │                  │       └── InMemoryOrderRepository.java
│   │  │                  └── service/
│   │  │                      └── OrderService.java
│   │  └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/java/com/equix/order_demo/
│                           ├── OrderDemoApplicationTests.java
│                           └── OrderServiceTest.java

```


---

## 🛠️ How to Run

### 1. Clone the repository

```bash
git clone https://github.com/Tuandq98/HFT-Order-Simulator.git
cd HFT-Order-Simulator
```
### 2. Run the application
Option 1: Run with Maven
```bash
mvn spring-boot:run
```

Option 2: Build JAR and run
```bash
mvn clean package
java -jar target/order-demo-0.0.1-SNAPSHOT.jar
```

**Note:** You can get/update the default account and password in the application.properties file (admin/admin123).

## ✅ Running Tests
```bash
mvn test
```
Includes service layer unit tests for:
- Order creation
- Order cancellation (valid/invalid status)
- Simulating executions
- Fetching order(s)

## 🌐 Example API Usage

Please visit http://localhost:8080/swagger-ui/index.html to view and try the api list.

```bash
http://localhost:8080/swagger-ui/index.html
```







