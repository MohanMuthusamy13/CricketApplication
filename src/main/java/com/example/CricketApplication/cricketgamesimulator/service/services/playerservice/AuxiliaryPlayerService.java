package com.example.CricketApplication.cricketgamesimulator.service.services.playerservice;

import com.example.CricketApplication.cricketgamesimulator.entities.Team;
import com.example.CricketApplication.cricketgamesimulator.entities.builders.ScoreRecordBuilder;
import com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceimplementation.MatchServiceImpl;
import com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceimplementation.ScoreRecorderServiceImpl;
import com.example.CricketApplication.cricketgamesimulator.service.services.MatchRecordStatusService;
import com.example.CricketApplication.cricketgamesimulator.service.services.majorgameservice.GameServiceImpl;
import com.example.CricketApplication.cricketgamesimulator.service.services.matchformatservice.MatchFormatService;
import com.example.CricketApplication.cricketgamesimulator.service.services.overservice.OverService;
import com.example.CricketApplication.cricketgamesimulator.service.services.scoreservice.PlayerCenturyAndHalfCenturyService;
import com.example.CricketApplication.cricketgamesimulator.service.services.scoreservice.ScoreModel;
import com.example.CricketApplication.cricketgamesimulator.service.services.teamservices.TeamSelectorService;
import com.example.CricketApplication.cricketgamesimulator.service.services.windeclarativeservices.WicketStatusProvider;
import com.example.CricketApplication.cricketgamesimulator.view.ScoreBoardDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuxiliaryPlayerService {

    @Autowired
    MatchServiceImpl matchRepositoryService;
    @Autowired
    MatchRecordStatusService statusService;
    @Autowired
    ScoreBoardDisplay scoreBoardDisplay;
    @Autowired
    TeamSelectorService teamSelectorService;
    @Autowired
    ScoreRecordBuilder scoreRecordBuilder;

    @Autowired
    ScoreRecorderServiceImpl scoreRecorderRepositoryService;

    @Autowired
    PlayerStatsRecorder playerStatsRecorder;

    public static void addScoreToBatter(int runsScorePerBall) {
        GameServiceImpl.getBattingPlayer().setScore(runsScorePerBall);
        GameServiceImpl.getBattingPlayer().setBallsFaced(1);
    }

    public static void setActiveStatusForPlayers() {
        GameServiceImpl.getBattingPlayer().setActiveStatus("active");
        GameServiceImpl.getBowlingPlayer().setActiveStatus("active");
    }

    public void playingTeamPlayersProvider(long matchId) throws Exception {
        GameServiceImpl.setMatchTeams(matchRepositoryService.getMatchById(matchId));
        GameServiceImpl.setPlayingTeams(new ArrayList<>());
        List<Team> playingTeams =  GameServiceImpl.getMatchTeams().getTeamsPlayed();
        GameServiceImpl.setTempMatchId(matchId);
        GameServiceImpl.setTeams(teamSelectorService.teamSelector(
                playingTeams.get(0).getTeamId(),
                playingTeams.get(1).getTeamId()
        ));
        GameServiceImpl.setPlayingTeamsPlayers(
                List.of(
                        GameServiceImpl.getTeams().get(0).getPlayers(),
                        GameServiceImpl.getTeams().get(1).getPlayers()
                )
        );
    }

    public void updateStatsAndScores(long matchId) throws Exception {
        GameServiceImpl.getMatchTeams().setTeamsPlayed(GameServiceImpl.getTeams());
        GameServiceImpl.getMatchTeams().setMatchFormat(MatchFormatService.getPlannedMatchFormat());
        PlayerCenturyAndHalfCenturyService.centuryStatsProvider(GameServiceImpl.getPlayingTeamsPlayers());
        matchRepositoryService.updateMatch(matchId, GameServiceImpl.getMatchTeams());
        statusService.saveMatchStatusRecord(matchId);
        playerStatsRecorder.savePlayerStats(GameServiceImpl.getPlayingTeamsPlayers());
    }

    public void saveScoreRecord(int runsScorePerBall) {
        String overCount = String.valueOf(OverService.getOverCount()) + "." + OverService.getBallsCount();

        scoreRecorderRepositoryService.saveScoreRecordPerBall(
                scoreRecordBuilder.build(
                        GameServiceImpl.getTempMatchId(),
                        overCount,
                        scoreBoardDisplay.runsForDisplayProvider(runsScorePerBall),
                        GameServiceImpl.getInnings(),
                        ScoreModel.getScoreOfBothTeams()[GameServiceImpl.getBatting()],
                        WicketStatusProvider.getWicketLose(),
                        GameServiceImpl.getBattingPlayer(),
                        GameServiceImpl.getBowlingPlayer()
                )
        );
    }



}