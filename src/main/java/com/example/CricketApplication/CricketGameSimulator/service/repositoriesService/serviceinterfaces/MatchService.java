package com.example.CricketApplication.CricketGameSimulator.service.repositoriesService.serviceinterfaces;

import com.example.CricketApplication.CricketGameSimulator.entities.Match;

import java.util.List;

public interface MatchService {

    Match saveMatch(Match match);
    Match getMatchById(Long matchId) throws Exception;
    List<Match> getMatchesPlayedByTeamName(String teamName);
    int getMatchesCountPlayedByTeamName(String teamName);
    Match updateMatch(long matchId, Match match) throws Exception;
}