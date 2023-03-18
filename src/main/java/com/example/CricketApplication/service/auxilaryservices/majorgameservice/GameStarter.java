package com.example.CricketApplication.service.auxilaryservices.majorgameservice;

import com.example.CricketApplication.entities.*;
import com.example.CricketApplication.repositories.repositoryImpl.MatchRepositoryImpl;
import com.example.CricketApplication.repositories.repositoryImpl.TeamRepository;
import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.OverService;
import com.example.CricketApplication.service.auxilaryservices.playerservice.AuxiliaryPlayerService;
import com.example.CricketApplication.service.auxilaryservices.initializematchservice.ResetGameService;
import com.example.CricketApplication.service.auxilaryservices.runtrackerservice.ScoreService;
import com.example.CricketApplication.service.auxilaryservices.initializematchservice.TossService;
import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.WicketStatusProvider;
import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.WinningStatusProvider;
import com.example.CricketApplication.utils.Constants;
import com.example.CricketApplication.view.ScoreBoardDisplay;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Data
@Service
public class GameStarter {

    @Getter @Setter
    private static List playingTeamsPlayers;
    @Getter @Setter
    private static List playingTeams;
    @Getter
    private static int currentBatter;
    @Getter
    private static int currentBowler = Constants.FIRST_BOWLER_IN_TEAM;
    @Getter @Setter
    private static int[] scoreTeams = new int[2];
    @Getter @Setter
    private static int innings;
    @Getter @Setter
    private static int battingTeamIndicator;
    @Setter
    private static int bowlingTeamIndicator;
    @Getter @Setter
    private static int totalOvers;
    private static String matchFormat;
    @Getter @Setter
    private static String flagForTeamWinningIndicationOnSecondInnings = "";
    @Setter
    private static int wickets;
    @Getter @Setter
    private static long tempMatchId;
    @Getter @Setter
    private static List<Team> teams = new ArrayList<>();
    @Getter @Setter
    private static Match matchTeams;
    private final TeamRepository teamRepository;

    private List<ScoreRecord> scoreRecords = new ArrayList<>();
    @Autowired
    private GamePlay gamePlay;
    @Autowired
    AuxiliaryPlayerService auxiliaryPlayerService;
    @Autowired
    WicketStatusProvider wicketTracker;
    @Autowired
    ScoreBoardDisplay scoreBoardDisplay;
    WinningStatusProvider checkWinning = new WinningStatusProvider();
    private final MatchRepositoryImpl matchRepositoryImpl;

    public GameStarter(TeamRepository teamRepository,
                       MatchRepositoryImpl matchRepositoryImpl) {
        gameServiceProvider();
        this.teamRepository = teamRepository;
        this.matchRepositoryImpl = matchRepositoryImpl;
    }

    public void gameServiceProvider() {
        ScoreService.setScoreOfBothTeams(new int[2]);
        GameStarter.scoreTeams = ScoreService.getScoreOfBothTeams();
        GameStarter.wickets = WicketStatusProvider.getWicketLose();
        GameStarter.innings = Constants.FIRST_INNINGS;
    }

    public static Player getBattingPlayer() {
        return ((ArrayList<Player>) playingTeamsPlayers.get(battingTeamIndicator)).get(currentBatter);
    }

    public static Player getBowlingPlayer() {
        return ((ArrayList<Player>) playingTeamsPlayers.get(Math.abs(1 - battingTeamIndicator))).get(currentBowler);
    }

    public static void setCurrentBatter(int currentBatter) {
        getBattingPlayer().setActiveStatus("active");
        GameStarter.currentBatter = currentBatter;
    }

    public static void setCurrentBowler(int currentBowler) {
        getBowlingPlayer().setActiveStatus("active");
        GameStarter.currentBowler = currentBowler;
    }

    public static void setNextBowler() {
        if (currentBowler < Constants.LAST_BOWLER_IN_TEAM) {
            currentBowler += 1;
        }
        else {
            setCurrentBowler(Constants.FIRST_BOWLER_IN_TEAM);
        }
    }

    public static void setNextBatter() {
        GameStarter.currentBatter += 1;
    }

    public void startGame(long matchId, String type) throws Exception {
        if (!ResumeMatchService.isResumed) {
            auxiliaryPlayerService.playingTeamPlayersProvider(matchId);
            TossService.startTossing();
        }
        tempMatchId = matchId;
        AuxiliaryPlayerService.setActiveStatusForPlayers();

        while (innings <= Constants.SECOND_INNINGS) {
            if ((WicketStatusProvider.getWicketLose() > Constants.LAST_WICKET || OverService.getOverCount() == totalOvers)
                    && innings == Constants.FIRST_INNINGS) {
                wicketTracker.startSecondInnings();
            }
            else if ((innings == Constants.SECOND_INNINGS && (OverService.getOverCount() == totalOvers)) ||
                    ((GameStarter.flagForTeamWinningIndicationOnSecondInnings).equals("MATCH ENDED")) ||
                    (WicketStatusProvider.getWicketLose() >= Constants.LAST_WICKET
                            && WicketStatusProvider.isAllWicketsDownInSecondInnings())){
                scoreBoardDisplay.showScoreOfBothTeams();
                checkWinning.checkWinningStatus();
                break;
            }
            else {
                gamePlay.startBattingAndBowling(type);
            }
        }
        GameStarter.getMatchTeams().setMatchFinished(true);
        auxiliaryPlayerService.saveMatchStatus(matchId);
        auxiliaryPlayerService.updateStatsAndScores(matchId);
        if (type.equals("MATCH AT A TIME")) {
            auxiliaryPlayerService.saveScoreRecords(scoreRecords);
        }
        ResetGameService.resetGame();
    }
}