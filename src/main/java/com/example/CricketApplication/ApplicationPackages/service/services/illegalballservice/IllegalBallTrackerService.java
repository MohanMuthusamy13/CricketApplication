package com.example.CricketApplication.ApplicationPackages.service.services.illegalballservice;

import com.example.CricketApplication.ApplicationPackages.entities.ScoreModel;
import com.example.CricketApplication.ApplicationPackages.service.services.majorgameservice.GameServiceImpl;
import lombok.Getter;
import lombok.Setter;

// MAKING THE CLASS NON-INITIALIZABLE AS THIS IS UTILITY CLASS
public class IllegalBallTrackerService {

    private static final int ignoreBallCount = -1;

    @Getter
    @Setter
    private static int wideBalls;

    @Getter
    @Setter
    private static int noBalls;

    private IllegalBallTrackerService() {}

    public static void wideTracker() {
        ScoreModel.addScore(GameServiceImpl.getBatting(), 1);
        GameServiceImpl.getBowlingPlayer().setBallsBowled(ignoreBallCount);
        wideBalls++;
    }

    public static void noBallTracker() {
        ScoreModel.addScore(GameServiceImpl.getBatting(), 1);
        GameServiceImpl.getBowlingPlayer().setBallsBowled(ignoreBallCount);
        noBalls++;
    }
}