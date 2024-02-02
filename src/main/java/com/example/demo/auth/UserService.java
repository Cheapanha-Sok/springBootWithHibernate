package com.example.demo.auth;

import com.example.demo.account.AccountRepository;
import com.example.demo.response.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final AccountRepository accountRepository;
    UserService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountRepository.findByEmail(email).orElseThrow(()-> new NotFoundException(String.format("User with email %s not found!" , email)));
    }
}
