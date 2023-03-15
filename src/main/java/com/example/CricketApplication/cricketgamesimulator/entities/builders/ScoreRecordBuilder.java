package com.example.CricketApplication.cricketgamesimulator.entities.builders;

import com.example.CricketApplication.cricketgamesimulator.entities.Player;
import com.example.CricketApplication.cricketgamesimulator.entities.ScoreRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreRecordBuilder {


    public ScoreRecord build(
            String matchId, String over, String ballOutcome, int innings,
            int totalRunsScoredAtThatInstance, int totalWicketsLoseAtThatInstance,
            Player Batsman, Player Bowler
    ) {
        return ScoreRecord.builder()
                .matchId(matchId)
                .overCount(over)
                .ballOutcome(ballOutcome)
                .innings(innings)
                .totalRunsScoredByBattingTeam(totalRunsScoredAtThatInstance)
                .totalWicketsDown(totalWicketsLoseAtThatInstance)
                .batsman(Batsman)
                .bowler(Bowler)
                .build();
    }
}