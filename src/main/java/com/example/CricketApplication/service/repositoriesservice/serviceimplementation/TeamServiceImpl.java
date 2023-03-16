package com.example.CricketApplication.service.repositoriesservice.serviceimplementation;

import com.example.CricketApplication.entities.Team;
import com.example.CricketApplication.exceptionhandler.NotFoundException;
import com.example.CricketApplication.repositories.TeamRepository;
import com.example.CricketApplication.service.repositoriesservice.serviceinterfaces.TeamService;
import com.example.CricketApplication.utils.builders.TeamBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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