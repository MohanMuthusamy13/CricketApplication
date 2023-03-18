package com.example.CricketApplication.utils.builders;

import com.example.CricketApplication.entities.MatchStateSaver;
import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.OverService;
import com.example.CricketApplication.service.auxilaryservices.initializematchservice.MatchFormatService;
import com.example.CricketApplication.service.auxilaryservices.majorgameservice.GameStarter;
import com.example.CricketApplication.service.auxilaryservices.runtrackerservice.ScoreService;
import com.example.CricketApplication.utils.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchStatusSaverBuilder {

    private final SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private MatchStatusSaverBuilder(SequenceGeneratorService sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    public MatchStateSaver buildMatchState() {
        return MatchStateSaver.builder()
                .id(sequenceGeneratorService.getSequenceNumber(MatchStateSaver.SEQUENCE_NAME))
                .matchId(GameStarter.getTempMatchId())
                .overCount(OverService.getOverCount())
                .ballCount(OverService.getBallsCount())
                .matchFormat(MatchFormatService.getPlannedMatchFormat())
                .innings(GameStarter.getInnings())
                .score(ScoreService.getScoreOfBothTeams())
                .batting(GameStarter.getBatting())
                .bowling(Math.abs(1 - GameStarter.getBatting()))
                .currentBatter(GameStarter.getCurrentBatter())
                .currentBowler(GameStarter.getCurrentBowler())
                .totalOvers(GameStarter.getTotalOvers())
                .build();
    }
}