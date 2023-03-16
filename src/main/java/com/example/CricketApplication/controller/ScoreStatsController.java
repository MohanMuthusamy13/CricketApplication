package com.example.CricketApplication.controller;

import com.example.CricketApplication.entities.ScoreRecord;
import com.example.CricketApplication.service.serviceinterfaces.ScoreRecorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cricket-game/score-stats")
public class ScoreStatsController {

    private final ScoreRecorderService scoreRecorderService;

    @Autowired
    public ScoreStatsController(ScoreRecorderService scoreRecorderService) {
        this.scoreRecorderService = scoreRecorderService;
    }

    @GetMapping("/compare-stats/{id}")
    public ResponseEntity<List<ScoreRecord>> getScoreStatOnParticularBall(
            @PathVariable(value = "id") long matchId,
            @RequestParam(value = "over") String over
    ) {
        return new ResponseEntity<>(
                scoreRecorderService.getStatsOnParticularBall(matchId, over),
                HttpStatus.OK
        );
    }

    @GetMapping("/stats-particular-ball/{id}")
    public ResponseEntity<List<ScoreRecord>> getStatsOnParticularBallAndInnings(
            @PathVariable(value = "id") long matchId,
            @RequestParam(value = "over") String over,
            @RequestParam(value = "innings") int innings
    ) {
        return new ResponseEntity<>(
                scoreRecorderService
                        .getStatsOnParticularBallAndInnings(matchId, over, innings),
                HttpStatus.OK
        );
    }
}