package com.example.CricketApplication.CricketGameSimulator.repositories;

import com.example.CricketApplication.CricketGameSimulator.entities.MatchStatusRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchStatusRepository extends MongoRepository<MatchStatusRecord, Long> {

    @Query(value = "{matchId : ?0}")
    Optional<MatchStatusRecord> getMatchRecordByMatchId(long matchId);

    @Query(value = "{matchId : ?0}", fields = "{matchStatus : 1}")
    Optional<String> getMatchStatusByMatchId(long matchId);

}