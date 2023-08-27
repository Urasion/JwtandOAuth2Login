package com.example.jwtprac.util;

import com.example.jwtprac.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Component
public class UserMapper {
    public User userMapping(Map<String, Object> attributes){
        return User.builder()
                .role("ROLE_USER")
                .name((String)attributes.get("name"))
                .email((String)attributes.get("email"))
                .username((String)attributes.get("username"))
                .password((String) attributes.get("key"))
                .createDate(LocalDateTime.now())
                .provider((String)attributes.get("provider")).build();
    }
}
