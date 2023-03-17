package com.example.CricketApplication.service.serviceinterfaces;

import com.example.CricketApplication.entities.Team;
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