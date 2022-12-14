package com.example.mongodbtest.repositories;

import com.example.mongodbtest.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, String> {
    Optional<Student> findStudentByEmailEquals(String email);
    Optional<Student> findStudentByIdEquals(String Id);
}
