package com.example.CricketApplication.ApplicationPackages.service.repositoriesService.serviceimplementation;

import com.example.CricketApplication.ApplicationPackages.entities.Team;
import com.example.CricketApplication.ApplicationPackages.exceptionHandler.NotFoundException;
import com.example.CricketApplication.ApplicationPackages.repositories.TeamRepository;
import com.example.CricketApplication.ApplicationPackages.entities.builders.TeamBuilder;
import com.example.CricketApplication.ApplicationPackages.service.repositoriesService.serviceinterfaces.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    TeamRepository teamRepository;
    @Autowired
    TeamBuilder teamCreationService;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team getTeamById(long id) {
        Team team = teamRepository.findById(id).orElse(null);
        if (team == null){
            throw new NotFoundException("Please enter the valid ID");
        }
        return team;
    }

    public Team updateTeam(Long id, Team updatedTeam) {
        Team team = teamRepository.findById(id).orElse(null);

        if (team == null) {
            throw new NotFoundException("Team does not exist with the respective id");
        }

        team.setTeamName(updatedTeam.getTeamName());
        team.setMatchesPlayed(updatedTeam.getMatchesPlayed());
        team.setPlayers(updatedTeam.getPlayers());

        return teamRepository.save(team);
    }

    @Override
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}