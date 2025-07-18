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
- `productService`: Manages product
- `orderService`: Handles orders
- `apiGateway`: Routes requests to services
- `serviceRegistry`: Eureka service discovery

How to Run
1. Start `serviceRegistry`
2. Run the `apiGateway`
3. Run the `productService`
4. Run the `orderService`

`Postman documentation link` - https://documenter.getpostman.com/view/33768573/2sB34kCy3Q