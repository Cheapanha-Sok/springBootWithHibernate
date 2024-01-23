package com.example.demo.faculty;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class FacultyConfig {
    @Bean
    CommandLineRunner facultyCommandLineRunner(FacultyRepository repository){
        return args ->{

        };

    }
}
