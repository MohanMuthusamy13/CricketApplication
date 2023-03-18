package com.example.CricketApplication.controller;

import com.example.CricketApplication.entities.Player;
import com.example.CricketApplication.service.serviceinterfaces.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cricket-game/stats")
public class MatchStatisticsController {

    private final PlayerService playerService;

    @Autowired
    public MatchStatisticsController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/max-overall-scorer")
    public ResponseEntity<Player> getOverallMaxScorer() {
        return new ResponseEntity<>(
                playerService.getOverAllMaxScorer(),
                HttpStatus.OK);
    }

    @GetMapping("/max-wicket-taker")
    public ResponseEntity<Player> getMaxWicketTaker() {
        return new ResponseEntity<>(
                playerService.getOverallMaxWicketTaker(),
                HttpStatus.OK);
    }

    @GetMapping("/max-boundaries-hitter")
    public ResponseEntity<Player> getMaxBoundariesHitter() {
        return new ResponseEntity<>(
                playerService.getMaxBoundariesHitter(),
                HttpStatus.OK);
    }

    @GetMapping("/max-sixes-hitter")
    public ResponseEntity<Player> getMaxSixesHitter() {
        return new ResponseEntity<>(
                playerService.getMaxSixesHitter(),
                HttpStatus.OK);
    }
}