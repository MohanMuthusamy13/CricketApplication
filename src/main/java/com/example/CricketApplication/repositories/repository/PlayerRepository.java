package com.example.CricketApplication.repositories.repository;

import com.example.CricketApplication.entities.Player;

import java.util.List;

public interface PlayerRepository {
    Player getOverallMaximumScorer();
    Player getOverallMaxWicketTaker();
    Player getMaxBoundariesHitter();
    Player getMaxSixesHitter();
    List<Player> findByTeamName(String teamName);
    List<Player> findByBaseAbility(String baseAbility);
    Player findByName(String name);
}