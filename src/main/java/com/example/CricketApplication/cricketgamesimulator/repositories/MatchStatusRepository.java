package com.example.CricketApplication.cricketgamesimulator.repositories;

import com.example.CricketApplication.cricketgamesimulator.entities.MatchStatusRecord;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchStatusRepository extends ElasticsearchRepository<MatchStatusRecord, String> {

    @Query("""
            {
                "bool": {
                  "should": [
                    {
                      "wildcard": {
                        "match_id": {
                          "value": "?0"
                        }
                      }
                    },
                    {
                      "fuzzy": {
                        "match_id": {
                          "value": "?0"
                        }
                      }
                    }
                  ]
                }
              }""")
    MatchStatusRecord getMatchStatus(String matchId);
}