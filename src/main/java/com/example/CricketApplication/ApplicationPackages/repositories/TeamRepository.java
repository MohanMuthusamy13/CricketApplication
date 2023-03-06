package com.example.CricketApplication.ApplicationPackages.repositories;

import com.example.CricketApplication.ApplicationPackages.entities.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends MongoRepository<Team, Long> {
}