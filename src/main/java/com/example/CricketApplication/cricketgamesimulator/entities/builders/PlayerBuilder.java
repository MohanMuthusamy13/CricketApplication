package com.example.CricketApplication.cricketgamesimulator.entities.builders;

import com.example.CricketApplication.cricketgamesimulator.entities.Player;
import com.example.CricketApplication.cricketgamesimulator.entities.factories.PlayerFactory;
import com.example.CricketApplication.cricketgamesimulator.entities.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// BUILDER CLASS
@Component
public class PlayerBuilder {
    private String playerName;
    private String baseAbility;
    private String teamName;
    private Team team;

    @Autowired
    private PlayerFactory playerFactory;

    public PlayerBuilder setPlayerName(String name) {
        this.playerName = name;
        return this;
    }

    public PlayerBuilder setBaseAbility(String baseAbility) {
        this.baseAbility = baseAbility;
        return this;
    }

    public PlayerBuilder setTeamName(String teamName) {
        this.teamName = teamName;
        return this;
    }

    public PlayerBuilder setTeam(Team team) {
        this.team = team;
        return this;
    }

    public Player createPlayer() {
        if (baseAbility.equals("Batsman")) {
            return playerFactory.createBatsmanWithInitialConditions(playerName, teamName, team);
        }
        else {
            return playerFactory.createBowlerWithInitialConditions(playerName, teamName, team);
        }
    }
}