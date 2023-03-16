package com.example.CricketApplication.cricketgamesimulator.controller;

import com.example.CricketApplication.cricketgamesimulator.entities.Match;
import com.example.CricketApplication.cricketgamesimulator.entities.builders.MatchBuilder;
import com.example.CricketApplication.cricketgamesimulator.service.serviceImpl.MatchServiceImpl;
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
            @PathVariable(value = "id") String id
    ) throws Exception {
        return new ResponseEntity<>(
                matchRepositoryService.getMatchById(id),
                HttpStatus.OK);
    }

    @PostMapping("/createMatch")
    public ResponseEntity<Match> createMatch(
            @RequestParam(value = "id1") String teamId1,
            @RequestParam(value = "id2") String teamId2,
            @RequestParam(value = "format") String format
    ) throws IOException {

        Match match = matchBuilder.build(format, teamId1, teamId2);
        return new ResponseEntity<>(matchRepositoryService.saveMatch(match), HttpStatus.CREATED);
    }

    @GetMapping("/getMatchesByFormat")
    public ResponseEntity<List<Match>> getMatchesByMatchFormat(
            @RequestParam(value = "format") String matchFormat) {
        return new ResponseEntity<>(
                matchRepositoryService.getMatchesByMatchFormat(matchFormat),
                HttpStatus.OK
        );
    }

    @GetMapping("/getMatchesByTeamName")
    public ResponseEntity<List<Match>> getMatchesByTeamName(
            @RequestParam(value = "teamName") String teamName) {
        return new ResponseEntity<>(
                matchRepositoryService.getMatchesByTeamName(teamName),
                HttpStatus.OK
        );
    }

}