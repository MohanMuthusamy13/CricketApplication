package com.example.CricketApplication.ApplicationPackages.service.repositoriesService.serviceinterfaces;

import com.example.CricketApplication.ApplicationPackages.entities.Match;

import java.util.List;

public interface MatchService {

    Match saveMatch(Match match);
    Match getMatchById(Long matchId) throws Exception;
    List<Match> getMatchesPlayedByTeamName(String teamName);
    int getMatchesCountPlayedByTeamName(String teamName);
    Match updateMatch(long matchId, Match match) throws Exception;
}