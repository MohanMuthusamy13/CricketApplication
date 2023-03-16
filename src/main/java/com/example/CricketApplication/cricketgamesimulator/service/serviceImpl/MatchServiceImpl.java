package com.example.CricketApplication.cricketgamesimulator.service.serviceImpl;

import com.example.CricketApplication.cricketgamesimulator.entities.Match;
import com.example.CricketApplication.cricketgamesimulator.exceptionhandler.NotFoundException;
import com.example.CricketApplication.cricketgamesimulator.repositories.MatchRepository;
import com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceinterfaces.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    private MatchRepository matchRepository;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    @Override
    public Match getMatchById(String matchId) {
        return matchRepository
                .findById(matchId)
                .orElseThrow(
                        () -> new NotFoundException("MatchId is not found")
                );
    }

    @Override
    public Match updateMatch(String matchId, Match updatedMatch) {
        Match match = matchRepository.findById(matchId).orElseThrow(
                () -> new NotFoundException("Match Id is not found")
        );

        match.setMatchFormat(updatedMatch.getMatchFormat());
        match.setTeamsPlayed(updatedMatch.getTeamsPlayed());
        match.setMatchStatus(updatedMatch.getMatchStatus());

        matchRepository.save(match);

        return match;
    }

    @Override
    public List<Match> getMatchesByMatchFormat(String matchFormat) {
        return matchRepository.findByMatchFormat(matchFormat);
    }

    @Override
    public List<Match> getMatchesByTeamName(String teamName) {
        return matchRepository.getMatchesByTeamName(teamName);
    }
}