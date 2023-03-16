package com.example.CricketApplication.service.auxilaryservices.majorgameservice;

public interface GameService {
    String startGame(long matchId) throws Exception;
    void startBattingAndBowling();
}