package com.example.CricketApplication.CricketGameSimulator.repositories;

import com.example.CricketApplication.CricketGameSimulator.entities.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends MongoRepository<Team, Long> {
}