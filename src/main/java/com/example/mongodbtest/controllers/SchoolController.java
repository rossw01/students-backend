package com.example.mongodbtest.controllers;

import com.example.mongodbtest.domain.School;
import com.example.mongodbtest.services.SchoolService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/schools")
@AllArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;
    @GetMapping
    public ResponseEntity<Object> fetchAllSchools() { return schoolService.getAllSchools(); }
    @GetMapping("/{id}")
    public ResponseEntity<Object> fetchSchoolById(@PathVariable String id) {
        return schoolService.getSchoolById(id);
    }
    @PostMapping
    public ResponseEntity<Object> addSchool(@RequestBody School school) {
        return schoolService.addSchool(school);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSchool(@PathVariable("id") String id, @RequestBody School school) {
        return schoolService.updateSchool(id, school);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSchool(@PathVariable("id") String id) {
        return schoolService.deleteSchool(id);
    }
}
