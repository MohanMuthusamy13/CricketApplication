package com.example.CricketApplication.service.auxilaryservices.runtrackerservice;

import com.example.CricketApplication.entities.Player;
import com.example.CricketApplication.service.auxilaryservices.playerservice.PlayersService;

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