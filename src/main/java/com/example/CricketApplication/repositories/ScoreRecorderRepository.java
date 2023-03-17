package com.example.CricketApplication.repositories;

import com.example.CricketApplication.entities.ScoreRecord;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRecorderRepository extends ElasticsearchRepository<ScoreRecord, String> {

    @Query("""
            {
                "bool": {
                  "must": [
                    {
                      "match": {
                        "match_id": "?0"
                      }
                    },
                    {
                      "match": {
                        "over_count": "?1"
                      }
                    },
                    {
                      "match": {
                        "innings": ?2
                      }
                    }
                  ]
                }
              }""")
    ScoreRecord getBallOutcome(String matchId, String overCount, int innings);

    @Query("""
            {
                "bool": {
                  "must": [
                    {
                      "match": {
                        "match_id": "?0"
                      }
                    },
                    {
                      "match": {
                        "over_count": "?1"
                      }
                    }
                  ]
                }
              }""")
    List<ScoreRecord> getStatsOnParticularBallOnBothInnings(String matchId, String overCount);

}