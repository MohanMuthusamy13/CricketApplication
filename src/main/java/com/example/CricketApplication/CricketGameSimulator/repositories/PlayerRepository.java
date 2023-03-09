package com.example.CricketApplication.CricketGameSimulator.repositories;

import com.example.CricketApplication.CricketGameSimulator.entities.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends MongoRepository<Player, Long> {

    List<Player> findByTeamName(String teamName);

    List<Player> findByBaseAbility(String baseAbility);

    Player findByName(String name);

}