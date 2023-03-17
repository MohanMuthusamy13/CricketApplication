package com.example.CricketApplication.service.serviceinterfaces;

import com.example.CricketApplication.entities.Match;
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