package com.example.CricketApplication.cricketgamesimulator.entities.factories;

import com.example.CricketApplication.cricketgamesimulator.entities.Player;
import com.example.CricketApplication.cricketgamesimulator.entities.Team;
import com.example.CricketApplication.cricketgamesimulator.service.serviceImpl.PlayerServiceImpl;
import com.example.CricketApplication.cricketgamesimulator.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerFactory {

    @Autowired
    private PlayerServiceImpl playerRepositoryService;



    public Player createBatsmanWithInitialConditions(String playerName, String teamName, Team team) {
        Player player = new Player(
                playerName,
                Constants.INITIAL_SCORE,
                Constants.INITIAL_BALL_FACED_COUNT,
                Constants.INITIAL_BALL_COUNT,
                Constants.INITIAL_WICKETS_TAKEN,
                "Batsman",
                Constants.INITIAL_MATCHES_PLAYED_COUNT,
                Constants.INITIAL_FOURS_COUNT,
                Constants.INITIAL_SIXES_COUNT,
                Constants.INITIAL_HALF_CENTURIES_COUNT,
                Constants.INITIAL_CENTURIES_COUNT,
                "inactive", teamName);
        playerRepositoryService.savePlayer(player);
        return player;
    }

    public Player createBowlerWithInitialConditions(String playerName, String teamName, Team team) {
        Player player = new Player(
                playerName,
                Constants.INITIAL_SCORE,
                Constants.INITIAL_BALL_FACED_COUNT,
                Constants.INITIAL_BALL_COUNT,
                Constants.INITIAL_WICKETS_TAKEN,
                "Bowler",
                Constants.INITIAL_MATCHES_PLAYED_COUNT,
                Constants.INITIAL_FOURS_COUNT,
                Constants.INITIAL_SIXES_COUNT,
                Constants.INITIAL_HALF_CENTURIES_COUNT ,
                Constants.INITIAL_CENTURIES_COUNT,
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