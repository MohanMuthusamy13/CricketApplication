package com.example.CricketApplication.service.services.playerservice;


import com.example.CricketApplication.entities.Player;
import com.example.CricketApplication.utils.builders.PlayerBuilder;
import com.example.CricketApplication.utils.Constants;
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
        for (int i = 1; i <= Constants.TOTAL_PLAYER_COUNT; i++) {
            if (i <= Constants.BATSMAN_COUNT) {
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
        if (player.getScore() >= Constants.CENTURY) {
            player.setCenturies(Constants.INCREASE_CENTURY_COUNT);
            return;
        }
        if (player.getScore() >= Constants.HALF_CENTURY) {
            player.setHalfCenturies(Constants.INCREASE_HALF_CENTURY_COUNT);
        }
    }
}