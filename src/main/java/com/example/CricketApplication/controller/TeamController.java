package com.example.CricketApplication.controller;

import com.example.CricketApplication.entities.Team;
import com.example.CricketApplication.service.repositoriesservice.serviceinterfaces.TeamService;
import com.example.CricketApplication.utils.builders.TeamBuilder;
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
    private final TeamService teamService;

    @Autowired
    TeamBuilder teamBuilder;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/getAllTeams")
    public ResponseEntity<List<Team>> getALlTeams() {
        return new ResponseEntity<>(teamService.getAllTeams(), HttpStatus.OK);
    }

    @GetMapping("/getTeamById")
    public ResponseEntity<Team> getTeamById(
            @RequestParam(value = "id") Long id
    ) throws IOException {
        return new ResponseEntity<>(teamService.getTeamById(id), HttpStatus.OK);
    }

    @PostMapping("/createTeam")
    public ResponseEntity<Team> createTeam(
            @RequestParam(value = "teamName") String teamName
    ) {
        Team team = teamBuilder.build(teamName);
        return new ResponseEntity<>(teamService.saveTeam(team), HttpStatus.CREATED);
    }

    @PutMapping("/updateTeam")
    public ResponseEntity<Team> updateTeam(
            @RequestParam(value = "id") Long id,
            @RequestBody Team team
    ) throws Exception {
        return new ResponseEntity<>(teamService.updateTeam(id, team), HttpStatus.OK);

    }

    @DeleteMapping("/deleteTeam")
    public ResponseEntity<HttpStatus> deleteTeam(
            @RequestParam(value = "id") Long id
    ) {
        teamService.deleteTeam(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}