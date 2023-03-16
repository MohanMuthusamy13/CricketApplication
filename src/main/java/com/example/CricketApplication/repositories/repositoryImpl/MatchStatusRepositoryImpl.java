package com.example.CricketApplication.repositories.repositoryImpl;

import com.example.CricketApplication.entities.MatchStatusRecord;
import com.example.CricketApplication.repositories.repository.MatchStatusRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchStatusRepositoryImpl extends MongoRepository<MatchStatusRecord, Long>, MatchStatusRepository {

    @Query(value = "{matchId : ?0}")
    Optional<MatchStatusRecord> getMatchRecordByMatchId(long matchId);

    @Query(value = "{matchId : ?0}", fields = "{matchStatus : 1}")
    Optional<String> getMatchStatusByMatchId(long matchId);

}