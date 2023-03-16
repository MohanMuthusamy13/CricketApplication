package com.example.CricketApplication.service.auxilaryservices.initializematchservice;

import com.example.CricketApplication.entities.MatchStatusRecord;
import com.example.CricketApplication.utils.SequenceGeneratorService;
import com.example.CricketApplication.service.serviceinterfaces.MatchService;
import com.example.CricketApplication.service.serviceinterfaces.MatchStatusService;
import com.example.CricketApplication.service.auxilaryservices.majorgameservice.GameService;
import com.example.CricketApplication.service.auxilaryservices.runtrackerservice.ScoreService;
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
            put(GameService.getTeams().get(0).getTeamId(), ScoreService.getScoreOfBothTeams()[0]);
            put(GameService.getTeams().get(1).getTeamId(), ScoreService.getScoreOfBothTeams()[1]);
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