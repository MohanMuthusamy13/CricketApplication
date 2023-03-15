package com.example.CricketApplication.cricketgamesimulator.repositories;

import com.example.CricketApplication.cricketgamesimulator.entities.Player;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends ElasticsearchRepository<Player, String> {


}