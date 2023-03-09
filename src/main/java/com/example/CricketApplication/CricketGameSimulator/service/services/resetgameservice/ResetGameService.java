package com.example.CricketApplication.CricketGameSimulator.service.services.resetgameservice;

import com.example.CricketApplication.CricketGameSimulator.service.services.scoreservice.ScoreModel;
import com.example.CricketApplication.CricketGameSimulator.service.services.majorgameservice.GameServiceImpl;
import com.example.CricketApplication.CricketGameSimulator.service.services.illegalballservice.IllegalBallTrackerService;
import com.example.CricketApplication.CricketGameSimulator.service.services.overservice.OverService;
import com.example.CricketApplication.CricketGameSimulator.service.services.windeclarativeservices.WicketStatusProvider;

public class ResetGameService {

    public static void resetGame() {
        GameServiceImpl.setBatting(0);
        GameServiceImpl.setBowling(0);
        GameServiceImpl.setInnings(1);
        GameServiceImpl.setCurrentBatter(0);
        GameServiceImpl.setCurrentBowler(7);
        GameServiceImpl.setWickets(0);
        GameServiceImpl.setFlagForTeamWinningIndicationOnSecondInnings("");
        GameServiceImpl.setScoreTeams(new int[2]);

        ScoreModel.setScoreOfBothTeams(new int[2]);

        WicketStatusProvider.setWicketLose(0);
        WicketStatusProvider.setWicketFlag(false);

        OverService.setOverCount(0);
        OverService.setBallsCount(0);
        OverService.setTempBallCount(0);

        IllegalBallTrackerService.setWideBalls(0);
        IllegalBallTrackerService.setNoBalls(0);

    }

}