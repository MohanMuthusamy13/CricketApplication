package com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceinterfaces;

import com.example.CricketApplication.cricketgamesimulator.entities.Match;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchService {

    Match saveMatch(Match match);
    Match getMatchById(String matchId);
    Match updateMatch(String matchId, Match match);
    List<Match> getMatchesByMatchFormat(String matchFormat);
    List<Match> getMatchesByTeamName(String teamName);
}