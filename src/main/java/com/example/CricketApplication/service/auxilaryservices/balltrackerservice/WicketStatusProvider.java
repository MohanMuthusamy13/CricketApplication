package com.example.CricketApplication.service.auxilaryservices.balltrackerservice;

import com.example.CricketApplication.service.auxilaryservices.majorgameservice.GamePlay;
import com.example.CricketApplication.service.auxilaryservices.majorgameservice.GameStarter;
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
            if (GameStarter.getInnings() == Constants.SECOND_INNINGS) {
                allWicketsDownInSecondInnings = true;
            }
            else {
                startSecondInnings();
            }
        }
        else {
            wicketLose++;
            GameStarter.getBattingPlayer().setBallsFaced(Constants.INCREASE_BALL_COUNT);
            GameStarter.getBattingPlayer().setActiveStatus("inactive");
            GameStarter.setNextBatter();
            GameStarter.getBattingPlayer().setActiveStatus("active");
            GameStarter.getBowlingPlayer().setWicketsTaken(Constants.INCREASE_WICKET_COUNT);
        }
    }

    public void startSecondInnings() {

        ScoreBoardDisplay.printFirstInningsFinalScore();
        System.out.println("\nStarted 2nd Innings :)");
        GameStarter.getBattingPlayer().setActiveStatus("inactive");
        GameStarter.getBowlingPlayer().setActiveStatus("inactive");
        GameStarter.setInnings(Constants.SECOND_INNINGS);
        wicketLose = Constants.INITIAL_WICKET_LOSE;
        GameStarter.setBattingTeamIndicator(Math.abs(1 - GameStarter.getBattingTeamIndicator()));
        score.setCurrentScore(Constants.INITIAL_SCORE);
        OverService.startFromFirstOver();
        GameStarter.setCurrentBatter(Constants.FIRST_BATTER_IN_TEAM);
        OverService.setTempBallCount(Constants.INITIAL_BALL_COUNT);
        GameStarter.setCurrentBowler(Constants.FIRST_BOWLER_IN_TEAM);
        GamePlay.setRunsScorePerBall(Constants.INITIAL_SCORE);
        System.out.printf(
                "The over got initialized %d.%d%n",
                OverService.getOverCount(), OverService.getBallsCount()
        );
    }
}