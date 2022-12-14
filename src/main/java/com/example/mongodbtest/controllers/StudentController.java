package com.example.mongodbtest.controllers;

import com.example.mongodbtest.domain.Student;
import com.example.mongodbtest.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping
    public ResponseEntity<Object> fetchAllStudents() {
        return studentService.getAllStudents();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> fetchStudentById(@PathVariable String id) {
        return studentService.getStudentById(id);
    }
    @PostMapping
    public ResponseEntity<Object> addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@PathVariable("id") String id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable("id") String id) {
        return studentService.deleteStudent(id);
    }
}
