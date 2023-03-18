package com.example.CricketApplication.service.auxilaryservices.initializematchservice;

import com.example.CricketApplication.service.auxilaryservices.runtrackerservice.ScoreService;
import com.example.CricketApplication.service.auxilaryservices.majorgameservice.GameStarter;
import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.IllegalBallTrackerService;
import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.OverService;
import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.WicketStatusProvider;
import com.example.CricketApplication.utils.Constants;

public class ResetGameService {

    public static void resetGame() {
        setToInitialState();
        initiateResetService();
    }

    public static void setToInitialState() {
        GameStarter.setBattingTeamIndicator(Constants.INITIAL_STATE);
        GameStarter.setBowlingTeamIndicator(Constants.INITIAL_STATE);
        GameStarter.setInnings(Constants.FIRST_INNINGS);
        GameStarter.setCurrentBatter(Constants.FIRST_BATTER_IN_TEAM);
        GameStarter.setCurrentBowler(Constants.FIRST_BOWLER_IN_TEAM);
        GameStarter.setWickets(Constants.INITIAL_WICKETS_TAKEN);
    }

    public static void initiateResetService() {
        GameStarter.setFlagForTeamWinningIndicationOnSecondInnings("");
        GameStarter.setScoreTeams(new int[2]);
        ScoreService.setScoreOfBothTeams(new int[2]);
        WicketStatusProvider.setWicketLose(Constants.INITIAL_WICKET_LOSE);
        WicketStatusProvider.setAllWicketsDownInSecondInnings(false);
        OverService.setOverCount(Constants.INITIAL_OVER_COUNT);
        OverService.setBallsCount(Constants.INITIAL_BALL_COUNT);
        OverService.setTempBallCount(Constants.INITIAL_BALL_COUNT);
        IllegalBallTrackerService.setWideBalls(Constants.INITIAL_STATE);
        IllegalBallTrackerService.setNoBalls(Constants.INITIAL_STATE);
    }
}