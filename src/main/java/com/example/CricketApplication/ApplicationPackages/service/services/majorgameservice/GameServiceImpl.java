package com.example.CricketApplication.ApplicationPackages.service.services.majorgameservice;

import com.example.CricketApplication.ApplicationPackages.entities.*;
import com.example.CricketApplication.ApplicationPackages.entities.builders.PlayerBuilder;
import com.example.CricketApplication.ApplicationPackages.entities.builders.ScoreRecordBuilder;
import com.example.CricketApplication.ApplicationPackages.repositories.TeamRepository;
import com.example.CricketApplication.ApplicationPackages.service.auxillaryServices.SequenceGeneratorService;
import com.example.CricketApplication.ApplicationPackages.service.repositoriesService.serviceimplementation.MatchServiceImpl;
import com.example.CricketApplication.ApplicationPackages.service.repositoriesService.serviceimplementation.ScoreRecorderServiceImpl;
import com.example.CricketApplication.ApplicationPackages.service.repositoriesService.serviceimplementation.TeamServiceImpl;
import com.example.CricketApplication.ApplicationPackages.service.repositoriesService.serviceinterfaces.MatchStatusService;
import com.example.CricketApplication.ApplicationPackages.service.services.overservice.OverService;
import com.example.CricketApplication.ApplicationPackages.service.services.matchformatservice.MatchFormatService;
import com.example.CricketApplication.ApplicationPackages.service.services.playerservice.PlayersService;
import com.example.CricketApplication.ApplicationPackages.service.services.illegalballservice.IllegalBallTrackerService;
import com.example.CricketApplication.ApplicationPackages.service.services.resetgameservice.ResetGameService;
import com.example.CricketApplication.ApplicationPackages.service.services.runservice.RunsGenerator;
import com.example.CricketApplication.ApplicationPackages.entities.builders.TeamBuilder;
import com.example.CricketApplication.ApplicationPackages.service.services.teamservices.TeamSelectorService;
import com.example.CricketApplication.ApplicationPackages.service.services.tossservice.TossService;
import com.example.CricketApplication.ApplicationPackages.service.services.windeclarativeservices.WicketStatusProvider;
import com.example.CricketApplication.ApplicationPackages.service.services.windeclarativeservices.WinningStatusProvider;
import com.example.CricketApplication.ApplicationPackages.view.ScoreBoardDisplay;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Data
@Component
public class GameServiceImpl implements GameService {
    private static List playingTeamsPlayers;
    private static List playingTeams;
    private static List scoreRecords = new ArrayList<>();
    private static int currentBatter;
    private static int currentBowler = 7;
    private static int[] scoreTeams = new int[2];
    private static int Innings;
    private static int Batting;
    private static int Bowling;
    private static int totalOvers;
    private static boolean legalBallFlag = false;
    private static String matchFormat;
    private static String flagForTeamWinningIndicationOnSecondInnings = "";
    private static int wickets;
    private static long tempMatchId;
    private static int runsScorePerBall;
    private static String plannedMatchFormat;

    private static Map<Long, Integer> scoreUtilityMap;
    @Autowired
    private MatchStatusService matchStatusService;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;


    static Scanner sc = new Scanner(System.in);


    private Team team1;
    private Team team2;

    private static List<Team> teams = new ArrayList<>();

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

    public static int getBatting() {
        return Batting;
    }

    public static void setBatting(int batting) {
        Batting = batting;
    }

    public static void setBowling(int bowling) {
        Bowling = bowling;
    }

    public static int getInnings() {
        return Innings;
    }

    public static void setInnings(int innings) {
        Innings = innings;
    }

    public static int getTotalOvers() {
        return totalOvers;
    }

    public static void setTotalOvers(int totalOvers) {
        GameServiceImpl.totalOvers = totalOvers;
    }

    public static String getPlannedMatchFormat() {
        return plannedMatchFormat;
    }

    public void wicketTrackerFunc() {
        wicketTracker.gotWicket();
    }

