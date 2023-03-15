package com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceinterfaces;

import com.example.CricketApplication.cricketgamesimulator.entities.Team;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamService {
    List<Team> getAllTeams();
    Team saveTeam(Team team);
    Team getTeamById(String id);
    Team updateTeam(String id, Team team);
    void deleteTeam(String id);
}