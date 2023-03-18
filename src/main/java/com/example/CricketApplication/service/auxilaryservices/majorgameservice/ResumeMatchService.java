package com.example.CricketApplication.service.auxilaryservices.majorgameservice;

import com.example.CricketApplication.entities.Match;
import com.example.CricketApplication.entities.MatchStateSaver;
import com.example.CricketApplication.repositories.repositoryImpl.MatchStatusSaverRepositoryImpl;
import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.OverService;
import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.WicketStatusProvider;
import com.example.CricketApplication.service.auxilaryservices.playerservice.AuxiliaryPlayerService;
import com.example.CricketApplication.service.auxilaryservices.runtrackerservice.ScoreService;
import com.example.CricketApplication.service.serviceinterfaces.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeMatchService {

    @Autowired
    private MatchStatusSaverRepositoryImpl saverRepository;

    @Autowired
    AuxiliaryPlayerService auxiliaryPlayerService;

    public static boolean isResumed = false;
    @Autowired
    private GameStarter gameService;

    private final MatchService matchService;

    @Autowired
    public ResumeMatchService(MatchService matchService) {
        this.matchService = matchService;
    }

    public void reinitializeToResumeMatch(MatchStateSaver matchStateSaver, Match resumedMatch) throws Exception {
        GameStarter.setInnings(matchStateSaver.getInnings());
        OverService.setOverCount(matchStateSaver.getOverCount());
        OverService.setBallsCount(matchStateSaver.getBallCount());
        WicketStatusProvider.setWicketLose(matchStateSaver.getWicketsLose());
        GameStarter.setBatting(matchStateSaver.getBatting());
        GameStarter.setBowling(matchStateSaver.getBowling());
        auxiliaryPlayerService.playingTeamPlayersProvider(resumedMatch.getMatchId());
        GameStarter.setCurrentBatter(matchStateSaver.getCurrentBatter());
        GameStarter.setCurrentBowler(matchStateSaver.getCurrentBowler());
        ScoreService.setScoreOfBothTeams(matchStateSaver.getScore());
        GameStarter.setTotalOvers(matchStateSaver.getTotalOvers());

    }

    public void resumeMatch(String type) throws Exception {
        Match pendingMatch = loadUnfinishedMatches();
        List<MatchStateSaver> matchStateSaverList = saverRepository.findAll();
        MatchStateSaver matchStateSaver = matchStateSaverList.get(matchStateSaverList.size() - 1);
        isResumed = true;
        reinitializeToResumeMatch(matchStateSaver, pendingMatch);
        gameService.startGame(matchStateSaver.getMatchId(), "PER BALL");
    }

    public Match loadUnfinishedMatches() {
        List<Match> matches1 = matchService.getMatches().stream()
                .filter(match -> !match.isMatchFinished())
                .toList();
        System.out.println(matches1);
        return matches1.get(matches1.size() - 1);
    }
}