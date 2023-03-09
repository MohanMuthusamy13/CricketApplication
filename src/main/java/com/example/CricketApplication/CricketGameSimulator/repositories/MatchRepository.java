package com.example.CricketApplication.CricketGameSimulator.repositories;

import com.example.CricketApplication.CricketGameSimulator.entities.Match;
import com.example.CricketApplication.CricketGameSimulator.entities.Player;
import com.example.CricketApplication.CricketGameSimulator.entities.PlayerStatsStructure;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends MongoRepository<Match, Long> {

    @Query(value = "{\"teamsPlayed.teamName\": ?0}",fields = "{_id: 1, matchFormat: 1}")
    List<Match> getMatchesPlayedByTeamName(String teamName);

    @Query(value = "{\"teamsPlayed.teamName\": ?0}", count = true)
    int getMatchesCountPlayedByTeamName(String teamName);

    @Aggregation(pipeline = {
            "{'$match': {'matchId' : ?0 }}",
            "{$unwind : '$teamsPlayed'}",
            "{$unwind : '$teamsPlayed.players'}",
            "{$project : {_id : \"$teamsPlayed.players._id\"," +
                    "name : \"$teamsPlayed.players.name\"," +
                    "score : {$max: \"$teamsPlayed.players.score\"}, " +
                    "wicketsTaken : \"$teamsPlayed.players.wicketsTaken\"," +
                    "teamName : \"$teamsPlayed.players.teamName\"," +
                    "ballsFaced : \"$teamsPlayed.players.ballsFaced\","+
                    "ballsBowled : \"$teamsPlayed.players.ballsBowled\"}}",
            "{$sort: {\"score\" : -1}}",
            "{$limit: 1}"
    })
    PlayerStatsStructure getMaxScorerIdByMatch(long matchId);

    @Aggregation(pipeline = {
            "{'$match': {'matchId' : ?0 }}",
            "{$unwind : '$teamsPlayed'}",
            "{$unwind : '$teamsPlayed.players'}",
            "{$project : {_id : \"$teamsPlayed.players._id\", " +
                    "name : \"$teamsPlayed.players.name\", " +
                    "score : \"$teamsPlayed.players.score\"," +
                    "wicketsTaken : {$max: \"$teamsPlayed.players.wicketsTaken\"}, " +
                    "teamName : \"$teamsPlayed.players.teamName\"," +
                    "ballsFaced : \"$teamsPlayed.players.ballsFaced\"," +
                    "ballsBowled : \"$teamsPlayed.players.ballsBowled\"}}",
            "{$sort: {\"wicketsTaken\" : -1}}",
            "{$limit: 1}"
    })
    PlayerStatsStructure getMaxWicketTakerIdByMatch(long matchId);



}