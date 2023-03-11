package com.example.CricketApplication.cricketgamesimulator.service.services.overservice;

import com.example.CricketApplication.cricketgamesimulator.entities.Player;
import com.example.CricketApplication.cricketgamesimulator.service.services.majorgameservice.GameServiceImpl;
import com.example.CricketApplication.cricketgamesimulator.utils.Constants;
import lombok.Getter;
import lombok.Setter;

public class OverService {

    @Getter @Setter
    private static int overCount;
    @Getter @Setter
    private static int ballsCount;
    @Setter
    private static int tempBallCount;

    private OverService() {}

    public static String getOverInString() {
        return String.format("%d.%d", overCount, ballsCount);
    }

    public static void startFromFirstOver() {
        overCount = Constants.INITIAL_OVER_COUNT;
        ballsCount = Constants.INITIAL_BALL_COUNT;
    }

    public static void IncreaseBallCount() {
        if (ballsCount == Constants.LAST_BALL_OF_OVER) {
            overCount++;
            ballsCount = Constants.INITIAL_BALL_COUNT;
        }
        else {
            ballsCount++;
        }
    }

    public static void BowlingStarts() {

        Player currentBowler = GameServiceImpl.getBowlingPlayer();

        if (tempBallCount < Constants.OVER_FINISHED) {
            tempBallCount++;
            currentBowler.setBallsBowled(Constants.INCREASE_BALL_COUNT);
        }
        else {
            currentBowler.setActiveStatus("inactive");
            GameServiceImpl.setNextBowler();
            currentBowler = GameServiceImpl.getBowlingPlayer();
            currentBowler.setActiveStatus("active");
            currentBowler.setBallsBowled(Constants.INCREASE_BALL_COUNT);
            tempBallCount = 1;
        }
    }
}