# Authentication API

The API accepts JSON requests at `http://localhost:8080`. Send `Content-Type: application/json`.

## Register

`POST /auth/register` creates an account.

### Request

```json
{
  "email": "alex@example.com",
  "password": "correct-horse"
}
```

Both fields are required and must not be blank. `email` must be a valid email address. For registration, `password` must contain at least 8 characters.

### Success — `201 Created`

The response has no body.

### Errors

If the email is already registered, the response is `409 Conflict`:

```json
{
  "timestamp": "2026-01-15T12:00:00Z",
  "status": 409,
  "error": "Conflict",
  "message": "An account with this email already exists"
}
```

Invalid fields return `400 Bad Request`. The error response has the same structure as above; `message` identifies the first validation error (for example, `email must not be blank`).

## Log in

`POST /auth/login` checks the account credentials and returns a signed JWT.

### Request

```json
{
  "email": "alex@example.com",
  "password": "correct-horse"
}
```

Both fields are required and must not be blank. `email` must be a valid email address. Login does not impose the registration password-length rule.

### Success — `200 OK`

```json
{
  "token": "<signed-jwt>",
  "expires_in": 3600
}
```

`token` is the signed JWT. `expires_in` is its lifetime in seconds and is configured with `JWT_EXPIRATION_SECONDS` (default: `3600`).

### Errors

An unrecognized email or incorrect password returns `401 Unauthorized`:

```json
{
  "timestamp": "2026-01-15T12:00:00Z",
  "status": 401,
  "error": "Unauthorized",
  "message": "Invalid email or password"
}
```

Invalid fields return `400 Bad Request` with the same error structure, where `message` identifies the first validation error.
