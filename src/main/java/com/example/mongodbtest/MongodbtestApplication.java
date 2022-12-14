package com.example.mongodbtest;
import com.example.mongodbtest.domain.Address;
import com.example.mongodbtest.domain.Gender;
import com.example.mongodbtest.domain.Student;
import com.example.mongodbtest.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class MongodbtestApplication {
    public static void main(String[] args) {
        SpringApplication.run(MongodbtestApplication.class, args);
    }
//    @Bean
//    CommandLineRunner runner(StudentRepository repository) {
//        return args -> {
//            Address address = new Address("United Kingdom", "Wakefield", "WF5 9MB");
//            Student student = new Student("Mister", "Tester", "testStudent@school.com", Gender.MALE, address, List.of("Computer Science", "Art"), BigDecimal.valueOf(9.85), LocalDateTime.now());
//
//            repository.findStudentByEmailEquals(student.getEmail()).ifPresentOrElse((s) -> {
//                System.out.println("Student already exists with email: " + student.getEmail());
//            }, () -> {
//                System.out.println("Inserted " + student.getFirstName() + " " + student.getLastName());
//                repository.insert(student);
//            });
//        };
//    }
}
