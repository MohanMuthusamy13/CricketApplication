package com.example.CricketApplication.ApplicationPackages.entities.builders;

import com.example.CricketApplication.ApplicationPackages.entities.Player;
import com.example.CricketApplication.ApplicationPackages.entities.ScoreRecord;
import com.example.CricketApplication.ApplicationPackages.entities.Team;
import com.example.CricketApplication.ApplicationPackages.service.auxillaryServices.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreRecordBuilder {

    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public ScoreRecordBuilder(SequenceGeneratorService sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    public ScoreRecord build(
            long matchId, String over, String ballOutcome, int innings,
            int totalRunsScoredAtThatInstance, int totalWicketsLoseAtThatInstance,
            Player Batsman, Player Bowler,
            Team battingTeam, Team bowlingTeam
    ) {
        return ScoreRecord.builder()
                .ballId(sequenceGeneratorService.getSequenceNumber(ScoreRecord.SEQUENCE_NAME))
                .matchId(matchId)
                .overCount(over)
                .ballOutcome(ballOutcome)
                .innings(innings)
                .totalRunsScoredByBattingTeam(totalRunsScoredAtThatInstance)
                .totalWicketsDown(totalWicketsLoseAtThatInstance)
                .batsman(Batsman)
                .bowler(Bowler)
                .battingTeam(battingTeam)
                .bowlingTeam(bowlingTeam)
                .build();
    }
}