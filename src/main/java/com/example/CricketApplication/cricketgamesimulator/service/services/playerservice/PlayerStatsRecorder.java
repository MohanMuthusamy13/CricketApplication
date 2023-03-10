package com.example.CricketApplication.cricketgamesimulator.service.services.playerservice;

import com.example.CricketApplication.cricketgamesimulator.entities.Player;
import com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceinterfaces.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerStatsRecorder {

    @Autowired
    public PlayerService playerService;

    public void savePlayerStat(List<Player> updatedPlayers) {
        for (Player player : updatedPlayers) {
            player.setMatchesPlayed(player.getMatchesPlayed() + 1);
            playerService.updatePlayer(player.getId(), player);
        }
    }
    public void savePlayerStats(List players) {
        for (Object player : players) {
            savePlayerStat((List<Player>)player);
        }
    }
}