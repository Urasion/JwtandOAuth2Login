package com.example.jwtprac.service;

import com.example.jwtprac.entity.User;
import com.example.jwtprac.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User findByUsername(String username){
        User user = userRepository.findByUsername(username);
        return user;
    }
}
