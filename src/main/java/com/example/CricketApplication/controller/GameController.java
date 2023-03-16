package com.example.CricketApplication.controller;

import com.example.CricketApplication.service.auxilaryservices.majorgameservice.GameService;
import com.example.CricketApplication.service.auxilaryservices.initializematchservice.MatchFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cricketGame")

public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/startMatch")
    public ResponseEntity<String> startMatch(
            @RequestParam(value = "matchId") long matchId,
            @RequestParam(value = "matchFormat") String matchFormat
    ) throws Exception {
        String format = MatchFormatService.matchFormatScheduler(matchFormat);
        String status = gameService.startGame(matchId);
        return new ResponseEntity<>("Match is completed", HttpStatus.OK);
    }

}