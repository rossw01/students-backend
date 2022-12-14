package com.example.mongodbtest.services;

import com.example.mongodbtest.domain.School;
import com.example.mongodbtest.domain.Student;
import com.example.mongodbtest.repositories.SchoolRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SchoolService {
    public final SchoolRepository schoolRepository;
    public ResponseEntity<Object> getAllSchools() {
        try {
            List<School> schools = new ArrayList<>(schoolRepository.findAll());
            if (schools.isEmpty()) {
                return new ResponseEntity<>("No Schools have been added", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(schools, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to fetch list of Schools", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Object> getSchoolById(String id) {
        try {
            Optional<School> school = schoolRepository.findById(id);
            if (school.isEmpty()) {
                return new ResponseEntity<>(String.format("School could not be found with Id: '%s'", id), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(school, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to fetch School", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> addSchool(School school) {
        try {
            school.setId(new ObjectId().toString());
            school.setCreated(LocalDateTime.now());
            return new ResponseEntity<>(schoolRepository.save(school), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Object> updateSchool(String id, School school) {
        Optional<School> schoolData = schoolRepository.findById(id);
        if (schoolData.isEmpty()) {
            return new ResponseEntity<>("School with that Id could not be found", HttpStatus.NOT_FOUND);
        }
        School _school = schoolData.get();
        _school.setName(school.getName());
        _school.setAddress(school.getAddress());
        _school.setStudents(school.getStudents());
        return new ResponseEntity<>(schoolRepository.save(_school), HttpStatus.OK);
    }
    public ResponseEntity<Object> deleteSchool(String id) {
        try {
            if (schoolRepository.findById(id).isEmpty()) {
                return new ResponseEntity<>("School with that Id could not be found", HttpStatus.NOT_FOUND);
            }
            schoolRepository.deleteById(id);
            return new ResponseEntity<>(String.format("School with ID '%s' deleted successfully", id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(String.format("Failed to remove School with ID '%s'", id), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
