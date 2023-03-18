package com.example.CricketApplication.service.auxilaryservices.majorgameservice;

import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.IllegalBallTrackerService;
import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.OverService;
import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.WicketStatusProvider;
import com.example.CricketApplication.service.auxilaryservices.playerservice.AuxiliaryPlayerService;
import com.example.CricketApplication.service.auxilaryservices.runtrackerservice.RunsGenerator;
import com.example.CricketApplication.service.auxilaryservices.runtrackerservice.ScoreService;
import com.example.CricketApplication.utils.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamePlay {

    @Autowired
    private LegalBallChecker playService;
    @Getter @Setter
    private static int runsScorePerBall;
    @Autowired
    WicketStatusProvider wicketTracker;
    @Autowired
    RunsGenerator runsGenerator;
    @Setter @Getter
    private static boolean legalBallFlag = false;
    @Getter @Setter
    private static String matchType;


    public void scoreAccumulator(int Batting, int runsScorePerBall) {
        ScoreService.addScore(Batting, runsScorePerBall);
        AuxiliaryPlayerService.addScoreToBatter(runsScorePerBall);
        GamePlay.setLegalBallFlag(true);
    }

    public void startBattingAndBowling(String type) throws Exception {
        runsScorePerBall = runsGenerator
                .runsGeneratorByAbility(GameStarter.getBattingPlayer().getBaseAbility());
        matchType = type;

        switch (runsScorePerBall) {
            case Constants.WICKET:
                wicketTracker.gotWicket();
                GamePlay.setLegalBallFlag(true);
                break;
            case Constants.WIDE:
                IllegalBallTrackerService.wideTracker();
                GamePlay.setLegalBallFlag(false);
                break;
            case Constants.NO_BALL:
                IllegalBallTrackerService.noBallTracker();
                GamePlay.setLegalBallFlag(false);
                break;
            case Constants.DOT_BALL, Constants.ONE_RUN,
                    Constants.TWO_RUNS, Constants.THREE_RUNS, Constants.FIVE_RUNS:
                scoreAccumulator(GameStarter.getBatting(), runsScorePerBall);
                break;
            case Constants.BOUNDARY:
                GameStarter.getBattingPlayer().setNoOfFours(1);
                scoreAccumulator(GameStarter.getBatting(), runsScorePerBall);
                break;
            case Constants.SIX:
                GameStarter.getBattingPlayer().setNoOfSixes(1);
                scoreAccumulator(GameStarter.getBatting(), runsScorePerBall);
                break;
        }
        playService.checkLegalBallAndUpdateStats();
    }
}