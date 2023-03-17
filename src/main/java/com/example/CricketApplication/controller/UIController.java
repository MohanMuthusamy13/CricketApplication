package com.example.CricketApplication.controller;

import com.example.CricketApplication.entities.Player;
import com.example.CricketApplication.service.serviceinterfaces.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Controller
@RequestMapping("/")
public class UIController {


    private PlayerService playerService;

    @Autowired
    public UIController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/search")
    public String getPlayers(Model model) {
        List<Player> players = playerService.getPlayerByName("Daniel");
        List<String> names = players.stream().flatMap(player->{
            return Stream.of(player.getName());
        }).collect(Collectors.toList());
        log.info("Player names {}", names);
        model.addAttribute("Player Names", names);
        return "playersearch";
    }
}