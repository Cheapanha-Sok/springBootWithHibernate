package com.example.demo.teacher;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeacherConfig {
    @Bean
    CommandLineRunner teacherCommandLineRunner(TeacherRepository teacherRepository){
        return args -> {

        };
    }
}
