package com.example.CricketApplication.service.services.playerservice;

import com.example.CricketApplication.entities.Team;
import com.example.CricketApplication.utils.builders.ScoreRecordBuilder;
import com.example.CricketApplication.service.serviceimpl.MatchServiceImpl;
import com.example.CricketApplication.service.serviceimpl.ScoreRecorderImpl;
import com.example.CricketApplication.service.services.majorgameservice.MatchRecordStatusService;
import com.example.CricketApplication.service.services.majorgameservice.GameServiceImpl;
import com.example.CricketApplication.service.services.initializematchservice.MatchFormatService;
import com.example.CricketApplication.service.services.balltrackerservice.OverService;
import com.example.CricketApplication.service.services.runtrackerservice.PlayerCenturyAndHalfCenturyService;
import com.example.CricketApplication.service.services.runtrackerservice.ScoreService;
import com.example.CricketApplication.service.services.teamservices.TeamSelectorService;
import com.example.CricketApplication.service.services.balltrackerservice.WicketStatusProvider;
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
    ScoreRecorderImpl scoreRecorderRepositoryService;

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

    public void playingTeamPlayersProvider(String matchId) throws Exception {
        GameServiceImpl.setMatchTeams(matchRepositoryService.getMatchById(matchId));
        GameServiceImpl.setPlayingTeams(new ArrayList<>());
        List<Team> playingTeams = GameServiceImpl.getMatchTeams().getTeamsPlayed();
        GameServiceImpl.setTempMatchId(matchId);
        GameServiceImpl.setTeams(teamSelectorService.teamSelector(
                playingTeams.get(Constants.FIRST_TEAM).getTeamId(),
                playingTeams.get(Constants.SECOND_TEAM).getTeamId()
        ));
        GameServiceImpl.setPlayingTeamsPlayers(
                List.of(
                        GameServiceImpl.getTeams().get(Constants.FIRST_TEAM).getPlayers(),
                        GameServiceImpl.getTeams().get(Constants.SECOND_TEAM).getPlayers()
                )
        );
    }

    public void updateStatsAndScores(String matchId) throws Exception {
        GameServiceImpl.getMatchTeams().setTeamsPlayed(GameServiceImpl.getTeams());
        GameServiceImpl.getMatchTeams().setMatchFormat(MatchFormatService.getPlannedMatchFormat());
        PlayerCenturyAndHalfCenturyService.centuryStatsProvider(GameServiceImpl.getPlayingTeamsPlayers());
        System.out.println(GameServiceImpl.getMatchTeams());
        System.out.println(matchRepositoryService.updateMatch(matchId, GameServiceImpl.getMatchTeams()));
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
                        ScoreService.getScoreOfBothTeams()[GameServiceImpl.getBatting()],
                        WicketStatusProvider.getWicketLose(),
                        GameServiceImpl.getBattingPlayer(),
                        GameServiceImpl.getBowlingPlayer()
                )
        );
}



}