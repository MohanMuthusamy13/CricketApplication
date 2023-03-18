package com.example.CricketApplication.service.auxilaryservices.playerservice;

import com.example.CricketApplication.entities.ScoreRecord;
import com.example.CricketApplication.entities.Team;
import com.example.CricketApplication.repositories.repositoryImpl.MatchRepositoryImpl;
import com.example.CricketApplication.service.auxilaryservices.majorgameservice.ResumeMatchService;
import com.example.CricketApplication.service.serviceimplementation.MatchServiceImpl;
import com.example.CricketApplication.service.serviceimplementation.ScoreRecorderServiceImpl;
import com.example.CricketApplication.service.auxilaryservices.initializematchservice.MatchRecordStatusService;
import com.example.CricketApplication.service.auxilaryservices.majorgameservice.GameStarter;
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
    @Autowired
    private MatchRepositoryImpl matchRepositoryImpl;

    public static void addScoreToBatter(int runsScorePerBall) {
        GameStarter.getBattingPlayer().setScore(runsScorePerBall);
        GameStarter.getBattingPlayer().setBallsFaced(1);
    }

    public static void setActiveStatusForPlayers() {
        GameStarter.getBattingPlayer().setActiveStatus("active");
        GameStarter.getBowlingPlayer().setActiveStatus("active");
    }

    public void playingTeamPlayersProvider(long matchId) throws Exception {
        GameStarter.setMatchTeams(matchRepositoryService.getMatchById(matchId));
        GameStarter.setPlayingTeams(new ArrayList<>());
        List<Team> playingTeams =  GameStarter.getMatchTeams().getTeamsPlayed();
        GameStarter.setTempMatchId(matchId);
        if (ResumeMatchService.isResumed) {
            GameStarter.setTeams(playingTeams);
        }
        else {
            GameStarter.setTeams(teamSelectorService.teamSelector(
                    playingTeams.get(Constants.FIRST_TEAM).getTeamId(),
                    playingTeams.get(Constants.SECOND_TEAM).getTeamId()
            ));
        }
        GameStarter.setPlayingTeamsPlayers(
                List.of(
                        GameStarter.getTeams().get(Constants.FIRST_TEAM).getPlayers(),
                        GameStarter.getTeams().get(Constants.SECOND_TEAM).getPlayers()
                )
        );
    }

    public void saveMatchStatus(long matchId) throws Exception {
        GameStarter.getMatchTeams().setTeamsPlayed(GameStarter.getTeams());
        GameStarter.getMatchTeams().setMatchFormat(MatchFormatService.getPlannedMatchFormat());
        matchRepositoryService.updateMatch(matchId, GameStarter.getMatchTeams());
        playerStatsRecorder.savePlayerStats(GameStarter.getPlayingTeamsPlayers());
    }

    public void updateStatsAndScores(long matchId) throws Exception {
        PlayerCenturyAndHalfCenturyService.centuryStatsProvider(GameStarter.getPlayingTeamsPlayers());
        matchRepositoryService.updateMatch(matchId, GameStarter.getMatchTeams());
        statusService.saveMatchStatusRecord(matchId);
    }

    public ScoreRecord buildScoreRecord(int runsScorePerBall) {
        return scoreRecordBuilder.build(
                GameStarter.getTempMatchId(),
                OverService.getOverCount() + "." + OverService.getBallsCount(),
                scoreBoardDisplay.runsForDisplayProvider(runsScorePerBall),
                GameStarter.getInnings(),
                ScoreService.getScoreOfBothTeams()[GameStarter.getBatting()],
                WicketStatusProvider.getWicketLose(),
                GameStarter.getBattingPlayer(),
                GameStarter.getBowlingPlayer()
        );
    }

    public void saveScoreRecord(ScoreRecord record) {
        scoreRecorderRepositoryService.saveScoreRecordPerBall(record);
    }

    public void saveScoreRecords(List<ScoreRecord> records) {
        scoreRecorderRepositoryService.saveAllRecords(records);
    }

}