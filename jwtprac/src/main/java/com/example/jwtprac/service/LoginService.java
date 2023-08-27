package com.example.jwtprac.service;

import com.example.jwtprac.auth.Jwt.JwtTokenProvider;
import com.example.jwtprac.dto.LoginDto;
import com.example.jwtprac.dto.Token;
import com.example.jwtprac.entity.User;
import com.example.jwtprac.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public Token login(LoginDto loginDto){
        User userEntity = userRepository.findByUsername(loginDto.getUsername());
        if(userEntity != null){
            if(bCryptPasswordEncoder.matches(loginDto.getPassword(), userEntity.getPassword())){
                String accessToken = jwtTokenProvider.generateAccessToken(userEntity);
                String refreshToken = jwtTokenProvider.generateRefreshToken(userEntity);
                return new Token(accessToken, refreshToken);
            }
        }
        return null;
    }



}
