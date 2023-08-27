package com.example.jwtprac.service;

import com.example.jwtprac.dto.JoinDto;
import com.example.jwtprac.entity.User;
import com.example.jwtprac.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public void join(JoinDto joinDto){
        User user = new User();
        user.setUsername(joinDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(joinDto.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);

    }

}
