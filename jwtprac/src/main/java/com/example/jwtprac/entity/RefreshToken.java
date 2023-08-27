package com.example.jwtprac.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;

@Getter
@RedisHash(value = "refreshToken")
public class RefreshToken {
    public RefreshToken(String refreshToken, String username, ArrayList<String> test) {
        this.id = refreshToken;
        this.username = username;
        this.test = test;
    }

    @Id
    private String id;

    private String username;

    private ArrayList<String> test = new ArrayList<>();
}
