package com.example.CricketApplication.service.serviceinterfaces;

import com.example.CricketApplication.entities.Match;

import java.util.List;

public interface MatchService {

    List<Match> getMatches();
    Match saveMatch(Match match);
    Match getMatchById(Long matchId) throws Exception;
    List<Match> getMatchesPlayedByTeamName(String teamName);
    int getMatchesCountPlayedByTeamName(String teamName);
    Match updateMatch(long matchId, Match match) throws Exception;
}