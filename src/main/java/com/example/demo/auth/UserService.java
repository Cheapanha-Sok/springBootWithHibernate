package com.example.demo.auth;

import com.example.demo.account.Account;
import com.example.demo.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final AccountRepository accountRepository;
    UserService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("email is not valid"));
    }
}
