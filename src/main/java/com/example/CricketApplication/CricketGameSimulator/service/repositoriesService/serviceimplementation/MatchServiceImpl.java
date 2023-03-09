package com.example.CricketApplication.CricketGameSimulator.service.repositoriesService.serviceimplementation;

import com.example.CricketApplication.CricketGameSimulator.entities.Match;
import com.example.CricketApplication.CricketGameSimulator.entities.Player;
import com.example.CricketApplication.CricketGameSimulator.entities.PlayerStatsStructure;
import com.example.CricketApplication.CricketGameSimulator.exceptionHandler.NotFoundException;
import com.example.CricketApplication.CricketGameSimulator.repositories.MatchRepository;
import com.example.CricketApplication.CricketGameSimulator.service.repositoriesService.serviceinterfaces.MatchService;
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

    public PlayerStatsStructure getMaxScorerIdByMatch(long matchId) {
        return matchRepository.getMaxScorerIdByMatch(matchId);
    }

    public PlayerStatsStructure getWicketTakerIdByMatch(long matchId) {
        return matchRepository.getMaxWicketTakerIdByMatch(matchId);
    }
}