package com.example.demo.course;

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
