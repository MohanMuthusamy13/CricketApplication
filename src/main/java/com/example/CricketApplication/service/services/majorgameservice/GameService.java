package com.example.CricketApplication.service.services.majorgameservice;

public interface GameService {
    String startGame(long matchId) throws Exception;
    void startBattingAndBowling();
}