package com.example.CricketApplication.cricketgamesimulator.controller;

import com.example.CricketApplication.cricketgamesimulator.entities.MatchStatusRecord;
import com.example.CricketApplication.cricketgamesimulator.repositories.MatchStatusRepository;
import com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceinterfaces.MatchService;
import com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceinterfaces.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cricketGame/stats")
public class MatchStatisticsController {

    @Autowired
    private MatchService matchService;

    private final MatchStatusRepository matchStatusRepository;

    @Autowired
    public MatchStatisticsController(PlayerService playerService,
                                     MatchStatusRepository matchStatusRepository) {
        this.matchStatusRepository = matchStatusRepository;
    }

    @GetMapping("/matchStatus")
    public ResponseEntity<MatchStatusRecord> getMatchStatus(
            @RequestParam(value = "id") String matchId
    ) {
        return new ResponseEntity<>(
                matchStatusRepository.getMatchStatus(matchId),
                HttpStatus.OK);
    }
}