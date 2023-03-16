package com.example.CricketApplication.controller;

import com.example.CricketApplication.entities.Match;
import com.example.CricketApplication.utils.builders.MatchBuilder;
import com.example.CricketApplication.service.repositoriesservice.serviceimplementation.MatchServiceImpl;
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
    public ResponseEntity<Match> getMatchById(
            @PathVariable(value = "id") Long id
    ) throws Exception {
        return new ResponseEntity<>(
                matchRepositoryService.getMatchById(id),
                HttpStatus.OK);
    }

    @GetMapping("/getMatchesPlayedByTeamName")
    public ResponseEntity<List<Match>> getMatchesPlayedByTeamName(
            @RequestParam(value = "teamName") String teamName
    ) {
        return new ResponseEntity<>(
                matchRepositoryService.getMatchesPlayedByTeamName(teamName),
                HttpStatus.OK);
    }

    @GetMapping("/getMatchesCountPlayedByTeamName")
    public ResponseEntity<Integer> getMatchesCountPlayedByTeamName(
            @RequestParam(value = "teamName") String teamName
    ) {
        return new ResponseEntity<>(
                matchRepositoryService.getMatchesCountPlayedByTeamName(teamName),
                HttpStatus.OK);
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