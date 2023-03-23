package com.example.CricketApplication.controller;

import com.example.CricketApplication.service.auxilaryservices.initializematchservice.MatchFormatService;
import com.example.CricketApplication.service.auxilaryservices.majorgameservice.GameStarter;
import com.example.CricketApplication.service.auxilaryservices.majorgameservice.ResumeMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/cricket-game")

public class GameController {

    private final GameStarter gameService;

    @Autowired
    private ResumeMatchService resumeMatchService;

    @Autowired
    public GameController(GameStarter gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/start-match")
    public ResponseEntity<String> startMatch(
            @RequestParam String type,
            @RequestParam(value = "id") long matchId,
            @RequestParam(value = "format") String matchFormat
    ) throws Exception {
        MatchFormatService.matchFormatScheduler(matchFormat);
        gameService.startGame(matchId, type);
        return new ResponseEntity<>("MATCH IS COMPLETED", HttpStatus.OK);
    }

    @GetMapping("/resume-match")
    public ResponseEntity<String> resumeMatch() throws Exception {
        resumeMatchService.resumeMatch("PER BALL");
        return new ResponseEntity<>("MATCH HAS RESUMED AND COMPLETED",
                HttpStatus.OK);
    }
}