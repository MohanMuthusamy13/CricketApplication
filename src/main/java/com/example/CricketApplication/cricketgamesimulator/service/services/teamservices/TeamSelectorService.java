package com.example.CricketApplication.cricketgamesimulator.service.services.teamservices;

import com.example.CricketApplication.cricketgamesimulator.entities.Team;
import com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceimplementation.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class TeamSelectorService {

    private TeamServiceImpl teamRepositoryService;

    @Autowired
    public void setTeamRepositoryService(TeamServiceImpl teamRepositoryService) {
        this.teamRepositoryService = teamRepositoryService;
    }

    public List<Team> teamSelector(long team1Id, long team2Id) throws IOException {
        List<Team> teams = new ArrayList<>();
        teams.add(teamRepositoryService.getTeamById(team1Id));
        teams.add(teamRepositoryService.getTeamById(team2Id));
        return teams;
    }
}