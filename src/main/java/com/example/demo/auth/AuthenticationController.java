package com.example.demo.auth;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid  FormLogin body) {
        return authenticationService.loginUser(body.getEmail(), body.getPassword());
    }
}
