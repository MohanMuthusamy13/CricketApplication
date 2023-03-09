package com.example.CricketApplication.CricketGameSimulator.service.services.majorgameservice;

import com.example.CricketApplication.CricketGameSimulator.entities.*;
import com.example.CricketApplication.CricketGameSimulator.entities.builders.PlayerBuilder;
import com.example.CricketApplication.CricketGameSimulator.entities.builders.ScoreRecordBuilder;
import com.example.CricketApplication.CricketGameSimulator.repositories.TeamRepository;
import com.example.CricketApplication.CricketGameSimulator.service.auxillaryServices.SequenceGeneratorService;
import com.example.CricketApplication.CricketGameSimulator.service.repositoriesService.serviceimplementation.MatchServiceImpl;
import com.example.CricketApplication.CricketGameSimulator.service.repositoriesService.serviceimplementation.ScoreRecorderServiceImpl;
import com.example.CricketApplication.CricketGameSimulator.service.repositoriesService.serviceimplementation.TeamServiceImpl;
import com.example.CricketApplication.CricketGameSimulator.service.repositoriesService.serviceinterfaces.MatchStatusService;
import com.example.CricketApplication.CricketGameSimulator.service.services.MatchRecordStatusService;
import com.example.CricketApplication.CricketGameSimulator.service.services.overservice.OverService;
import com.example.CricketApplication.CricketGameSimulator.service.services.matchformatservice.MatchFormatService;
import com.example.CricketApplication.CricketGameSimulator.service.services.playerservice.PlayerStatsRecorder;
import com.example.CricketApplication.CricketGameSimulator.service.services.playerservice.PlayersService;
import com.example.CricketApplication.CricketGameSimulator.service.services.illegalballservice.IllegalBallTrackerService;
import com.example.CricketApplication.CricketGameSimulator.service.services.resetgameservice.ResetGameService;
import com.example.CricketApplication.CricketGameSimulator.service.services.runservice.RunsGenerator;
import com.example.CricketApplication.CricketGameSimulator.entities.builders.TeamBuilder;
import com.example.CricketApplication.CricketGameSimulator.service.services.scoreservice.PlayerCenturyAndHalfCenturyService;
import com.example.CricketApplication.CricketGameSimulator.service.services.scoreservice.ScoreModel;
import com.example.CricketApplication.CricketGameSimulator.service.services.teamservices.TeamSelectorService;
import com.example.CricketApplication.CricketGameSimulator.service.services.tossservice.TossService;
import com.example.CricketApplication.CricketGameSimulator.service.services.windeclarativeservices.WicketStatusProvider;
import com.example.CricketApplication.CricketGameSimulator.service.services.windeclarativeservices.WinningStatusProvider;
import com.example.CricketApplication.CricketGameSimulator.view.ScoreBoardDisplay;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Data
@Component
public class GameServiceImpl implements GameService {

    @Getter
    private static List playingTeamsPlayers;

    @Getter
    private static List playingTeams;

    private static List scoreRecords = new ArrayList<>();

    private static int currentBatter;

    private static int currentBowler = 7;

    @Getter @Setter
    private static int[] scoreTeams = new int[2];

    @Getter @Setter
    private static int Innings;

    @Getter @Setter
    private static int Batting;

    @Setter
    private static int Bowling;

    @Getter @Setter
    private static int totalOvers;

    @Setter
    private static boolean legalBallFlag = false;

    private static String matchFormat;

    @Getter @Setter
    private static String flagForTeamWinningIndicationOnSecondInnings = "";

    @Setter
    private static int wickets;

    private static long tempMatchId;

    @Getter @Setter
    private static int runsScorePerBall;
    @Getter
    private static String plannedMatchFormat;

    private static Map<Long, Integer> scoreUtilityMap;

    @Autowired
    private MatchStatusService matchStatusService;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    static Scanner sc = new Scanner(System.in);

    private Team team1;
    private Team team2;

    @Getter
    private static List<Team> teams = new ArrayList<>();

    private static Match matchTeams;

    @Autowired
    ScoreModel score;
    @Autowired
    WicketStatusProvider wicketTracker;
    @Autowired
    ScoreBoardDisplay scoreBoardDisplay;
    @Autowired
    RunsGenerator runsGenerator;
    @Autowired
    PlayersService playersTeamService;
    @Autowired
    TeamSelectorService teamSelectorService;
    WinningStatusProvider checkWinning = new WinningStatusProvider();

    @Autowired
    MatchRecordStatusService statusService;
    @Autowired
    PlayerStatsRecorder playerStatsRecorder;

    @Autowired
    TeamBuilder teamCreationService;
    @Autowired
    TeamServiceImpl teamRepositoryService;
    @Autowired
    ScoreRecorderServiceImpl scoreRecorderRepositoryService;
    @Autowired
    MatchServiceImpl matchRepositoryService;

    @Autowired
    ScoreBuilder scoreBuilder;
    private final TeamRepository teamRepository;

    @Autowired
    ScoreRecordBuilder scoreRecordBuilder;

    PlayerBuilder playerBuilder = new PlayerBuilder();


    public GameServiceImpl(TeamRepository teamRepository) {
        gameServiceProvider();
        this.teamRepository = teamRepository;
    }

    public void gameServiceProvider() {
        new ScoreModel().setScoreOfBothTeams(new int[2]);
        GameServiceImpl.scoreTeams = new ScoreModel().getScoreOfBothTeams();
        GameServiceImpl.wickets = WicketStatusProvider.getWicketLose();
        GameServiceImpl.Innings = 1;
    }

    public static Player getBattingPlayer() {
        return ((ArrayList<Player>) playingTeamsPlayers.get(Batting)).get(currentBatter);
    }

