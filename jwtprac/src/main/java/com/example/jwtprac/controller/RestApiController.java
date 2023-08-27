package com.example.jwtprac.controller;

import com.example.jwtprac.auth.Jwt.JwtTokenProvider;
import com.example.jwtprac.dto.JoinDto;
import com.example.jwtprac.dto.LoginDto;
import com.example.jwtprac.dto.Token;
import com.example.jwtprac.entity.User;
import com.example.jwtprac.service.JoinService;
import com.example.jwtprac.service.LoginService;
import com.example.jwtprac.service.RedisService;
import com.example.jwtprac.service.UserService;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class RestApiController {
    private final RedisService redisService;
    private final JoinService joinService;
    private final LoginService loginService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;


    @GetMapping("home")
    public String home(){
        return "<h1>home</h1>";
    }
    @GetMapping("")
    public String main(){
        return "메인페이지";
    }
    @PostMapping("token")
    public String token(){
        return "token";
    }
    @PostMapping("/login")
    public Token login(@RequestBody LoginDto loginDto) throws Exception{
        log.info("로그인핸들러 접근");
        Token token = loginService.login(loginDto);
        log.info("엑세스토큰 : {}",token.getAccessToken());
        log.info("리프레시토큰 : {}",token.getRefreshToken());
        redisService.saveRefreshToken(token.getRefreshToken());
        return token;

    }
    @PostMapping("/join")
    public String join(@RequestBody JoinDto joinDto) throws Exception{
        joinService.join(joinDto);
        return "회원가입 완료";
    }
    @GetMapping("/api/v1/user")
    public String user(@AuthenticationPrincipal User user){
        return "유저 페이지";
    }

    @PostMapping("/refresh")
    public String refreshToken(@RequestBody Map<String, String> data){
        String refreshToken = data.get("refreshToken");
        log.info("refreshToken : {}", refreshToken);
        if(redisService.checkRefreshToken(refreshToken)){
            try{
                jwtTokenProvider.validateRefreshToken(refreshToken);
                String username = jwtTokenProvider.findUsernameByRefresh(refreshToken);
                String accessToken = jwtTokenProvider.generateAccessToken(userService.findByUsername(username));
                return accessToken;
            }catch(JwtException ex){
                return ex.getMessage();
            }
        }else{
            return "현재 로그인 정보가 없습니다. 다시 로그인해주세요.";
        }
    }
    @PostMapping("/logout")
    public String logout(@RequestBody Map<String, String> data){
        String refreshToken = data.get("refreshToken");
        log.info("refreshToken : {}", refreshToken);
        return "로그아웃 완료";
    }

    @GetMapping("/api/v1/manager")
    public String manager(){
        return "매니저 페이지";
    }
}
