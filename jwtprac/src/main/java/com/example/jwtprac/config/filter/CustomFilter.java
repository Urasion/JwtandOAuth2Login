package com.example.jwtprac.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class CustomFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        log.info("필터1");
        // 토큰 : cos(토큰)을 만들어 줘야하는데, id와 pw 모두 정상적으로 들어와서 로그인이 완료되면 토큰을 만들어 주고 그걸 응답을 해준다.
        // 요청할 때 마다 header에 Authorization에 value 값으로 토큰을 가지고 오는데 토큰이 넘어올 시 이 토큰이 내가 만든 토큰인지 체크만 하면된다.
        // (RSA, HS256)을 통해서 검증을 함
        if(req.getMethod().equals("POST")){
            log.info("POST 요청됨");
            String headerAuth = ((HttpServletRequest) request).getHeader("Authorization");
            log.info("headerAuth : {}",headerAuth);
            if(headerAuth.equals("cos")){
                chain.doFilter(req, res);
            }else{
                PrintWriter outPrintWriter = res.getWriter();
                outPrintWriter.println("인증 실패");
            }
        }

    }
}
