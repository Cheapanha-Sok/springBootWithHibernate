package com.example.demo.account;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountConfig {
    @Bean
    CommandLineRunner accountCommandLineRunner(AccountRepository accountRepository){
        return args -> {

        };
    }
}
