package com.example.CricketApplication.ApplicationPackages.service.services.overservice;

import com.example.CricketApplication.ApplicationPackages.entities.Player;
import com.example.CricketApplication.ApplicationPackages.service.services.majorgameservice.GameServiceImpl;
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
        overCount = 0;
        ballsCount = 0;
    }

    public static void IncreaseBallCount() {
        if (ballsCount == 5) {
            overCount++;
            ballsCount = 0;
        }
        else {
            ballsCount++;
        }
    }

    public static void BowlingStarts() {

        Player currentBowler = GameServiceImpl.getBowlingPlayer();

        if (tempBallCount < 6) {
            tempBallCount++;
            currentBowler.setBallsBowled(1);
        }
        else {
            currentBowler.setActiveStatus("inactive");
            GameServiceImpl.setNextBowler();
            currentBowler = GameServiceImpl.getBowlingPlayer();
            currentBowler.setActiveStatus("active");

            currentBowler.setBallsBowled(1);
            tempBallCount = 1;
        }
    }
}