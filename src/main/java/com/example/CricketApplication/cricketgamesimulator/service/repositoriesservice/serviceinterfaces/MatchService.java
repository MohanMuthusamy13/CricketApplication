package com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceinterfaces;

import com.example.CricketApplication.cricketgamesimulator.entities.Match;
import com.example.CricketApplication.cricketgamesimulator.entities.PlayerStatsStructure;

import java.util.List;

public interface MatchService {

    Match saveMatch(Match match);
    Match getMatchById(Long matchId) throws Exception;
    List<Match> getMatchesPlayedByTeamName(String teamName);
    int getMatchesCountPlayedByTeamName(String teamName);
    Match updateMatch(long matchId, Match match) throws Exception;
    PlayerStatsStructure getMaxScorerIdByMatch(long matchId);
    PlayerStatsStructure getWicketTakerIdByMatch(long matchId);
    PlayerStatsStructure getMaxStrikeRatePlayer(long matchId);
}