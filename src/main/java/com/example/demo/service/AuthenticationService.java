package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.service.TokenService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
     AuthenticationService( AuthenticationManager authenticationManager , TokenService tokenService){
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public ResponseEntity<LoginResponse> loginUser(String email, String password){
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            String token = tokenService.generateJwt(auth);
            Account account = (Account) auth.getPrincipal();
            LoginResponse response = new LoginResponse(account , token);
            return ResponseEntity.ok(response);
        }catch (BadCredentialsException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    public record LoginResponse(Account account , String token) {
    }
}
