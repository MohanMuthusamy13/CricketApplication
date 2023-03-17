package com.example.CricketApplication.service.serviceimpl;

import com.example.CricketApplication.entities.Player;
import com.example.CricketApplication.exceptionhandler.NotFoundException;
import com.example.CricketApplication.repositories.PlayerRepository;
import com.example.CricketApplication.service.serviceinterfaces.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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