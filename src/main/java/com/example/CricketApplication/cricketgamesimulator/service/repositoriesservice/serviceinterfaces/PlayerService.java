package com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceinterfaces;

import com.example.CricketApplication.cricketgamesimulator.entities.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerService {
    Player getPlayerById(String id) throws Exception;
    Player savePlayer(Player player);
    Player updatePlayer(String id, Player player);
    List<Player> getPlayerByName(String name);
    List<Player> getPlayersByTeamAndBaseAbility(String teamName, String baseAbility);
    List<Player> getPlayersByTeamName(String teamName);

}