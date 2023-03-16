package com.example.CricketApplication.service.auxilaryservices.playerservice;

import com.example.CricketApplication.entities.Team;
import com.example.CricketApplication.service.serviceimplementation.MatchServiceImpl;
import com.example.CricketApplication.service.serviceimplementation.ScoreRecorderServiceImpl;
import com.example.CricketApplication.service.auxilaryservices.initializematchservice.MatchRecordStatusService;
import com.example.CricketApplication.service.auxilaryservices.majorgameservice.GameService;
import com.example.CricketApplication.service.auxilaryservices.initializematchservice.MatchFormatService;
import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.OverService;
import com.example.CricketApplication.service.auxilaryservices.runtrackerservice.PlayerCenturyAndHalfCenturyService;
import com.example.CricketApplication.service.auxilaryservices.runtrackerservice.ScoreService;
import com.example.CricketApplication.service.auxilaryservices.majorgameservice.TeamSelectorService;
import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.WicketStatusProvider;
import com.example.CricketApplication.utils.builders.ScoreRecordBuilder;
import com.example.CricketApplication.utils.Constants;
import com.example.CricketApplication.view.ScoreBoardDisplay;
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
        GameService.getBattingPlayer().setScore(runsScorePerBall);
        GameService.getBattingPlayer().setBallsFaced(1);
    }

    public static void setActiveStatusForPlayers() {
        GameService.getBattingPlayer().setActiveStatus("active");
        GameService.getBowlingPlayer().setActiveStatus("active");
    }

    public void playingTeamPlayersProvider(long matchId) throws Exception {
        GameService.setMatchTeams(matchRepositoryService.getMatchById(matchId));
        GameService.setPlayingTeams(new ArrayList<>());
        List<Team> playingTeams =  GameService.getMatchTeams().getTeamsPlayed();
        GameService.setTempMatchId(matchId);
        GameService.setTeams(teamSelectorService.teamSelector(
                playingTeams.get(Constants.FIRST_TEAM).getTeamId(),
                playingTeams.get(Constants.SECOND_TEAM).getTeamId()
        ));
        GameService.setPlayingTeamsPlayers(
                List.of(
                        GameService.getTeams().get(Constants.FIRST_TEAM).getPlayers(),
                        GameService.getTeams().get(Constants.SECOND_TEAM).getPlayers()
                )
        );
    }

    public void updateStatsAndScores(long matchId) throws Exception {
        GameService.getMatchTeams().setTeamsPlayed(GameService.getTeams());
        GameService.getMatchTeams().setMatchFormat(MatchFormatService.getPlannedMatchFormat());
        PlayerCenturyAndHalfCenturyService.centuryStatsProvider(GameService.getPlayingTeamsPlayers());
        matchRepositoryService.updateMatch(matchId, GameService.getMatchTeams());
        statusService.saveMatchStatusRecord(matchId);
        playerStatsRecorder.savePlayerStats(GameService.getPlayingTeamsPlayers());
    }

    public void saveScoreRecord(int runsScorePerBall) {
        String overCount = String.valueOf(OverService.getOverCount()) + "." + OverService.getBallsCount();

        scoreRecorderRepositoryService.saveScoreRecordPerBall(
                scoreRecordBuilder.build(
                        GameService.getTempMatchId(),
                        overCount,
                        scoreBoardDisplay.runsForDisplayProvider(runsScorePerBall),
                        GameService.getInnings(),
                        ScoreService.getScoreOfBothTeams()[GameService.getBatting()],
                        WicketStatusProvider.getWicketLose(),
                        GameService.getBattingPlayer(),
                        GameService.getBowlingPlayer()
                )
        );
    }



}