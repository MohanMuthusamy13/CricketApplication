package com.example.CricketApplication.cricketgamesimulator.controller;

import com.example.CricketApplication.cricketgamesimulator.entities.Player;
import com.example.CricketApplication.cricketgamesimulator.entities.PlayerStatsStructure;
import com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceinterfaces.MatchService;
import com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceinterfaces.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Player getOverallMaxScorer() {
        return playerService.getOverAllMaxScorer();
    }

    @GetMapping("/maxWicketTaker")
    public Player getMaxWicketTaker() {
        return playerService.getOverallMaxWicketTaker();
    }

    @GetMapping("/maxBoundariesHitter")
    public Player getMaxBoundariesHitter() {
        return playerService.getMaxBoundariesHitter();
    }

    @GetMapping("/maxSixesHitter")
    public Player getMaxSixesHitter() {
        return playerService.getMaxSixesHitter();
    }

    @GetMapping("/maxScorer/{id}")
    public PlayerStatsStructure getMaxScorerByMatch(
            @PathVariable long id
    ) {
        return matchService.getMaxScorerIdByMatch(id);
    }

    @GetMapping("/maxWicketTaker/{id}")
    public PlayerStatsStructure getMaxWicketTakerById(
            @PathVariable long id
    ) {
        return matchService.getWicketTakerIdByMatch(id);
    }
}