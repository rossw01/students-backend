package com.example.mongodbtest.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
public class School {
    @Id
    private String id;
    private String name;
    private Address address;
    private LocalDateTime created;
    private List<String> students; // ObjectId list
    public School(String name, Address address, List<String> students) {
        this.name = name;
        this.address = address;
        this.students = students;
    }
}
