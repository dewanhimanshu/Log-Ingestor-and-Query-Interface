package com.example.logIngestor.repository;

import com.example.logIngestor.model.Log;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends MongoRepository<Log,String> {

}
