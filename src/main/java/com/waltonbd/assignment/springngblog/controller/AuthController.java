package com.waltonbd.assignment.springngblog.controller;

import com.waltonbd.assignment.springngblog.dto.BaseResponse;
import com.waltonbd.assignment.springngblog.dto.LoginRequest;
import com.waltonbd.assignment.springngblog.dto.RegisterRequest;
import com.waltonbd.assignment.springngblog.service.AuthService;
import com.waltonbd.assignment.springngblog.service.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse> signup(@RequestBody RegisterRequest registerRequest) {
        try {
            authService.signup(registerRequest);
            return ResponseEntity.ok(new BaseResponse(200, "Successfully registered user."));
        } catch (Throwable h) {
            return ResponseEntity.ok(new BaseResponse(300, h.getMessage()));
        }
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
