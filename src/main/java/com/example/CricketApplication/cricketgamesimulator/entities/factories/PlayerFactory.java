package com.example.CricketApplication.cricketgamesimulator.entities.factories;

import com.example.CricketApplication.cricketgamesimulator.entities.Player;
import com.example.CricketApplication.cricketgamesimulator.entities.Team;
import com.example.CricketApplication.cricketgamesimulator.service.auxillaryservices.SequenceGeneratorService;
import com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceimplementation.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerFactory {

    private SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    private PlayerServiceImpl playerRepositoryService;

    @Autowired
    public PlayerFactory(SequenceGeneratorService sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
    }


    public Player createBatsmanWithInitialConditions(String name, String teamName, Team team) {
        Player player = new Player(
                sequenceGeneratorService.getSequenceNumber(Player.SEQUENCE_NAME),
                name, 0, 0, 0,
                0,"Batsman", 0, 0, 0, 0, 0,
                "inactive",teamName);
        playerRepositoryService.savePlayer(player);
        return player;
    }


    public Player createBowlerWithInitialConditions(String name, String teamName, Team team) {
        Player player = new Player(
                sequenceGeneratorService.getSequenceNumber(Player.SEQUENCE_NAME),
                name, 0, 0, 0,
                0, "Bowler", 0, 0, 0, 0 ,0,
                "inactive",teamName);
        playerRepositoryService.savePlayer(player);
        return player;

    }

    public Player createPlayer(String name, String baseAbility,String teamName, Team team) {
        if (baseAbility.equals("Batsman")) {
            return createBatsmanWithInitialConditions(name, teamName,  team);
        }
        else {
            return createBowlerWithInitialConditions(name, teamName, team);
        }
    }
}