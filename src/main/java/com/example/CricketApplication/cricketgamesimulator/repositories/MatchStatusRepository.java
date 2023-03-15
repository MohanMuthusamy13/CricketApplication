package com.example.CricketApplication.cricketgamesimulator.repositories;

import com.example.CricketApplication.cricketgamesimulator.entities.MatchStatusRecord;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchStatusRepository extends ElasticsearchRepository<MatchStatusRecord, Long> {

//    @Query(value = "{matchId : ?0}")
    Optional<MatchStatusRecord> getMatchRecordByMatchId(long matchId);

//    @Query(value = "{matchId : ?0}", fields = "{matchStatus : 1}")
    Optional<String> getMatchStatusByMatchId(long matchId);

}