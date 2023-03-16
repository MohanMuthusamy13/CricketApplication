package com.example.CricketApplication.cricketgamesimulator.service.serviceImpl;

import co.elastic.clients.elasticsearch._types.aggregations.AggregationBuilders;
import co.elastic.clients.elasticsearch._types.aggregations.TermsAggregation;
import com.example.CricketApplication.cricketgamesimulator.entities.Player;
import com.example.CricketApplication.cricketgamesimulator.exceptionhandler.NotFoundException;
import com.example.CricketApplication.cricketgamesimulator.repositories.PlayerRepository;
import com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceinterfaces.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.Aggregation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    @Override
    public Player getPlayerById(String id) throws Exception {
        Player player = playerRepository.findById(id).orElse(null);
        if (player == null) {
            throw new NotFoundException("Player with the given id does not exist");
        }
        return player;
    }

    @Override
    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player updatePlayer(String id, Player player) {
        Player tempPlayer = playerRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Player with the id is not found")
                );

        tempPlayer.setScore(player.getScore());
        tempPlayer.setBallsFaced(player.getBallsFaced());
        tempPlayer.setBallsBowled(player.getBallsBowled());
        tempPlayer.setWicketsTaken(player.getWicketsTaken());
        tempPlayer.setMatchesPlayed(player.getMatchesPlayed());
        tempPlayer.setNoOfFours(player.getNoOfFours());
        tempPlayer.setNoOfSixes(player.getNoOfSixes());
        tempPlayer.setHalfCenturies(player.getHalfCenturies());
        tempPlayer.setCenturies(player.getCenturies());
        tempPlayer.setActiveStatus(player.getActiveStatus());

        playerRepository.save(tempPlayer);
        return tempPlayer;
    }

    @Override
    public List<Player> getPlayersByTeamAndBaseAbility(String teamName, String baseAbility) {
        return playerRepository.getPlayersByTeamAndBaseAbility(teamName, baseAbility);
    }

    @Override
    public List<Player> getPlayerByName(String name) {
        return playerRepository.getPlayerByName(name);
    }

    @Override
    public List<Player> getPlayersByTeamName(String teamName) {
        return playerRepository.getPlayersByTeamName(teamName);
    }
}