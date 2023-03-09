package com.example.CricketApplication.CricketGameSimulator.controller;


import com.example.CricketApplication.CricketGameSimulator.entities.MatchStatusRecord;
import com.example.CricketApplication.CricketGameSimulator.service.repositoriesService.serviceinterfaces.MatchStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cricketGame/display")
public class ScoreBoardDisplayController {

    private MatchStatusService matchStatusService;

    @Autowired
    public ScoreBoardDisplayController(MatchStatusService matchStatusService) {
        this.matchStatusService = matchStatusService;
    }

    @GetMapping("/getMatchStatusRecord/{id}")
    public ResponseEntity<MatchStatusRecord> getMatchStatusRecord(
            @PathVariable(value = "id") long matchId
    ) {
        return new ResponseEntity<>(
                matchStatusService.getMatchRecordByMatchId(matchId),
                HttpStatus.OK
        );
    }

    @GetMapping("/getMatchStatus/{id}")
    public ResponseEntity<String> getMatchStatus(
            @PathVariable(value = "id") long matchId
    ) {
        return new ResponseEntity<>(
                matchStatusService.getMatchStatusByMatchId(matchId),
                HttpStatus.OK
        );
    }
}