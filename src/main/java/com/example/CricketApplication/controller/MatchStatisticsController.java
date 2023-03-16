package com.example.CricketApplication.controller;

import com.example.CricketApplication.entities.Player;
import com.example.CricketApplication.entities.PlayerStatsStructure;
import com.example.CricketApplication.service.repositoriesservice.serviceinterfaces.MatchService;
import com.example.CricketApplication.service.repositoriesservice.serviceinterfaces.PlayerService;
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
}