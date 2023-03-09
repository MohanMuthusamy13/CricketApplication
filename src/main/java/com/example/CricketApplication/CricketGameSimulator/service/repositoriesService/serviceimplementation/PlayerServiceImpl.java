package com.example.CricketApplication.CricketGameSimulator.service.repositoriesService.serviceimplementation;

import com.example.CricketApplication.CricketGameSimulator.entities.Player;
import com.example.CricketApplication.CricketGameSimulator.exceptionHandler.NotFoundException;
import com.example.CricketApplication.CricketGameSimulator.repositories.PlayerRepository;
import com.example.CricketApplication.CricketGameSimulator.service.repositoriesService.serviceinterfaces.PlayerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player getPlayerById(Long id) {
        Player player = playerRepository.findById(id).orElse(null);
        if (player == null) {
            throw new NotFoundException("Player with the given id does not exist");
        }
        return player;
    }

    @Override
    public Player findByName(String name) {
        Player player = playerRepository.findByName(name);
        if (player == null) {
            throw new NotFoundException("Player with the given name does not exist");
        }
        return player;
    }

    @Override
    public List<Player> getPlayersWithTeamName(String teamName) {
        return playerRepository.findByTeamName(teamName);
    }

    @Override
    public List<Player> getPlayersWithBaseAbility(String baseAbility) {
        return playerRepository.findByBaseAbility(baseAbility);
    }

    @Override
    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }
}