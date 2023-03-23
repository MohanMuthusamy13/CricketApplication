package com.example.CricketApplication.repositories;

import com.example.CricketApplication.entities.MatchStateSaver;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchStatusSaverRepository extends MongoRepository<MatchStateSaver, Long> {
}