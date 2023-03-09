package com.example.CricketApplication.CricketGameSimulator.controller;

import com.example.CricketApplication.CricketGameSimulator.entities.Player;
import com.example.CricketApplication.CricketGameSimulator.entities.PlayerStatsStructure;
import com.example.CricketApplication.CricketGameSimulator.service.repositoriesService.serviceinterfaces.MatchService;
import com.example.CricketApplication.CricketGameSimulator.service.repositoriesService.serviceinterfaces.PlayerService;
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