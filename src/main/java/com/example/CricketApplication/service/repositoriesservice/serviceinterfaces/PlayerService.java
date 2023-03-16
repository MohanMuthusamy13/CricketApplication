package com.example.CricketApplication.service.repositoriesservice.serviceinterfaces;

import com.example.CricketApplication.entities.Player;

import java.util.List;

public interface PlayerService {
    Player getPlayerById(Long id) throws Exception;
    List<Player> getPlayersWithTeamName(String teamName);
    List<Player> getPlayersWithBaseAbility(String baseAbility);
    Player findByName(String name) throws Exception;
    Player savePlayer(Player player);
    Player updatePlayer(long id, Player player);
    Player getOverAllMaxScorer();
    Player getOverallMaxWicketTaker();
    Player getMaxBoundariesHitter();
    Player getMaxSixesHitter();
}