    public static int[] getScoreTeams() {
        return scoreTeams;
    }

    public static void setScoreTeams(int[] scoreTeams) {
        GameServiceImpl.scoreTeams = scoreTeams;
    }

    public static int getCurrentBatter() {
        return currentBatter;
    }

    public static void setCurrentBatter(int currentBatter) {
        getBattingPlayer().setActiveStatus("active");
        GameServiceImpl.currentBatter = currentBatter;
    }

    public static int getCurrentBowler() {
        return currentBowler;
    }

    public static void setCurrentBowler(int currentBowler) {
        getBowlingPlayer().setActiveStatus("active");
        GameServiceImpl.currentBowler = currentBowler;
    }

    public static List getScoreRecords() {
        return scoreRecords;
    }

    public static boolean isLegalBallFlag() {
        return legalBallFlag;
    }

    public static void setFlagForTeamWinningIndicationOnSecondInnings(String flagForTeamWinningIndicationOnSecondInnings) {
        GameServiceImpl.flagForTeamWinningIndicationOnSecondInnings = flagForTeamWinningIndicationOnSecondInnings;
    }

    public static void setLegalBallFlag(boolean legalBallFlag) {
        GameServiceImpl.legalBallFlag = legalBallFlag;
    }

    public static int getRunsScorePerBall() {
        return runsScorePerBall;
    }

    public static void setRunsScorePerBall(int runsScorePerBall) {
        GameServiceImpl.runsScorePerBall = runsScorePerBall;
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

    public static List<Team> getTeams() {
        return teams;
    }

    public static List getPlayingTeamsPlayers() {
        return playingTeamsPlayers;
    }

    public void addScoreToBatter(int runsScorePerBall) {
        getBattingPlayer().setScore(runsScorePerBall);
        getBattingPlayer().setBallsFaced(1);
    }


    public static void setWickets(int wickets) {
        GameServiceImpl.wickets = wickets;
    }

    public static String getFlagForTeamWinningIndicationOnSecondInning() {
        return flagForTeamWinningIndicationOnSecondInnings;
    }

    public void setActiveStatusForPlayers() {
        getBattingPlayer().setActiveStatus("active");
        getBowlingPlayer().setActiveStatus("active");
    }

    public void matchFormatScheduler(String matchFormat) {
        plannedMatchFormat = matchFormat.toUpperCase();
        GameServiceImpl.setTotalOvers(MatchFormatService.oversScheduler(plannedMatchFormat));
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
            System.out.println(Arrays.toString(score.getScoreOfBothTeams()));
            System.out.println(Batting);
            System.out.println(runsScorePerBall);
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


    public void startGame(long matchId) throws Exception {
        Match matchTeams = matchRepositoryService.getMatchById(matchId);
        playingTeams = new ArrayList<>();
        List<Team> playingTeams =  matchTeams.getTeamsPlayed();
        tempMatchId = matchId;
        teams = teamSelectorService.teamSelector(
                playingTeams.get(0).getTeamId(),
                playingTeams.get(1).getTeamId()
        );


        playingTeamsPlayers = List.of(teams.get(0).getPlayers(), teams.get(1).getPlayers());
        System.out.println(playingTeamsPlayers);
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
        matchRepositoryService.updateMatch(matchId, matchTeams);

        scoreUtilityMap = new HashMap<>()
        {{
            put(teams.get(0).getTeamId(), ScoreModel.getScoreOfBothTeams()[0]);
            put(teams.get(1).getTeamId(), ScoreModel.getScoreOfBothTeams()[1]);
        }};

        MatchStatusRecord matchStatusRecord = MatchStatusRecord.builder()
                .matchStatusRecordId(sequenceGeneratorService.getSequenceNumber(MatchStatusRecord.SEQUENCE_NAME))
                .matchId(matchId)
                .matchStatus(scoreBoardDisplay.getResults())
                .scoreOfBothTeams(scoreUtilityMap)
                .build();

        matchStatusService.save(matchStatusRecord);
        ResetGameService.resetGame();
    }
}