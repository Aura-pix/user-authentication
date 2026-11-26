# User Authentication

A Java 17 and Spring Boot REST API for user registration and JWT-based login. It uses Spring Web, Validation, and Data JPA with an in-memory H2 database, BCrypt password hashing, and HMAC-SHA256 signed JWTs.

This repository was created to test documentation tooling; API documentation lives in [`docs/api.md`](docs/api.md).

## Run locally

```bash
mvn spring-boot:run
```

The API listens at `http://localhost:8080`. Run the tests with `mvn test`. For non-local use, set `JWT_SECRET` to a strong random value of at least 32 bytes and `JWT_EXPIRATION_SECONDS` to a positive integer; the default secret is for development only.
