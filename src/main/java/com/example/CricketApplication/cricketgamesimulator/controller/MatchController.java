package com.example.CricketApplication.cricketgamesimulator.controller;

import com.example.CricketApplication.cricketgamesimulator.entities.Match;
import com.example.CricketApplication.cricketgamesimulator.entities.builders.MatchBuilder;
import com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceimplementation.MatchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cricketGame/match")
public class MatchController {

    @Autowired
    private MatchServiceImpl matchRepositoryService;

    @Autowired
    private MatchBuilder matchBuilder;

    @GetMapping("/{id}")
    public Match getMatchById(
            @PathVariable(value = "id") Long id
    ) throws Exception {
        return matchRepositoryService.getMatchById(id);
    }

    @GetMapping("/getMatchesPlayedByTeamName")
    public List<Match> getMatchesPlayedByTeamName(
            @RequestParam(value = "teamName") String teamName
    ) {
        return matchRepositoryService.getMatchesPlayedByTeamName(teamName);
    }

    @GetMapping("/getMatchesCountPlayedByTeamName")
    public int getMatchesCountPlayedByTeamName(
            @RequestParam(value = "teamName") String teamName
    ) {
        return matchRepositoryService.getMatchesCountPlayedByTeamName(teamName);
    }

    @PostMapping("/createMatch")
    public ResponseEntity<Match> createMatch(
            @RequestParam(value = "id1") long teamId1,
            @RequestParam(value = "id2") long teamId2,
            @RequestParam(value = "format") String format
    ) throws IOException {

        Match match = matchBuilder.build(format, teamId1, teamId2);
        return new ResponseEntity<>(matchRepositoryService.saveMatch(match), HttpStatus.CREATED);
    }

}