# 🚀 SaaS Dashboard - Spring Boot API

Backend service built with **Spring Boot** that implements a shared business domain within a multi-implementation SaaS architecture.

This project is part of a system where the same domain is developed using different backend technologies, demonstrating technology-agnostic design.

---

## 🧠 Concept

This repository represents the **Java (Spring Boot)** implementation of a shared business domain.

The same domain is also implemented using:

- Node.js (TypeScript)
- Java (Spring Boot)

Both implementations:

- Share the same domain rules  
- Expose similar APIs  
- Can be used interchangeably by the frontend  

This approach demonstrates how business logic can remain consistent regardless of the underlying technology.

---

## 🎯 Purpose

This service handles core backend responsibilities, including:

- User and role management  
- Organizational hierarchy (manager relationships)  
- Data aggregation for analytics dashboards  
- Business rule enforcement  

It provides structured data for a frontend dashboard and integrates with a forecasting microservice.

---

## 🔄 System Context

```bash
        Frontend (React)
                ↓
    Backend (Spring Boot / Node.js)
           ↓             ↓
     Database       Redis Pub/Sub
           ↘
        FastAPI (Forecast Service)
```

---

## 🔗 Related Repositories

- Frontend → https://github.com/ing-agarcia/react-saas-dashboard-analytics  
- Node.js Implementation → https://github.com/ing-agarcia/saas-dashboard-api-node  
- Forecast Microservice → https://github.com/ing-agarcia/fastapi-forecast  

---

## 📦 Installation

```bash
git clone <repo>
cd springboot-saas-api
./mvnw clean install

./mvnw spring-boot:run
```

---

## 🚀 Features

- User Management (CRUD)
- Role & Group Management
- Hierarchical relationships (Manager → User)
- Dashboard data aggregation
- RESTful API design
- Clean layered architecture (Controller / Service / Repository)
- Redis Pub/Sub event communication
- Typed domain events
- Event-driven architecture patterns

---

## 🧩 Architecture Approach

This project follows:

- Layered architecture
- Separation of concerns
- Domain-driven design (DDD) principles
- Event-driven communication patterns
- Infrastructure abstraction for messaging systems

The application separates:

- Domain models
- Persistence entities
- DTO projections
- Messaging/event contracts

It mirrors the same business domain implemented in other backend technologies while keeping the core logic technology-agnostic.

---

## 📡 Event-Driven Communication

The application uses Redis Pub/Sub to publish domain events such as:

- OPPORTUNITY_CREATED
- STAGE_CHANGED
- PROBABILITY_INCREASED
- PROBABILITY_DECREASED

Messaging is abstracted through a publisher interface, allowing future migration to technologies such as Kafka or RabbitMQ without impacting domain logic.

---

## ⚙️ Tech Stack
- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- Maven
- REST APIs
- Redis (Pub/Sub messaging)

---

## 📊 Use Cases

- Powering SaaS dashboards
- Managing organizational structures
- Providing data for analytics and forecasting
- Supporting multi-technology backend strategies

---

## 🧪 Best Practices

- Clean code structure
- Domain-focused design
- Scalable architecture
- Technology-agnostic business logic
- Event abstraction via interfaces
- Typed event contracts
- Decoupled messaging infrastructure

---

## 🚀 Future Improvements

- CI/CD pipeline
- Integration testing
- Kafka integration
- WebSocket realtime notifications

---

## 🧠 Key Takeaway

This project demonstrates how the same business domain can be implemented across different technologies while maintaining consistency, scalability, and clean architecture principles.

---

## 👨‍💻 Author

Project focused on demonstrating:

- Implementation of the same domain across multiple technologies
- Strong understanding of backend architecture
- Ability to design scalable and technology-agnostic systems