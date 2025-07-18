 Ecommerce Microservices Project

This is a simple microservices ecommerce system built with Spring Boot

Stack
- Java 21
- Spring Boot 3
- Spring WebFlux
- Spring Cloud Gateway
- Eureka Server
- MapStruct
- Lombok


Services
- `productService`: Manages product data
- `orderService`: Handles orders
- `apiGateway`: Routes requests to services
- `serviceRegistry`: Eureka service discovery

How to Run
1. Start `serviceRegistry`
2. Run the `apiGateway`
3. Run the `productService`
4. Run the `orderService`