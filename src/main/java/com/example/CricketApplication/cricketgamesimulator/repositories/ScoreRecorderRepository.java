package com.example.CricketApplication.cricketgamesimulator.repositories;

import com.example.CricketApplication.cricketgamesimulator.entities.ScoreRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRecorderRepository extends MongoRepository<ScoreRecord, Long> {

    @Query(value = "{\"matchId\" : ?0, \"overCount\" : \"?1\"}}")
    List<ScoreRecord> getStatsOnParticularBall(long matchId, String over);

    @Query(value = "{\"matchId\" : ?0, \"overCount\" : \"?1\", \"innings\" : ?2}")
    List<ScoreRecord> getStatsOnParticularBallAndInnings(long matchId,
                                                   String over, int innings);

}