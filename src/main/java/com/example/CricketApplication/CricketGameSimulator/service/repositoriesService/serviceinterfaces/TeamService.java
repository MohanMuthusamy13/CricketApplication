package com.example.CricketApplication.CricketGameSimulator.service.repositoriesService.serviceinterfaces;

import com.example.CricketApplication.CricketGameSimulator.entities.Team;

import java.util.List;

public interface TeamService {

    List<Team> getAllTeams();
    Team saveTeam(Team team);
    Team getTeamById(long id);
    Team updateTeam(Long id, Team team);
    void deleteTeam(Long id);
}