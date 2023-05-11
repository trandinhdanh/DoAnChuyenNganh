package com.techpower.exammanagement.auth;

public record AuthenticationRequest(
        String username,
        String password
) {
}
