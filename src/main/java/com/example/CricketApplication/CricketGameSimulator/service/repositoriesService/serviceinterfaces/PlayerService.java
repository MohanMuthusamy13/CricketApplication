package com.example.CricketApplication.CricketGameSimulator.service.repositoriesService.serviceinterfaces;

import com.example.CricketApplication.CricketGameSimulator.entities.Player;

import java.util.List;

public interface PlayerService {
    Player getPlayerById(Long id) throws Exception;
    List<Player> getPlayersWithTeamName(String teamName);
    List<Player> getPlayersWithBaseAbility(String baseAbility);
    Player findByName(String name) throws Exception;
    Player savePlayer(Player player);
}