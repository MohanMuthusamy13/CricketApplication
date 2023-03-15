package com.example.CricketApplication.cricketgamesimulator.controller;

import com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceinterfaces.MatchService;
import com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceinterfaces.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
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
}