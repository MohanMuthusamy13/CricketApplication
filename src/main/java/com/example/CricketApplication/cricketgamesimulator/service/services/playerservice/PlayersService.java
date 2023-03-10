package com.example.CricketApplication.cricketgamesimulator.service.services.playerservice;


import com.example.CricketApplication.cricketgamesimulator.entities.Player;
import com.example.CricketApplication.cricketgamesimulator.entities.builders.PlayerBuilder;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayersService {


    @Autowired
    private PlayerBuilder playerBuilder;

    private final Faker faker = new Faker();

    public PlayersService(PlayerBuilder playerBuilder) {
        this.playerBuilder = playerBuilder;
    }

    public List<Player> getPlayers(String teamName) {
        ArrayList<Player> playerTeam = new ArrayList<>();
        String baseAbility;
        int playerCount = 11;
        for (int i = 1; i <= playerCount; i++) {
            if (i < 6) {
                baseAbility = "Batsman";
            }
            else {
                baseAbility = "Bowler";
            }
            Player player =
                    playerBuilder
                            .setPlayerName(faker.name().fullName())
                            .setBaseAbility(baseAbility)
                            .setTeamName(teamName)
                            .createPlayer();
            playerTeam.add(player);
        };
        return playerTeam;
    }

    public static void checkForCenturiesAndHalfCenturies(Player player) {
        if (player.getScore() >= 100) {
            player.setCenturies(1);
            return;
        }

        if (player.getScore() >= 50) {
            player.setHalfCenturies(1);
        }
    }
}