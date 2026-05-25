# 🛒 Product API

A production-ready RESTful API for product management built with Java Spring Boot and PostgreSQL. Features JWT authentication, Spring Security, pagination, and full CRUD operations.

## 🌐 Live

| | URL |
|---|---|
| 🖥️ Frontend | https://product-frontend-vm4l.vercel.app |
| 📡 API | https://product-api-production-949c.up.railway.app/api/v1/products |
| 📖 Swagger UI | https://product-api-production-949c.up.railway.app/swagger-ui/index.html |

## 🚀 Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3.5 |
| Database | PostgreSQL |
| ORM | Spring Data JPA / Hibernate |
| Security | Spring Security + JWT |
| Validation | Spring Boot Validation |
| Docs | SpringDoc OpenAPI (Swagger UI) |
| DevOps | Docker, Railway |
| Build Tool | Maven |

---

## 📁 Architecture

```
com.example.First.project/
├── controller/     # HTTP layer — requests and responses
├── service/        # Business logic layer
├── repository/     # Database access layer
├── model/          # JPA entities
├── dto/            # Request/Response DTOs
├── exception/      # Global exception handling
└── security/       # JWT filter, config, utilities
```

**Key design decisions:**
- **DTO pattern** — separates internal entities from API responses
- **JWT + Spring Security** — stateless authentication, protected routes
- **Global Exception Handler** — centralized error handling
- **@Valid** — input validation at controller boundary
- **Pagination** — efficient data fetching with Spring Data Pageable

---

## 📦 API Endpoints

### Auth (Public)
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/v1/auth/register` | Register new user |
| POST | `/api/v1/auth/login` | Login + get JWT token |

### Products
| Method | Endpoint | Auth | Description |
|---|---|---|---|
| GET | `/api/v1/products` | ❌ Public | Get all products (paginated) |
| GET | `/api/v1/products/{id}` | ❌ Public | Get product by ID |
| GET | `/api/v1/products/search?name=` | ❌ Public | Search by name |
| POST | `/api/v1/products` | ✅ Required | Create product |
| PUT | `/api/v1/products/{id}` | ✅ Required | Update product |
| DELETE | `/api/v1/products/{id}` | ✅ Required | Delete product |

---

## 🔍 Example Requests

**Register:**
```json
POST /api/v1/auth/register
{ "email": "user@gmail.com", "password": "secret123" }

Response 200:
{ "token": "eyJhbGciOiJIUzI1NiJ9..." }
```

**Create product (with token):**
```json
POST /api/v1/products
Authorization: Bearer eyJhbGci...
{ "name": "iPhone 15", "price": 999.99 }

Response 201:
{ "id": 1, "name": "iPhone 15", "price": 999.99 }
```

**Get paginated products:**
```
GET /api/v1/products?page=0&size=10
```

---

## 🐳 Run with Docker (Recommended)

Make sure Docker Desktop is running:

```bash
git clone https://github.com/Kostia20034/Product-api.git
cd Product-api
docker-compose up
```

App runs at `http://localhost:8080` — no Java or PostgreSQL installation needed!! ✅

---

## ⚙️ Run Locally (Manual)

**Prerequisites:** Java 21, Maven, PostgreSQL

```bash
git clone https://github.com/Kostia20034/Product-api.git
cd Product-api
```

Create database:
```sql
CREATE DATABASE product_api;
```

Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/product_api
spring.datasource.username=postgres
spring.datasource.password=your_password
```

```bash
./mvnw spring-boot:run
```

---

## 📖 API Documentation

Swagger UI available at:
```
http://localhost:8080/swagger-ui/index.html
```

---

## 🔮 Roadmap

- [ ] Refresh tokens (proper logout)
- [ ] Unit + integration tests
- [ ] CI/CD with GitHub Actions
- [ ] Rate limiting
