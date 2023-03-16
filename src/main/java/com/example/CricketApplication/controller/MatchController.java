package com.example.CricketApplication.controller;

import com.example.CricketApplication.entities.Match;
import com.example.CricketApplication.service.repositoriesservice.serviceinterfaces.MatchService;
import com.example.CricketApplication.utils.builders.MatchBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cricketGame/match")
public class MatchController {

    private final MatchService matchService;

    @Autowired
    private MatchBuilder matchBuilder;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(
            @PathVariable(value = "id") Long id
    ) throws Exception {
        return new ResponseEntity<>(
                matchService.getMatchById(id),
                HttpStatus.OK);
    }

    @GetMapping("/getMatchesPlayedByTeamName")
    public ResponseEntity<List<Match>> getMatchesPlayedByTeamName(
            @RequestParam(value = "teamName") String teamName
    ) {
        return new ResponseEntity<>(
                matchService.getMatchesPlayedByTeamName(teamName),
                HttpStatus.OK);
    }

    @GetMapping("/getMatchesCountPlayedByTeamName")
    public ResponseEntity<Integer> getMatchesCountPlayedByTeamName(
            @RequestParam(value = "teamName") String teamName
    ) {
        return new ResponseEntity<>(
                matchService.getMatchesCountPlayedByTeamName(teamName),
                HttpStatus.OK);
    }

    @PostMapping("/createMatch")
    public ResponseEntity<Match> createMatch(
            @RequestParam(value = "id1") long teamId1,
            @RequestParam(value = "id2") long teamId2,
            @RequestParam(value = "format") String format
    ) throws IOException {

        Match match = matchBuilder.build(format, teamId1, teamId2);
        return new ResponseEntity<>(matchService.saveMatch(match), HttpStatus.CREATED);
    }

}