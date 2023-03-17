package com.example.CricketApplication.service.services.balltrackerservice;

import com.example.CricketApplication.service.services.majorgameservice.GameServiceImpl;
import com.example.CricketApplication.utils.Constants;
import com.example.CricketApplication.view.ScoreBoardDisplay;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class WicketStatusProvider extends WinningStatusProvider {

    @Getter @Setter
    private static int wicketLose;
    @Getter @Setter
    private static boolean allWicketsDownInSecondInnings = false;

    public void gotWicket() {
        if (wicketLose >= Constants.LAST_WICKET) {
            if (GameServiceImpl.getInnings() == Constants.SECOND_INNINGS) {
                allWicketsDownInSecondInnings = true;
            }
            else {
                startSecondInnings();
            }
        }
        else {
            wicketLose++;
            GameServiceImpl.getBattingPlayer().setBallsFaced(Constants.INCREASE_BALL_COUNT);
            GameServiceImpl.getBattingPlayer().setActiveStatus("inactive");
            GameServiceImpl.setNextBatter();
            GameServiceImpl.getBattingPlayer().setActiveStatus("active");
            GameServiceImpl.getBowlingPlayer().setWicketsTaken(Constants.INCREASE_WICKET_COUNT);
        }
    }

    public void startSecondInnings() {

        ScoreBoardDisplay.printFirstInningsFinalScore();
        System.out.println("\nStarted 2nd Innings :)");
        GameServiceImpl.getBattingPlayer().setActiveStatus("inactive");
        GameServiceImpl.getBowlingPlayer().setActiveStatus("inactive");
        GameServiceImpl.setInnings(Constants.SECOND_INNINGS);
        wicketLose = Constants.INITIAL_WICKET_LOSE;
        GameServiceImpl.setBatting(Math.abs(1 - GameServiceImpl.getBatting()));
        score.setCurrentScore(Constants.INITIAL_SCORE);
        OverService.startFromFirstOver();
        GameServiceImpl.setCurrentBatter(Constants.FIRST_BATTER_IN_TEAM);
        OverService.setTempBallCount(Constants.INITIAL_BALL_COUNT);
        GameServiceImpl.setCurrentBowler(Constants.FIRST_BOWLER_IN_TEAM);
        GameServiceImpl.setRunsScorePerBall(Constants.INITIAL_SCORE);
        System.out.printf(
                "The over got initialized %d.%d%n",
                OverService.getOverCount(), OverService.getBallsCount()
        );
    }
}