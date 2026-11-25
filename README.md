# User Authentication API

A Java 17 / Spring Boot REST API for registering users and signing in with an HMAC-SHA256 JWT. User emails are stored in an H2 database and passwords are stored as BCrypt hashes.

## Run locally

```bash
mvn spring-boot:run
```

The API listens on `http://localhost:8080`. The default in-memory H2 database and JWT secret are for local development only. Set `JWT_SECRET` to a private, random value of at least 32 bytes and `JWT_EXPIRATION_SECONDS` to a positive integer before running outside local development. The database is in memory and is cleared when the application stops.

Run the integration tests with:

```bash
mvn test
```

## Endpoints

### Register

`POST /auth/register` creates an account and returns `201 Created` with an empty response body. Both fields are required; the email must be valid and the password must contain at least 8 characters.

Request JSON:

```json
{
  "email": "alex@example.com",
  "password": "correct-horse"
}
```

If that email is already registered, send this exact request:

```json
{
  "email": "alex@example.com",
  "password": "correct-horse"
}
```

The endpoint returns `409 Conflict`:

```json
{
  "timestamp": "2026-01-15T12:00:00Z",
  "status": 409,
  "error": "Conflict",
  "message": "An account with this email already exists"
}
```

### Login

`POST /auth/login` checks the credentials and returns `200 OK` with a signed JWT. The exact request JSON is:

```json
{
  "email": "alex@example.com",
  "password": "correct-horse"
}
```

Successful response JSON:

```json
{
  "token": "<signed-jwt>",
  "expires_in": 3600
}
```

`expires_in` is the token lifetime in seconds and can be changed with `JWT_EXPIRATION_SECONDS`. An incorrect email or password returns `401 Unauthorized` with the same error format, without revealing which credential was incorrect. Invalid request fields return `400 Bad Request`.
