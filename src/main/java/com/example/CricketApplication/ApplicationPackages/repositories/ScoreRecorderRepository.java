package com.example.CricketApplication.ApplicationPackages.repositories;

import com.example.CricketApplication.ApplicationPackages.entities.ScoreRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRecorderRepository extends MongoRepository<ScoreRecord, Long> {

}