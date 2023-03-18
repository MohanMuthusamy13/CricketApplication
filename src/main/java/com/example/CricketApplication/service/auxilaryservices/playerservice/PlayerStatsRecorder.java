package com.example.CricketApplication.service.auxilaryservices.playerservice;

import com.example.CricketApplication.entities.Player;
import com.example.CricketApplication.service.auxilaryservices.majorgameservice.GameStarter;
import com.example.CricketApplication.service.serviceinterfaces.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerStatsRecorder {

    @Autowired
    public PlayerService playerService;

    public void savePlayerStat(List<Player> updatedPlayers) {
        if (GameStarter.getMatchTeams().isMatchFinished()) {
            updatedPlayers
                    .forEach(player -> player.setMatchesPlayed(player.getMatchesPlayed() + 1));
        }
        updatedPlayers.forEach(player -> playerService.updatePlayer(player.getId(), player));
    }

    public void savePlayerStats(List players) {
        for (Object player : players) {
            savePlayerStat((List<Player>)player);
        }
    }
}