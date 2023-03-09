package com.example.CricketApplication.CricketGameSimulator.service.services.scoreservice;

import com.example.CricketApplication.CricketGameSimulator.entities.Player;
import com.example.CricketApplication.CricketGameSimulator.service.repositoriesService.serviceinterfaces.PlayerService;
import com.example.CricketApplication.CricketGameSimulator.service.services.playerservice.PlayersService;

import java.util.List;

public class PlayerCenturyAndHalfCenturyService {

    public static void saveStatsOfCenturies(Player player) {
        PlayersService.checkForCenturiesAndHalfCenturies(player);
    }

    public static void playerStat(List<Player> players) {
        for (Player player : players) {
            saveStatsOfCenturies(player);
        }
    }

    public static void centuryStatsProvider(List playerTeams) {
        for (Object players : playerTeams) {
            playerStat((List<Player>) players);
        }
    }
}