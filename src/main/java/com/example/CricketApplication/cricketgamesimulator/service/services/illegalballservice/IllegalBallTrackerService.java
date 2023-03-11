package com.example.CricketApplication.cricketgamesimulator.service.services.illegalballservice;

import com.example.CricketApplication.cricketgamesimulator.service.services.scoreservice.ScoreModel;
import com.example.CricketApplication.cricketgamesimulator.service.services.majorgameservice.GameServiceImpl;
import com.example.CricketApplication.cricketgamesimulator.utils.Constants;
import lombok.Getter;
import lombok.Setter;

// MAKING THE CLASS NON-INITIALIZABLE AS THIS IS UTILITY CLASS
public class IllegalBallTrackerService {

    private static final int ignoreBallCount = -1;

    @Getter @Setter
    private static int wideBalls;
    @Getter @Setter
    private static int noBalls;

    private IllegalBallTrackerService() {}

    public static void wideTracker() {
        ScoreModel.addScore(GameServiceImpl.getBatting(), Constants.ILLEGAL_BALL_RUN);
        GameServiceImpl.getBowlingPlayer().setBallsBowled(ignoreBallCount);
        wideBalls++;
    }

    public static void noBallTracker() {
        ScoreModel.addScore(GameServiceImpl.getBatting(), Constants.ILLEGAL_BALL_RUN);
        GameServiceImpl.getBowlingPlayer().setBallsBowled(ignoreBallCount);
        noBalls++;
    }
}