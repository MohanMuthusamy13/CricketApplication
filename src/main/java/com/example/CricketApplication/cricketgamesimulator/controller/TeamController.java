package com.example.CricketApplication.cricketgamesimulator.controller;

import com.example.CricketApplication.cricketgamesimulator.entities.Team;
import com.example.CricketApplication.cricketgamesimulator.entities.builders.TeamBuilder;
import com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceimplementation.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cricketGame")
public class TeamController {

    @Autowired
    TeamServiceImpl teamRepositoryService;

    @Autowired
    TeamBuilder teamBuilder;

    @GetMapping("/getAllTeams")
    public ResponseEntity<List<Team>> getALlTeams() {
        return new ResponseEntity<>(teamRepositoryService.getAllTeams(), HttpStatus.OK);
    }

    @GetMapping("/getTeamById")
    public ResponseEntity<Team> getTeamById(
            @RequestParam(value = "id") Long id
    ) throws IOException {
        return new ResponseEntity<>(teamRepositoryService.getTeamById(id), HttpStatus.OK);
    }

    @PostMapping("/createTeam")
    public ResponseEntity<Team> createTeam(
            @RequestParam(value = "teamName") String teamName
    ) {
        Team team = teamBuilder.build(teamName);
        return new ResponseEntity<>(teamRepositoryService.saveTeam(team), HttpStatus.CREATED);
    }

    @PutMapping("/updateTeam")
    public ResponseEntity<Team> updateTeam(
            @RequestParam(value = "id") Long id,
            @RequestBody Team team
    ) throws Exception {
        return new ResponseEntity<>(teamRepositoryService.updateTeam(id, team), HttpStatus.OK);

    }

    @DeleteMapping("/deleteTeam")
    public ResponseEntity<HttpStatus> deleteTeam(
            @RequestParam(value = "id") Long id
    ) {
        teamRepositoryService.deleteTeam(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}