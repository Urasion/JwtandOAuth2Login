package com.example.jwtprac.auth.OAuth2;

import com.example.jwtprac.auth.Jwt.JwtTokenProvider;
import com.example.jwtprac.dto.Token;
import com.example.jwtprac.entity.User;
import com.example.jwtprac.repository.UserRepository;
import com.example.jwtprac.util.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     *
     * 2023-08-27 정지원 작업
     *
     * User get = 레포지토리에서 가져온 유저정보
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        User user = userMapper.userMapping(attributes);
        log.info("Principal OAuth2User : {}", oAuth2User);
        User get = userRepository.findByUsername(user.getUsername());
        if(get == null){
            userRepository.save(user);
        }
        String accessToken = jwtTokenProvider.generateAccessToken(user);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user);
        Token token = new Token(accessToken, refreshToken);
        log.info("엑세스토큰 : {}",token.getAccessToken());
        log.info("리프레시토큰 : {}",token.getRefreshToken());
        response.getWriter().write(objectMapper.writeValueAsString(token));
    }
}
