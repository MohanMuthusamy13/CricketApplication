package com.example.CricketApplication.service.auxilaryservices.balltrackerservice;

import com.example.CricketApplication.service.auxilaryservices.runtrackerservice.ScoreService;
import com.example.CricketApplication.service.auxilaryservices.majorgameservice.GameServiceImpl;
import com.example.CricketApplication.utils.Constants;
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
        ScoreService.addScore(GameServiceImpl.getBatting(), Constants.ILLEGAL_BALL_RUN);
        GameServiceImpl.getBowlingPlayer().setBallsBowled(ignoreBallCount);
        wideBalls++;
    }

    public static void noBallTracker() {
        ScoreService.addScore(GameServiceImpl.getBatting(), Constants.ILLEGAL_BALL_RUN);
        GameServiceImpl.getBowlingPlayer().setBallsBowled(ignoreBallCount);
        noBalls++;
    }
}