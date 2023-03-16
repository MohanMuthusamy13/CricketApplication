package com.example.CricketApplication.cricketgamesimulator.controller;

import com.example.CricketApplication.cricketgamesimulator.entities.ScoreRecord;
import com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceinterfaces.ScoreRecorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cricket-game/scoreStats")
public class ScoreStatsController {

    private ScoreRecorderService scoreRecorderService;

    @Autowired
    public ScoreStatsController(ScoreRecorderService scoreRecorderService) {
        this.scoreRecorderService = scoreRecorderService;
    }

    @GetMapping("/compareStats")
    public ResponseEntity<List<ScoreRecord>> getScoreStatOnParticularBall(
            @RequestParam(value = "id") String matchId,
            @RequestParam(value = "over") String over
    ) {
        return new ResponseEntity<>(
                scoreRecorderService.getStatsOnParticularBall(matchId, over),
                HttpStatus.OK
        );
    }

    @GetMapping("/getStatsParticularBall")
    public ResponseEntity<ScoreRecord> getStatsOnParticularBallAndInnings(
            @RequestParam(value = "id") String matchId,
            @RequestParam(value = "over") String over,
            @RequestParam(value = "innings") int innings
    ) {
        return new ResponseEntity<>(
                scoreRecorderService
                        .getBallOutcome(matchId, over, innings),
                HttpStatus.OK
        );
    }
}