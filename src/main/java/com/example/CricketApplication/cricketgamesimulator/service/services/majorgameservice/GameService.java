package com.example.CricketApplication.cricketgamesimulator.service.services.majorgameservice;

public interface GameService {
    String matchFormatScheduler(String matchFormat);
    String startGame(long matchId) throws Exception;
    void startBattingAndBowling();
}