package com.example.CricketApplication.controller;


import com.example.CricketApplication.entities.MatchStatusRecord;
import com.example.CricketApplication.service.serviceinterfaces.MatchStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cricket-game/display")
public class ScoreBoardDisplayController {

    private final MatchStatusService matchStatusService;

    @Autowired
    public ScoreBoardDisplayController(MatchStatusService matchStatusService) {
        this.matchStatusService = matchStatusService;
    }

    @GetMapping("/match-status-record/{id}")
    public ResponseEntity<MatchStatusRecord> getMatchStatusRecord(
            @PathVariable(value = "id") long matchId
    ) {
        return new ResponseEntity<>(
                matchStatusService.getMatchRecordByMatchId(matchId),
                HttpStatus.OK
        );
    }

    @GetMapping("/match-status/{id}")
    public ResponseEntity<String> getMatchStatus(
            @PathVariable(value = "id") long matchId
    ) {
        return new ResponseEntity<>(
                matchStatusService.getMatchStatusByMatchId(matchId),
                HttpStatus.OK
        );
    }
}