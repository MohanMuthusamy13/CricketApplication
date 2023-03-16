package com.example.CricketApplication.controller;

import com.example.CricketApplication.entities.Player;
import com.example.CricketApplication.service.serviceinterfaces.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/cricketGame/player")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerRepositoryService) {
        this.playerService = playerRepositoryService;
    }

    @GetMapping("/getPlayerById")
    public ResponseEntity<Player> getPlayerById(
            @RequestParam(value = "id") Long id
    ) throws Exception {
        return new ResponseEntity<>(
                playerService.getPlayerById(id), HttpStatus.OK
        );
    }

    @GetMapping("/getPlayerByName")
    public ResponseEntity<Player> getPlayerByName(
            @RequestParam(value = "name") String name
    ) throws Exception {
        return new ResponseEntity<>(
                playerService.findByName(name), HttpStatus.OK
        );
    }

    @GetMapping("/getPlayersWithTeamName")
    public ResponseEntity<List<Player>> getPlayersWithTeamName(
            @RequestParam(value = "teamName") String teamName
    ) {
        return new ResponseEntity<>(
                playerService.getPlayersWithTeamName(teamName), HttpStatus.OK
        );
    }

    @GetMapping("/getPlayersWithBaseAbility")
    public ResponseEntity<List<Player>> getPlayersWithBaseAbility(
            @RequestParam(value = "baseAbility") String baseAbility
    ) {
        return new ResponseEntity<>(
                playerService.getPlayersWithBaseAbility(baseAbility), HttpStatus.OK
        );
    }
}