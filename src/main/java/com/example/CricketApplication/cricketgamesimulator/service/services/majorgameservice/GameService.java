package com.example.CricketApplication.cricketgamesimulator.service.services.majorgameservice;

public interface GameService {
    String startGame(String matchId) throws Exception;
    void startBattingAndBowling();
}