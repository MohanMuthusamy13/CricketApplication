package com.example.CricketApplication.service.serviceimpl;

import com.example.CricketApplication.entities.Team;
import com.example.CricketApplication.utils.builders.TeamBuilder;
import com.example.CricketApplication.exceptionhandler.NotFoundException;
import com.example.CricketApplication.repositories.TeamRepository;
import com.example.CricketApplication.service.serviceinterfaces.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<Team> teams = new ArrayList<>();
        Iterable<Team> teamIterable =  teamRepository.findAll();
        for (Team team : teamIterable) {
            teams.add(team);
        }
        return teams;
    }

    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team getTeamById(String id) {
        Team team = teamRepository.findById(id).orElse(null);
        if (team == null){
            throw new NotFoundException("Please enter the valid ID");
        }
        return team;
    }

    public Team updateTeam(String id, Team updatedTeam) {
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
    public void deleteTeam(String id) {
        teamRepository.deleteById(id);
    }
}