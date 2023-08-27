package com.example.jwtprac.auth;

import com.example.jwtprac.entity.Warn;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
/**
 * Spring Security는 인증 인가 실패시 FilterSecurityInterceptor가 2가지 예외를 발생시킵니다.
 * 인가 예외가 생기면 AccessDenidedException을 발생시키는데 이는 AccessDeniedHandler를 implement하여 구현할 수 있다.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        log.warn("CustomAccessDenied : 인가되지 않은 페이지를 접근하셨습니다.");
        Warn message = new Warn();
        message.setErrorType("Forbidden");
        message.setCode("403");
        message.setMessage("인가되지 않은 페이지를 접근하셨습니다.");
        response.getWriter().write(objectMapper.writeValueAsString(message));
    }
}
