package com.example.CricketApplication.service.auxilaryservices.majorgameservice;

import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.IllegalBallTrackerService;
import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.WicketStatusProvider;
import com.example.CricketApplication.service.auxilaryservices.playerservice.AuxiliaryPlayerService;
import com.example.CricketApplication.service.auxilaryservices.runtrackerservice.RunsGenerator;
import com.example.CricketApplication.service.auxilaryservices.runtrackerservice.ScoreService;
import com.example.CricketApplication.utils.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.example.CricketApplication.utils.Constants.*;

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
            case WICKET:
                wicketTracker.gotWicket();
                GamePlay.setLegalBallFlag(true);
                break;
            case WIDE:
                IllegalBallTrackerService.wideTracker();
                GamePlay.setLegalBallFlag(false);
                break;
            case NO_BALL:
                IllegalBallTrackerService.noBallTracker();
                GamePlay.setLegalBallFlag(false);
                break;
            case DOT_BALL, ONE_RUN,
                    TWO_RUNS, THREE_RUNS, FIVE_RUNS:
                scoreAccumulator(GameStarter.getBattingTeamIndicator(), runsScorePerBall);
                break;
            case BOUNDARY:
                GameStarter.getBattingPlayer().setNoOfFours(1);
                scoreAccumulator(GameStarter.getBattingTeamIndicator(), runsScorePerBall);
                break;
            case SIX:
                GameStarter.getBattingPlayer().setNoOfSixes(1);
                scoreAccumulator(GameStarter.getBattingTeamIndicator(), runsScorePerBall);
                break;
        }
        playService.checkLegalBallAndUpdateStats();
    }
}