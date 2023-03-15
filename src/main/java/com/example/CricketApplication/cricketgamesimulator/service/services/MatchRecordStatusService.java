package com.example.CricketApplication.cricketgamesimulator.service.services;

import com.example.CricketApplication.cricketgamesimulator.entities.MatchStatusRecord;
import com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceinterfaces.MatchService;
import com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceinterfaces.MatchStatusService;
import com.example.CricketApplication.cricketgamesimulator.service.services.majorgameservice.GameServiceImpl;
import com.example.CricketApplication.cricketgamesimulator.service.services.scoreservice.ScoreModel;
import com.example.CricketApplication.cricketgamesimulator.view.ScoreBoardDisplay;
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
            put(GameServiceImpl.getTeams().get(0).getTeamId(), ScoreModel.getScoreOfBothTeams()[0]);
            put(GameServiceImpl.getTeams().get(1).getTeamId(), ScoreModel.getScoreOfBothTeams()[1]);
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