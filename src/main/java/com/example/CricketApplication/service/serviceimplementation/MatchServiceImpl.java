package com.example.CricketApplication.service.serviceimplementation;

import com.example.CricketApplication.entities.Match;
import com.example.CricketApplication.exceptionhandler.NotFoundException;
import com.example.CricketApplication.repositories.repositoryImpl.MatchRepositoryImpl;
import com.example.CricketApplication.service.serviceinterfaces.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    private MatchRepositoryImpl matchRepository;

    @Autowired
    public MatchServiceImpl(MatchRepositoryImpl matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    @Override
    public Match getMatchById(Long matchId) throws Exception{
        Match match = matchRepository.findById(matchId).orElse(null);
        if (match == null) {
            throw new NotFoundException("Match Id is Invalid");
        }

        return match;
    }

    @Override
    public List<Match> getMatchesPlayedByTeamName(String teamName) {
        return matchRepository.getMatchesPlayedByTeamName(teamName);
    }

    @Override
    public int getMatchesCountPlayedByTeamName(String teamName) {
        return matchRepository.getMatchesCountPlayedByTeamName(teamName);
    }

    @Override
    public Match updateMatch(long matchId, Match updatedMatch) throws Exception {
        Match match = matchRepository.findById(matchId).orElse(null);
        if (match == null) {
            throw new NotFoundException("Match Id is Invalid");
        }
        match.setMatchFormat(updatedMatch.getMatchFormat());
        match.setTeamsPlayed(updatedMatch.getTeamsPlayed());
        match.setMatchStatus(updatedMatch.getMatchStatus());
        return matchRepository.save(match);
    }
}