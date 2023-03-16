package com.example.CricketApplication.controller;

import com.example.CricketApplication.entities.Team;
import com.example.CricketApplication.service.serviceinterfaces.TeamService;
import com.example.CricketApplication.utils.builders.TeamBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cricket-game")
public class TeamController {

    @Autowired
    private final TeamService teamService;

    @Autowired
    TeamBuilder teamBuilder;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/teams")
    public ResponseEntity<List<Team>> getALlTeams() {
        return new ResponseEntity<>(teamService.getAllTeams(), HttpStatus.OK);
    }

    @GetMapping("/team/{id}")
    public ResponseEntity<Team> getTeamById(
            @PathVariable Long id
    ) throws IOException {
        return new ResponseEntity<>(teamService.getTeamById(id), HttpStatus.OK);
    }

    @PostMapping("/create-team")
    public ResponseEntity<Team> createTeam(
            @RequestParam(value = "name") String teamName
    ) {
        Team team = teamBuilder.build(teamName);
        return new ResponseEntity<>(teamService.saveTeam(team), HttpStatus.CREATED);
    }

    @PutMapping("/update-team/{id}")
    public ResponseEntity<Team> updateTeam(
            @PathVariable Long id,
            @RequestBody Team team
    ) throws Exception {
        return new ResponseEntity<>(teamService.updateTeam(id, team), HttpStatus.OK);

    }

    @DeleteMapping("/delete-team/{id}")
    public ResponseEntity<HttpStatus> deleteTeam(
            @PathVariable Long id
    ) {
        teamService.deleteTeam(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}