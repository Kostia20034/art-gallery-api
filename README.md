# Art Gallery API

Backend REST API for the Art Gallery web application. Built with Spring Boot, it handles artwork management, user authentication, and contact form submissions, backed by a PostgreSQL database.

**Live API:** https://art-gallery-api-production-683f.up.railway.app

## Features

- 🔐 JWT-based authentication with role-based access control (Admin / User)
- 🖼️ Full CRUD for artworks (create, read, update, delete)
- 📄 Pagination support for artwork listings
- 📬 Contact form submission and storage
- 🛡️ Spring Security with stateless session management
- 🗄️ PostgreSQL persistence via Spring Data JPA / Hibernate

## Tech Stack

- **Java 21**
- **Spring Boot 3.5**
- Spring Security
- Spring Data JPA / Hibernate
- PostgreSQL
- JWT (jjwt library)
- Maven

## Getting Started

### Prerequisites

- Java 21+
- Maven
- PostgreSQL (local or remote)

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/Kostia20034/art-gallery-api.git
   cd art-gallery-api
   ```

2. Configure environment variables (see below) or update `src/main/resources/application.properties` directly for local development.

3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

The API will start on `http://localhost:8080`.

### Environment Variables

| Variable | Description |
|---|---|
| `DATABASE_URL` | JDBC connection string, e.g. `jdbc:postgresql://host:port/dbname` |
| `DATABASE_USERNAME` | Database username |
| `DATABASE_PASSWORD` | Database password |
| `JWT_SECRET` | Secret key used to sign JWT tokens |
| `admin.email` | Email address automatically granted `ROLE_ADMIN` on registration |

## API Endpoints

### Auth

| Method | Endpoint | Access | Description |
|---|---|---|---|
| POST | `/api/v1/auth/register` | Public | Register a new user |
| POST | `/api/v1/auth/login` | Public | Log in and receive a JWT |

### Artworks

| Method | Endpoint | Access | Description |
|---|---|---|---|
| GET | `/api/v1/artworks` | Public | List artworks (paginated) |
| GET | `/api/v1/artworks/{id}` | Public | Get a single artwork |
| POST | `/api/v1/artworks` | Admin only | Create a new artwork |
| PUT | `/api/v1/artworks/{id}` | Admin only | Update an artwork |
| DELETE | `/api/v1/artworks/{id}` | Admin only | Delete an artwork |

### Contact

| Method | Endpoint | Access | Description |
|---|---|---|---|
| POST | `/api/v1/contact` | Public | Submit a contact form message |

## Authentication

Protected endpoints require a JWT in the `Authorization` header:

```
Authorization: Bearer <your-token-here>
```

Tokens are obtained via `/api/v1/auth/login` and are valid for 24 hours.

## Deployment

This service is deployed on [Railway](https://railway.app), connected to a managed PostgreSQL instance in the same project. Environment variables are configured in the Railway service settings.


## License

This project is for portfolio/educational purposes.
