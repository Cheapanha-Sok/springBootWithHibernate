package com.example.demo.course;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
public class CourseConfig {
    @Bean
    CommandLineRunner courseCommandLineRunner(CourseRepository courseRepository){
        return args -> {

        };
    }
}
