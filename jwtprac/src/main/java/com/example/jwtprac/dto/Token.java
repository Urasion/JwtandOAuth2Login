package com.example.jwtprac.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data

public class Token {
    private String accessToken;
    private String refreshToken;

    public Token(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
