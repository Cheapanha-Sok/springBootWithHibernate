package com.example.demo.department;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DepartmentConfig {
    @Bean
    CommandLineRunner departmentCommandLineRunner(DepartmentRepository repository){
        return args -> {

        };
    }
}
