package com.example.demo.config;

import com.example.demo.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner studentCommandLIne(StudentRepository repository){
        return args -> {

        };
    }

}
