package com.example.CricketApplication.cricketgamesimulator.repositories;

import com.example.CricketApplication.cricketgamesimulator.entities.Match;
import com.example.CricketApplication.cricketgamesimulator.entities.Player;
import com.example.CricketApplication.cricketgamesimulator.entities.PlayerStatsStructure;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends ElasticsearchRepository<Match, Long> {

//    @Query(value = "{\"teamsPlayed.teamName\": ?0}",fields = "{_id: 1, matchFormat: 1}")
    List<Match> getMatchesPlayedByTeamName(String teamName);

//    @Query(value = "{\"teamsPlayed.teamName\": ?0}", count = true)
    int getMatchesCountPlayedByTeamName(String teamName);


//    @Aggregation(pipeline = {
//            "{$match : {_id : ?0}}",
//            "{$unwind: \"$teamsPlayed\"}",
//            "{$unwind : \"$teamsPlayed.players\"}",
//            "{$sort : {\"teamsPlayed.players.score\" : -1}}",
//            "{$limit : 1}",
//            "{$set : {strikeRate : {$round : [{$divide : [{$multiply : [100, \"$teamsPlayed.players.score\"]}, \"$teamsPlayed.players.ballsFaced\"]}, 2]}}}",
//            "{$project : {name : \"$teamsPlayed.players.name\",score : \"$teamsPlayed.players.score\",wicketsTaken : \"$teamsPlayed.players.wicketsTaken\",teamName : \"$teamsPlayed.players.teamName\",ballsFaced : \"$teamsPlayed.players.ballsFaced\",ballsBowled : \"$teamsPlayed.players.ballsBowled\",strikeRate : \"$strikeRate\"}}"
//    })
    PlayerStatsStructure getMaxScorerIdByMatch(long matchId);

//    @Aggregation(pipeline = {
//            "{$match : {_id : ?0}}",
//            "{$unwind: \"$teamsPlayed\"}",
//            "{$unwind : \"$teamsPlayed.players\"}",
//            "{$sort : {\"teamsPlayed.players.wicketsTaken\" : -1}}",
//            "{$limit : 1}",
//            "{$set : {strikeRate : {$round : [{$divide : [{$multiply : [100, \"$teamsPlayed.players.score\"]}, \"$teamsPlayed.players.ballsFaced\"]}, 2]}}}",
//            "{$project : {name : \"$teamsPlayed.players.name\",score : \"$teamsPlayed.players.score\",wicketsTaken : \"$teamsPlayed.players.wicketsTaken\",teamName : \"$teamsPlayed.players.teamName\",ballsFaced : \"$teamsPlayed.players.ballsFaced\",ballsBowled : \"$teamsPlayed.players.ballsBowled\",strikeRate : \"$strikeRate\"}}"
//    })
    PlayerStatsStructure getMaxWicketTakerIdByMatch(long matchId);


//    @Aggregation(pipeline = {
//            "{$match:{_id : ?0}}",
//            "{$unwind:\"$teamsPlayed\"}",
//            "{$unwind:\"$teamsPlayed.players\"}",
//            "{$match:{\"teamsPlayed.players.score\":{$gte:30}}}",
//            "{$set:{strikeRate:{$round:[{$divide:[{$multiply:[100,\"$teamsPlayed.players.score\"]},\"$teamsPlayed.players.ballsFaced\"]},2]}}}",
//            "{$sort:{strikeRate:-1}}",
//            "{$limit:1}",
//            "{$project:{name:\"$teamsPlayed.players.name\",score:\"$teamsPlayed.players.score\",wicketsTaken:\"$teamsPlayed.players.wicketsTaken\",teamName:\"$teamsPlayed.players.teamName\",ballsFaced:\"$teamsPlayed.players.ballsFaced\",ballsBowled:\"$teamsPlayed.players.ballsBowled\",strikeRate:\"$strikeRate\"}}"
//    })
    PlayerStatsStructure getMaxStrikeRatePlayer(long matchId);

}