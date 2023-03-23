package com.example.CricketApplication.service.auxilaryservices.majorgameservice;

import java.util.*;
import com.example.CricketApplication.entities.*;
import com.example.CricketApplication.repositories.MatchRepository;
import com.example.CricketApplication.repositories.TeamRepository;
import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.OverService;
import com.example.CricketApplication.service.auxilaryservices.playerservice.AuxiliaryPlayerService;
import com.example.CricketApplication.service.auxilaryservices.initializematchservice.ResetGameService;
import com.example.CricketApplication.service.auxilaryservices.runtrackerservice.ScoreService;
import com.example.CricketApplication.service.auxilaryservices.initializematchservice.TossService;
import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.WicketStatusProvider;
import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.WinningStatusProvider;
import com.example.CricketApplication.view.ScoreBoardDisplay;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.example.CricketApplication.utils.Constants.*;

@Slf4j
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
    private static int currentBowler = FIRST_BOWLER_IN_TEAM;
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
    @Setter
    private static List<ScoreRecord> scoreRecords = new ArrayList<>();
    @Autowired
    private GamePlay gamePlay;
    @Autowired
    AuxiliaryPlayerService auxiliaryPlayerService;
    @Autowired
    WicketStatusProvider wicketTracker;
    @Autowired
    ScoreBoardDisplay scoreBoardDisplay;
    WinningStatusProvider checkWinning = new WinningStatusProvider();
    private final MatchRepository matchRepositoryImpl;

    public GameStarter(TeamRepository teamRepository,
                       MatchRepository matchRepositoryImpl) {
        gameServiceProvider();
        this.teamRepository = teamRepository;
        this.matchRepositoryImpl = matchRepositoryImpl;
    }

    public void gameServiceProvider() {
        ScoreService.setScoreOfBothTeams(new int[2]);
        GameStarter.scoreTeams = ScoreService.getScoreOfBothTeams();
        GameStarter.wickets = WicketStatusProvider.getWicketLose();
        GameStarter.innings = FIRST_INNINGS;
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
        if (currentBowler < LAST_BOWLER_IN_TEAM) {
            currentBowler += 1;
        }
        else {
            setCurrentBowler(FIRST_BOWLER_IN_TEAM);
        }
    }

    public static void setNextBatter() {
        GameStarter.currentBatter += 1;
    }

    public void startGame(long matchId, String type) throws Exception {
        log.debug("Thread id {}", Thread.currentThread().getName());
        if (!ResumeMatchService.isResumed) {
            auxiliaryPlayerService.playingTeamPlayersProvider(matchId);
            TossService.startTossing();
        }
        tempMatchId = matchId;
        AuxiliaryPlayerService.setActiveStatusForPlayers();

        while (innings <= SECOND_INNINGS) {
            if ((WicketStatusProvider.getWicketLose() > LAST_WICKET || OverService.getOverCount() == totalOvers)
                    && innings == FIRST_INNINGS) {
                wicketTracker.startSecondInnings();
            }
            else if ((innings == SECOND_INNINGS && (OverService.getOverCount() == totalOvers)) ||
                    ((GameStarter.flagForTeamWinningIndicationOnSecondInnings).equals("MATCH ENDED")) ||
                    (WicketStatusProvider.getWicketLose() >= LAST_WICKET
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
            auxiliaryPlayerService.saveScoreRecords(LegalBallChecker.getScoreRecords());
        }
        ResetGameService.resetGame();
    }
}