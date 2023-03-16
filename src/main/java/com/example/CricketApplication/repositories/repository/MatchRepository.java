package com.example.CricketApplication.repositories.repository;

import com.example.CricketApplication.entities.Match;

import java.util.List;

public interface MatchRepository {
    List<Match> getMatchesPlayedByTeamName(String teamName);
    int getMatchesCountPlayedByTeamName(String teamName);

}