package com.example.CricketApplication.cricketgamesimulator.entities.builders;

import com.example.CricketApplication.cricketgamesimulator.entities.Match;
import com.example.CricketApplication.cricketgamesimulator.service.serviceImpl.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MatchBuilder {

    private TeamServiceImpl teamRepositoryService;

    @Autowired
    public void setTeamRepositoryService(TeamServiceImpl teamRepositoryService) {
        this.teamRepositoryService = teamRepositoryService;
    }

    public Match build(String format, String teamId1, String teamId2) throws IOException {
        return Match.builder()
                .teamsPlayed(
                        List.of(
                                teamRepositoryService.getTeamById(teamId1),
                                teamRepositoryService.getTeamById(teamId2)
                        ))
                .matchFormat(format)
                .build();
    }
}