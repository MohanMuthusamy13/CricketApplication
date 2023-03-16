package com.example.CricketApplication.service.repositoriesservice.serviceinterfaces;

import com.example.CricketApplication.entities.Match;
import com.example.CricketApplication.entities.PlayerStatsStructure;

import java.util.List;

public interface MatchService {

    Match saveMatch(Match match);
    Match getMatchById(Long matchId) throws Exception;
    List<Match> getMatchesPlayedByTeamName(String teamName);
    int getMatchesCountPlayedByTeamName(String teamName);
    Match updateMatch(long matchId, Match match) throws Exception;
}