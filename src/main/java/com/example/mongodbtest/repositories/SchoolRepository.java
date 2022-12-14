package com.example.mongodbtest.repositories;

import com.example.mongodbtest.domain.School;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SchoolRepository extends MongoRepository<School, String> {
}
