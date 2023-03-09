package com.example.CricketApplication.CricketGameSimulator.service.repositoriesService.serviceinterfaces;

import com.example.CricketApplication.CricketGameSimulator.entities.Match;
import com.example.CricketApplication.CricketGameSimulator.entities.Player;
import com.example.CricketApplication.CricketGameSimulator.entities.PlayerStatsStructure;

import java.util.List;

public interface MatchService {

    Match saveMatch(Match match);
    Match getMatchById(Long matchId) throws Exception;
    List<Match> getMatchesPlayedByTeamName(String teamName);
    int getMatchesCountPlayedByTeamName(String teamName);
    Match updateMatch(long matchId, Match match) throws Exception;
    PlayerStatsStructure getMaxScorerIdByMatch(long matchId);
    PlayerStatsStructure getWicketTakerIdByMatch(long matchId);
}