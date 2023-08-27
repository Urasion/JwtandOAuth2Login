package com.example.jwtprac.dto;

import lombok.Data;

@Data
public class JoinDto {
    private String username;
    private String password;

    public JoinDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
