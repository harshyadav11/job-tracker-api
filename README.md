# Job Application System Backend


A backend system to track job applications with filtering, pagination, and containerized deployment.

## 🚀 Features
- JWT Authentication
- Job CRUD APIs
- Pagination & Filtering
- Sorting
- Status Tracking
- Exception Handling
- Dockerized with MySQL (Docker Compose)

## 🛠 Tech Stack
- Java (Spring Boot)
- MySQL
- JPA / Hibernate
- Docker & Docker Compose

## ▶️ Run Locally
mvn spring-boot:run

## 🐳 Run with Docker
docker-compose up --build

## 📌 API Examples
GET /jobs?page=0&size=5  
GET /jobs?status=APPLIED  
GET /jobs?company=google  
GET /jobs?sort=company,asc