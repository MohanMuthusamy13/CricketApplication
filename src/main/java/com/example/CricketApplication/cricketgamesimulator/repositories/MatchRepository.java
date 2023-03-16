package com.example.CricketApplication.cricketgamesimulator.repositories;

import com.example.CricketApplication.cricketgamesimulator.entities.Match;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends ElasticsearchRepository<Match, String> {

    List<Match> findByMatchFormat(String matchFormat);

    @Query("""
            {
                "nested": {
                  "path": "teams_played",
                  "query": {
                    "match": {
                      "teams_played.team_name": "?0"
                    }
                  }
                }
              }""")
    List<Match> getMatchesByTeamName(String teamName);

}