package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner studentCommandLIne(StudentRepository repository){
        return args -> {
            Student panha = new Student(
                    "John Doe",
                    "Male",
                    LocalDate.of(2000, Month.JANUARY, 15), // Replace with the actual date
                    "123-456-7890",
                    "123 Main St",
                    2023,
                    2,
                    "Computer Science"
            );
            repository.saveAll(
                    List.of()
            );
        };
    }

}
