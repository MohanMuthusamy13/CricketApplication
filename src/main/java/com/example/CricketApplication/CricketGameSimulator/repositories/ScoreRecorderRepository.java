package com.example.CricketApplication.CricketGameSimulator.repositories;

import com.example.CricketApplication.CricketGameSimulator.entities.ScoreRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRecorderRepository extends MongoRepository<ScoreRecord, Long> {

}