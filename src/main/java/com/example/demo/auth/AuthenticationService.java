package com.example.demo.auth;

import com.example.demo.account.Account;
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

    public ResponseEntity<?> loginUser(String email, String password){
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            String token = tokenService.generateJwt(auth);
            Account account = (Account) auth.getPrincipal();
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO(account, token);
            return ResponseEntity.ok(loginResponseDTO);
        }catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }
}
