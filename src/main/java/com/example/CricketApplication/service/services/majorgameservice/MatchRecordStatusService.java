package com.example.CricketApplication.service.services.majorgameservice;

import com.example.CricketApplication.entities.MatchStatusRecord;
import com.example.CricketApplication.service.serviceinterfaces.MatchService;
import com.example.CricketApplication.service.serviceinterfaces.MatchStatusService;
import com.example.CricketApplication.service.services.majorgameservice.GameServiceImpl;
import com.example.CricketApplication.service.services.runtrackerservice.ScoreService;
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
    ScoreBoardDisplay scoreBoardDisplay;

    public void saveMatchStatusRecord(String matchId) {
        Map<String, Integer> scoreUtilityMap = new HashMap<>() {{
            put(GameServiceImpl.getTeams().get(0).getTeamId(), ScoreService.getScoreOfBothTeams()[0]);
            put(GameServiceImpl.getTeams().get(1).getTeamId(), ScoreService.getScoreOfBothTeams()[1]);
        }};

        MatchStatusRecord matchStatusRecord = MatchStatusRecord.builder()
                .matchId(matchId)
                .matchStatus(scoreBoardDisplay.getResults())
                .scoreOfBothTeams(scoreUtilityMap)
//                .maxScorer(matchService.getMaxScorerIdByMatch(matchId))
//                .maxWicketTaker(matchService.getWicketTakerIdByMatch(matchId))
                .build();

        matchStatusService.save(matchStatusRecord);
    }
}