    public static Player getBowlingPlayer() {
        return ((ArrayList<Player>) playingTeamsPlayers.get(Math.abs(1 - Batting))).get(currentBowler);
    }


    public void wicketTrackerFunc() {
        wicketTracker.gotWicket();
    }

    public static void setCurrentBatter(int currentBatter) {
        getBattingPlayer().setActiveStatus("active");
        GameServiceImpl.currentBatter = currentBatter;
    }

    public static void setCurrentBowler(int currentBowler) {
        getBowlingPlayer().setActiveStatus("active");
        GameServiceImpl.currentBowler = currentBowler;
    }

    public static void setNextBowler() {
        if (currentBowler < 10) {
            currentBowler += 1;
        }
        else {
            setCurrentBowler(7);
        }
    }

    public static void setNextBatter() {
        GameServiceImpl.currentBatter += 1;
    }

    public void addScoreToBatter(int runsScorePerBall) {
        getBattingPlayer().setScore(runsScorePerBall);
        getBattingPlayer().setBallsFaced(1);
    }

    public void setActiveStatusForPlayers() {
        getBattingPlayer().setActiveStatus("active");
        getBowlingPlayer().setActiveStatus("active");
    }

    public void saveScoreRecord() {
        String overCount = String.valueOf(OverService.getOverCount()) + "." + OverService.getBallsCount();

        scoreRecorderRepositoryService.saveScoreRecordPerBall(
                scoreRecordBuilder.build(
                        tempMatchId,
                        overCount,
                        scoreBoardDisplay.runsForDisplayProvider(runsScorePerBall),
                        Innings,
                        score.getScoreOfBothTeams()[Batting],
                        WicketStatusProvider.getWicketLose(),
                        getBattingPlayer(),
                        getBowlingPlayer(),
                        teams.get(Batting),
                        teams.get(Math.abs(1 - Batting))
                )
        );
    }

    public String matchFormatScheduler(String matchFormat) {
        plannedMatchFormat = matchFormat.toUpperCase();
        GameServiceImpl.setTotalOvers(MatchFormatService.oversScheduler(plannedMatchFormat));
        return plannedMatchFormat;
    }

    public void startBattingAndBowling() {
        runsScorePerBall = runsGenerator.runsGeneratorByAbility(getBattingPlayer().getBaseAbility());
        if (runsScorePerBall == -1) {
            wicketTrackerFunc();
            GameServiceImpl.setLegalBallFlag(true);
        }
        else if (runsScorePerBall == -2) {
            IllegalBallTrackerService.wideTracker();
            GameServiceImpl.setLegalBallFlag(false);
        }
        else if (runsScorePerBall == -3) {
            IllegalBallTrackerService.noBallTracker();
            GameServiceImpl.setLegalBallFlag(false);
        }
        else {
            ScoreModel.addScore(Batting, runsScorePerBall);
            addScoreToBatter(runsScorePerBall);
            GameServiceImpl.setLegalBallFlag(true);
        }

        if (legalBallFlag) {
            OverService.BowlingStarts();
        }

        if (checkWinning.checkWinningStatusNumber() != 3) {
            checkWinning.checkWinningStatus();
            scoreBoardDisplay.showFinalScoreBoard();
            System.exit(0);
        }
        else {
            if (legalBallFlag)
                OverService.IncreaseBallCount();
        }

        if (Innings == 2) {
            if (!checkWinning.checkWinningStatusForSecondInnings().equals("")){
                flagForTeamWinningIndicationOnSecondInnings = "Game Over";
            }
        }
        scoreBoardDisplay.showStatusPerBall();
        saveScoreRecord();

    }

    public List playingTeamPlayersProvider(long matchId) throws Exception {
        matchTeams = matchRepositoryService.getMatchById(matchId);
        playingTeams = new ArrayList<>();
        List<Team> playingTeams =  matchTeams.getTeamsPlayed();
        tempMatchId = matchId;
        teams = teamSelectorService.teamSelector(
                playingTeams.get(0).getTeamId(),
                playingTeams.get(1).getTeamId()
        );

        playingTeamsPlayers = List.of(teams.get(0).getPlayers(), teams.get(1).getPlayers());
        return playingTeamsPlayers;
    }


    public String startGame(long matchId) throws Exception {
        playingTeamsPlayers = playingTeamPlayersProvider(matchId);
        TossService.startTossing();
        setActiveStatusForPlayers();


        while (Innings <= 2) {
            if ((WicketStatusProvider.getWicketLose() > 9 || OverService.getOverCount() == totalOvers) && Innings == 1) {
                wicketTracker.startSecondInnings();
            }
            else if ((Innings == 2 && (OverService.getOverCount() == totalOvers)) ||
                    ((GameServiceImpl.flagForTeamWinningIndicationOnSecondInnings).equals("Game Over")) ||
                    (WicketStatusProvider.getWicketLose() >= 9 && WicketStatusProvider.isWicketFlag())){
                scoreBoardDisplay.showScoreOfBothTeams();
                checkWinning.checkWinningStatus();
                break;
            }
            else {
                startBattingAndBowling();
            }
        }

        matchTeams.setTeamsPlayed(getTeams());
        matchTeams.setMatchFormat(getPlannedMatchFormat());
        PlayerCenturyAndHalfCenturyService.centuryStatsProvider(playingTeamsPlayers);
        matchRepositoryService.updateMatch(matchId, matchTeams);
        statusService.saveMatchStatusRecord(matchId);
        playerStatsRecorder.savePlayerStats(playingTeamsPlayers);
        ResetGameService.resetGame();
        return "Match has Completed";
    }
}