package com.example.CricketApplication.service.services.majorgameservice;

public interface GameService {
    String startGame(String matchId) throws Exception;
    void startBattingAndBowling();
}