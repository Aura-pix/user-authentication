package com.aurapix.userauth.dto;

public record AuthResponse(String token, long expires_in) {
}
