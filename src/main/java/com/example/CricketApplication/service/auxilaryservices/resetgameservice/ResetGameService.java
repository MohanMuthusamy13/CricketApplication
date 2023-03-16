package com.example.CricketApplication.service.auxilaryservices.resetgameservice;

import com.example.CricketApplication.service.auxilaryservices.runtrackerservice.ScoreService;
import com.example.CricketApplication.service.auxilaryservices.majorgameservice.GameServiceImpl;
import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.IllegalBallTrackerService;
import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.OverService;
import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.WicketStatusProvider;
import com.example.CricketApplication.utils.Constants;

public class ResetGameService {

    public static void setToInitialState() {
        GameServiceImpl.setBatting(Constants.INITIAL_STATE);
        GameServiceImpl.setBowling(Constants.INITIAL_STATE);
        GameServiceImpl.setInnings(Constants.FIRST_INNINGS);
        GameServiceImpl.setCurrentBatter(Constants.FIRST_BATTER_IN_TEAM);
        GameServiceImpl.setCurrentBowler(Constants.FIRST_BOWLER_IN_TEAM);
        GameServiceImpl.setWickets(Constants.INITIAL_WICKETS_TAKEN);
    }

    public static void resetGame() {

        setToInitialState();
        GameServiceImpl.setFlagForTeamWinningIndicationOnSecondInnings("");
        GameServiceImpl.setScoreTeams(new int[2]);

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