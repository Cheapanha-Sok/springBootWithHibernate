package com.example.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody FormLogin body) {
        return authenticationService.loginUser(body.getEmail(), body.getPassword());
    }
}
