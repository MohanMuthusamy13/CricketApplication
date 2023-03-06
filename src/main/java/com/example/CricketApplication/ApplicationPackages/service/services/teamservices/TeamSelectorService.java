package com.example.CricketApplication.ApplicationPackages.service.services.teamservices;

import com.example.CricketApplication.ApplicationPackages.entities.Team;
import com.example.CricketApplication.ApplicationPackages.service.repositoriesService.serviceimplementation.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class TeamSelectorService {

    private static List<Team> teams;

    private TeamServiceImpl teamRepositoryService;

    @Autowired
    public void setTeamRepositoryService(TeamServiceImpl teamRepositoryService) {
        this.teamRepositoryService = teamRepositoryService;
    }

    Scanner sc = new Scanner(System.in);

    public List<Team> teamSelector(long team1Id, long team2Id) throws IOException {
        teams = new ArrayList<>();
        teams.add(teamRepositoryService.getTeamById(team1Id));
        teams.add(teamRepositoryService.getTeamById(team2Id));
        return teams;
    }


}