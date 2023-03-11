package com.example.CricketApplication.cricketgamesimulator.controller;

import com.example.CricketApplication.cricketgamesimulator.entities.Player;
import com.example.CricketApplication.cricketgamesimulator.entities.PlayerStatsStructure;
import com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceinterfaces.MatchService;
import com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceinterfaces.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cricketGame/stats")
public class MatchStatisticsController {

    private PlayerService playerService;

    @Autowired
    private MatchService matchService;

    @Autowired
    public MatchStatisticsController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/maxOverallScorer")
    public ResponseEntity<Player> getOverallMaxScorer() {
        return new ResponseEntity<>(
                playerService.getOverAllMaxScorer(),
                HttpStatus.OK);
    }

    @GetMapping("/maxWicketTaker")
    public ResponseEntity<Player> getMaxWicketTaker() {
        return new ResponseEntity<>(
                playerService.getOverallMaxWicketTaker(),
                HttpStatus.OK);
    }

    @GetMapping("/maxBoundariesHitter")
    public ResponseEntity<Player> getMaxBoundariesHitter() {
        return new ResponseEntity<>(
                playerService.getMaxBoundariesHitter(),
                HttpStatus.OK);
    }

    @GetMapping("/maxSixesHitter")
    public ResponseEntity<Player> getMaxSixesHitter() {
        return new ResponseEntity<>(
                playerService.getMaxSixesHitter(),
                HttpStatus.OK);
    }

    @GetMapping("/maxScorer/{id}")
    public ResponseEntity<PlayerStatsStructure> getMaxScorerByMatch(@PathVariable long id) {
        return new ResponseEntity<>(
                matchService.getMaxScorerIdByMatch(id),
                HttpStatus.OK);
    }

    @GetMapping("/maxWicketTaker/{id}")
    public ResponseEntity<PlayerStatsStructure> getMaxWicketTakerById(@PathVariable long id) {
        return new ResponseEntity<>(
                matchService.getWicketTakerIdByMatch(id),
                HttpStatus.OK);
    }

    @GetMapping("/maxStrikeRatePlayer/{id}")
    public ResponseEntity<PlayerStatsStructure> getMaxStrikeRatePlayer(@PathVariable(value = "id") long matchId) {
        return new ResponseEntity<>(
                matchService.getMaxStrikeRatePlayer(matchId),
                HttpStatus.OK);
    }
}