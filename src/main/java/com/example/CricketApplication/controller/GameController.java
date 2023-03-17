package com.example.CricketApplication.controller;

import com.example.CricketApplication.service.services.initializematchservice.MatchFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.CricketApplication.service.services.majorgameservice.GameServiceImpl;

@RestController
@RequestMapping("/cricket-game")

public class GameController {

    @Autowired
    GameServiceImpl cricket;

    @GetMapping("/startMatch")
    public ResponseEntity<String> startMatch(
            @RequestParam(value = "matchId") String matchId,
            @RequestParam(value = "matchFormat") String matchFormat
    ) throws Exception {
        String format = MatchFormatService.matchFormatScheduler(matchFormat);
        String status = cricket.startGame(matchId);
        return new ResponseEntity<>("Match is completed", HttpStatus.OK);
    }

}