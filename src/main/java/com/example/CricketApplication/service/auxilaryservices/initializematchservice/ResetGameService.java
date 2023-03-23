package com.example.CricketApplication.service.auxilaryservices.initializematchservice;

import com.example.CricketApplication.service.auxilaryservices.runtrackerservice.ScoreService;
import com.example.CricketApplication.service.auxilaryservices.majorgameservice.GameStarter;
import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.IllegalBallTrackerService;
import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.OverService;
import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.WicketStatusProvider;
import com.example.CricketApplication.utils.Constants;
import static com.example.CricketApplication.utils.Constants.*;

public class ResetGameService {

    public static void resetGame() {
        setToInitialState();
        initiateResetService();
    }

    public static void setToInitialState() {
        GameStarter.setBattingTeamIndicator(INITIAL_STATE);
        GameStarter.setBowlingTeamIndicator(INITIAL_STATE);
        GameStarter.setInnings(FIRST_INNINGS);
        GameStarter.setCurrentBatter(FIRST_BATTER_IN_TEAM);
        GameStarter.setCurrentBowler(FIRST_BOWLER_IN_TEAM);
        GameStarter.setWickets(INITIAL_WICKETS_TAKEN);
    }

    public static void initiateResetService() {
        GameStarter.setFlagForTeamWinningIndicationOnSecondInnings("");
        GameStarter.setScoreTeams(new int[2]);
        ScoreService.setScoreOfBothTeams(new int[2]);
        WicketStatusProvider.setWicketLose(INITIAL_WICKET_LOSE);
        WicketStatusProvider.setAllWicketsDownInSecondInnings(false);
        OverService.setOverCount(INITIAL_OVER_COUNT);
        OverService.setBallsCount(INITIAL_BALL_COUNT);
        OverService.setTempBallCount(INITIAL_BALL_COUNT);
        IllegalBallTrackerService.setWideBalls(INITIAL_STATE);
        IllegalBallTrackerService.setNoBalls(INITIAL_STATE);
    }
}