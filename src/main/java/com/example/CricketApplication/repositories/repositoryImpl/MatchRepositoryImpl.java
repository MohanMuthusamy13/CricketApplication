package com.example.CricketApplication.repositories.repositoryImpl;

import com.example.CricketApplication.entities.Match;
import com.example.CricketApplication.repositories.repository.MatchRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepositoryImpl extends MongoRepository<Match, Long>, MatchRepository {

    @Query(value = "{\"teamsPlayed.teamName\": ?0}",fields = "{_id: 1, matchFormat: 1}")
    List<Match> getMatchesPlayedByTeamName(String teamName);

    @Query(value = "{\"teamsPlayed.teamName\": ?0}", count = true)
    int getMatchesCountPlayedByTeamName(String teamName);

}