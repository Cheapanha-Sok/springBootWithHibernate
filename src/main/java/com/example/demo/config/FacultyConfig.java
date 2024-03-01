package com.example.demo.config;

import com.example.demo.repository.FacultyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FacultyConfig {
    @Bean
    CommandLineRunner facultyCommandLineRunner(FacultyRepository repository){
        return args ->{

        };

    }
}
