package com.example.demo.controllers;

import com.example.demo.models.entity.User;
import com.example.demo.models.pojo.LoginRequest;
import com.example.demo.models.pojo.RegisterRequest;
import com.example.demo.models.pojo.Response;
import com.example.demo.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Response> signup(@RequestBody RegisterRequest registerRequest){
        return  new ResponseEntity<>(authService.signup(registerRequest), OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity(authService.login(loginRequest), OK);
    }



}
