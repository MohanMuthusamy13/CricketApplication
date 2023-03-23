package com.example.CricketApplication.service.auxilaryservices.majorgameservice;

import com.example.CricketApplication.entities.ScoreRecord;
import com.example.CricketApplication.repositories.MatchStatusSaverRepository;
import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.OverService;
import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.WinningStatusProvider;
import com.example.CricketApplication.service.auxilaryservices.playerservice.AuxiliaryPlayerService;
import com.example.CricketApplication.utils.Constants;
import com.example.CricketApplication.utils.builders.MatchStatusSaverBuilder;
import com.example.CricketApplication.view.ScoreBoardDisplay;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LegalBallChecker {

    @Autowired
    ScoreBoardDisplay scoreBoardDisplay;
    @Getter
    private static List<ScoreRecord> scoreRecords = new ArrayList<>();
    @Autowired
    AuxiliaryPlayerService playerService;
    @Autowired
    MatchStatusSaverRepository saverRepository;
    WinningStatusProvider checkWinning = new WinningStatusProvider();
    @Autowired
    MatchStatusSaverBuilder matchStatusSaverBuilder;

    public void checkLegalBallAndUpdateStats() {
        if (GamePlay.isLegalBallFlag()) {
            OverService.BowlingStarts();
        }

        if (checkWinning.checkWinningStatusNumber() != 3) {
            checkWinning.checkWinningStatus();
            scoreBoardDisplay.showFinalScoreBoard();
            System.exit(0);
        }
        else {
            if (GamePlay.isLegalBallFlag())
                OverService.IncreaseBallCount();
        }

        if (GameStarter.getInnings() == Constants.SECOND_INNINGS &&
                !checkWinning.checkWinningStatusForSecondInnings().equals("")) {
            GameStarter.setFlagForTeamWinningIndicationOnSecondInnings("MATCH ENDED");
        }
        scoreBoardDisplay.showStatusPerBall();
        ScoreRecord scoreRecord = playerService.buildScoreRecord(GamePlay.getRunsScorePerBall());
        if (GamePlay.getMatchType().equals("MATCH AT A TIME")) {
            scoreRecords.add(scoreRecord);
        }
        else {
            playerService.saveScoreRecord(scoreRecord);
            saverRepository.save(matchStatusSaverBuilder.buildMatchState());
        }
    }
}