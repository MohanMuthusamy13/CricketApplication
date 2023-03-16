package com.example.CricketApplication.service.auxilaryservices.balltrackerservice;

import com.example.CricketApplication.service.auxilaryservices.majorgameservice.GameService;
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
            if (GameService.getInnings() == Constants.SECOND_INNINGS) {
                allWicketsDownInSecondInnings = true;
            }
            else {
                startSecondInnings();
            }
        }
        else {
            wicketLose++;
            GameService.getBattingPlayer().setBallsFaced(Constants.INCREASE_BALL_COUNT);
            GameService.getBattingPlayer().setActiveStatus("inactive");
            GameService.setNextBatter();
            GameService.getBattingPlayer().setActiveStatus("active");
            GameService.getBowlingPlayer().setWicketsTaken(Constants.INCREASE_WICKET_COUNT);
        }
    }

    public void startSecondInnings() {

        ScoreBoardDisplay.printFirstInningsFinalScore();
        System.out.println("\nStarted 2nd Innings :)");
        GameService.getBattingPlayer().setActiveStatus("inactive");
        GameService.getBowlingPlayer().setActiveStatus("inactive");
        GameService.setInnings(Constants.SECOND_INNINGS);
        wicketLose = Constants.INITIAL_WICKET_LOSE;
        GameService.setBatting(Math.abs(1 - GameService.getBatting()));
        score.setCurrentScore(Constants.INITIAL_SCORE);
        OverService.startFromFirstOver();
        GameService.setCurrentBatter(Constants.FIRST_BATTER_IN_TEAM);
        OverService.setTempBallCount(Constants.INITIAL_BALL_COUNT);
        GameService.setCurrentBowler(Constants.FIRST_BOWLER_IN_TEAM);
        GameService.setRunsScorePerBall(Constants.INITIAL_SCORE);
        System.out.printf(
                "The over got initialized %d.%d%n",
                OverService.getOverCount(), OverService.getBallsCount()
        );
    }
}