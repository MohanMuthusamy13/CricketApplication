package com.example.CricketApplication.CricketGameSimulator.repositories;

import com.example.CricketApplication.CricketGameSimulator.entities.Player;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends MongoRepository<Player, Long> {

    @Aggregation(pipeline = {
            "{'$sort' : {score : -1}}",
            "{'$limit' : 1}"
    })
    Player getOverallMaximumScorer();

    @Aggregation(pipeline = {
            "{'$sort' : {wicketsTaken : -1}}",
            "{'$limit' : 1}"
    })
    Player getOverallMaxWicketTaker();

    @Query(value = "{\"_id\" : ?0}")
    Player getMaxScorerByMatchId(long playerId);

    List<Player> findByTeamName(String teamName);

    List<Player> findByBaseAbility(String baseAbility);

    Player findByName(String name);

}