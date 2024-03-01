package com.example.demo.config;

import com.example.demo.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CourseConfig {
    @Bean
    CommandLineRunner courseCommandLineRunner(CourseRepository courseRepository){
        return args -> {

        };
    }
}
