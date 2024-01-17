package com.example.demo.role;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleConfig {
    @Bean
    CommandLineRunner commandLineRunner(RoleRepository roleRepository){
        return args -> {

        };
    }
}
