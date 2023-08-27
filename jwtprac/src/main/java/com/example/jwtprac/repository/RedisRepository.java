package com.example.jwtprac.repository;

import com.example.jwtprac.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RedisRepository extends CrudRepository<RefreshToken, String> {
}
