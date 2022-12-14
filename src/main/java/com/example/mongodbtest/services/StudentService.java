package com.example.mongodbtest.services;

import com.example.mongodbtest.domain.Student;
import com.example.mongodbtest.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentService {
    public final StudentRepository studentRepository;
    public ResponseEntity<Object> getAllStudents() {
        try {
            List<Student> students = new ArrayList<Student>(studentRepository.findAll());
            if (students.isEmpty()) {
               return new ResponseEntity<>("No users have been added", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to fetch list of students", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Object> getStudentById(String id) {
        try {
            Optional<Student> student = studentRepository.findById(id);
            if (student.isEmpty()) {
                return new ResponseEntity<>(String.format("Student could not be found with Id: '%s'", id), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to fetch Student", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Object> addStudent(Student student) {
        if (studentRepository.findById(student.getId()).isPresent()) {
            return new ResponseEntity<>("A student with that ID has already been added", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Student '" + student.getFirstName() + " " + student.getLastName() + "' Inserted", HttpStatus.OK);
    }
    public ResponseEntity<Object> updateStudent(String id, Student student) {
        Optional<Student> studentData = studentRepository.findById(id);
        if (studentData.isEmpty()) {
            return new ResponseEntity<>("Student with that Id could not be found", HttpStatus.NOT_FOUND);
        }
        Student _student = studentData.get();
        _student.setFirstName(student.getFirstName());
        _student.setLastName(student.getLastName());
        _student.setEmail(student.getEmail());
        _student.setGender(student.getGender());
        _student.setAddress(student.getAddress());
        _student.setFavouriteSubjects(student.getFavouriteSubjects());
        _student.setTotalSpentInBooks(student.getTotalSpentInBooks());
        return new ResponseEntity<>(studentRepository.save(_student), HttpStatus.OK);
    }
    public ResponseEntity<Object> deleteStudent(String id) {
        try {
            if (studentRepository.findById(id).isEmpty()) {
                return new ResponseEntity<>("Student with that Id could not be found", HttpStatus.NOT_FOUND);
            }
            studentRepository.deleteById(id);
            return new ResponseEntity<>(String.format("Student with ID '%s' deleted successfully", id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(String.format("Failed to remove Student with ID '%s'", id), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
