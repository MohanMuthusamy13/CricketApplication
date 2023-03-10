package com.example.CricketApplication.cricketgamesimulator.service.services.majorgameservice;

public interface GameService {
    String startGame(long matchId) throws Exception;
    void startBattingAndBowling();
}