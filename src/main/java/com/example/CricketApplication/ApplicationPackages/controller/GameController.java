package com.example.CricketApplication.ApplicationPackages.controller;

import com.example.CricketApplication.ApplicationPackages.service.repositoriesService.serviceimplementation.MatchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.CricketApplication.ApplicationPackages.service.services.majorgameservice.GameServiceImpl;

@RestController
@RequestMapping("/cricketGame")

public class GameController {

    @Autowired
    GameServiceImpl cricket;

    @Autowired
    MatchServiceImpl matchRepositoryService;

    @GetMapping("/")
    public String index() {
        return "Done";
    }

    @GetMapping("/startMatch")
    public ResponseEntity<String> startMatch(
            @RequestParam(value = "matchId") long matchId,
            @RequestParam(value = "matchFormat") String matchFormat
    ) throws Exception {
        cricket.matchFormatScheduler(matchFormat);
        cricket.startGame(matchId);
        return new ResponseEntity<>("Match is completed", HttpStatus.OK);
    }

}