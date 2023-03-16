package com.example.CricketApplication.controller;

import com.example.CricketApplication.entities.Player;
import com.example.CricketApplication.service.repositoriesservice.serviceimplementation.PlayerServiceImpl;
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

    private final PlayerServiceImpl playerRepositoryService;

    public PlayerController(PlayerServiceImpl playerRepositoryService) {
        this.playerRepositoryService = playerRepositoryService;
    }

    @GetMapping("/getPlayerById")
    public ResponseEntity<Player> getPlayerById(
            @RequestParam(value = "id") Long id
    ) throws Exception {
        return new ResponseEntity<>(
                playerRepositoryService.getPlayerById(id), HttpStatus.OK
        );
    }

    @GetMapping("/getPlayerByName")
    public ResponseEntity<Player> getPlayerByName(
            @RequestParam(value = "name") String name
    ) throws Exception {
        return new ResponseEntity<>(
                playerRepositoryService.findByName(name), HttpStatus.OK
        );
    }

    @GetMapping("/getPlayersWithTeamName")
    public ResponseEntity<List<Player>> getPlayersWithTeamName(
            @RequestParam(value = "teamName") String teamName
    ) {
        return new ResponseEntity<>(
                playerRepositoryService.getPlayersWithTeamName(teamName), HttpStatus.OK
        );
    }

    @GetMapping("/getPlayersWithBaseAbility")
    public ResponseEntity<List<Player>> getPlayersWithBaseAbility(
            @RequestParam(value = "baseAbility") String baseAbility
    ) {
        return new ResponseEntity<>(
                playerRepositoryService.getPlayersWithBaseAbility(baseAbility), HttpStatus.OK
        );
    }
}