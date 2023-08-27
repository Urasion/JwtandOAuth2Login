package com.example.jwtprac.service;

import com.example.jwtprac.entity.RefreshToken;
import com.example.jwtprac.repository.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.type.descriptor.java.ObjectJavaType;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate redisTemplate;
    private final RedisRepository redisRepository;

    public void saveRefreshToken(String token){
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        setOperations.add("refreshToken",token);
    }
    public boolean checkRefreshToken(String token){
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        if(setOperations.isMember("refreshToken",token)){
            return true;
        }
        return false;
    }
    public void deleteRefreshToken(String token){

    }
}
