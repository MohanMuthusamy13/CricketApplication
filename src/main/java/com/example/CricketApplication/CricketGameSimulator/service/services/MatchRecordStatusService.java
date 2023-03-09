package com.example.CricketApplication.CricketGameSimulator.service.services;

import com.example.CricketApplication.CricketGameSimulator.entities.MatchStatusRecord;
import com.example.CricketApplication.CricketGameSimulator.service.auxillaryServices.SequenceGeneratorService;
import com.example.CricketApplication.CricketGameSimulator.service.repositoriesService.serviceinterfaces.MatchStatusService;
import com.example.CricketApplication.CricketGameSimulator.service.services.majorgameservice.GameServiceImpl;
import com.example.CricketApplication.CricketGameSimulator.service.services.scoreservice.ScoreModel;
import com.example.CricketApplication.CricketGameSimulator.view.ScoreBoardDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MatchRecordStatusService {

    @Autowired
    MatchStatusService matchStatusService;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    ScoreBoardDisplay scoreBoardDisplay;

    public void saveMatchStatusRecord(long matchId) {
        Map<Long, Integer> scoreUtilityMap = new HashMap<>() {{
            put(GameServiceImpl.getTeams().get(0).getTeamId(), ScoreModel.getScoreOfBothTeams()[0]);
            put(GameServiceImpl.getTeams().get(1).getTeamId(), ScoreModel.getScoreOfBothTeams()[1]);
        }};

        MatchStatusRecord matchStatusRecord = MatchStatusRecord.builder()
                .matchStatusRecordId(sequenceGeneratorService.getSequenceNumber(MatchStatusRecord.SEQUENCE_NAME))
                .matchId(matchId)
                .matchStatus(scoreBoardDisplay.getResults())
                .scoreOfBothTeams(scoreUtilityMap)
                .build();

        matchStatusService.save(matchStatusRecord);
    }
}