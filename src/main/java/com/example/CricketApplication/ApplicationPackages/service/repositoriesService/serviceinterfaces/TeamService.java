package com.example.CricketApplication.ApplicationPackages.service.repositoriesService.serviceinterfaces;

import com.example.CricketApplication.ApplicationPackages.entities.Team;

import java.io.IOException;
import java.util.List;

public interface TeamService {

    List<Team> getAllTeams();
    Team saveTeam(Team team);
    Team getTeamById(long id);
    Team updateTeam(Long id, Team team);
    void deleteTeam(Long id);
}