package com.example.CricketApplication.service.services;

import com.example.CricketApplication.entities.MatchStatusRecord;
import com.example.CricketApplication.service.auxillaryservices.SequenceGeneratorService;
import com.example.CricketApplication.service.repositoriesservice.serviceinterfaces.MatchService;
import com.example.CricketApplication.service.repositoriesservice.serviceinterfaces.MatchStatusService;
import com.example.CricketApplication.service.services.majorgameservice.GameServiceImpl;
import com.example.CricketApplication.service.services.scoreservice.ScoreModel;
import com.example.CricketApplication.view.ScoreBoardDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MatchRecordStatusService {

    @Autowired
    MatchService matchService;

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