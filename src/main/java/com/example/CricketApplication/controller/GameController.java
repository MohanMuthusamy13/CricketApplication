package com.example.CricketApplication.controller;

import com.example.CricketApplication.service.services.majorgameservice.GameService;
import com.example.CricketApplication.service.services.matchformatservice.MatchFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.CricketApplication.service.services.majorgameservice.GameServiceImpl;

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