package com.example.CricketApplication.repositories.repositoryImpl;

import com.example.CricketApplication.entities.MatchStateSaver;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchStatusSaverRepositoryImpl extends MongoRepository<MatchStateSaver, Long> {
}