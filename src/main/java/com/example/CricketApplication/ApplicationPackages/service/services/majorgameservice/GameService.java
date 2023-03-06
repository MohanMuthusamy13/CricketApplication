package com.example.CricketApplication.ApplicationPackages.service.services.majorgameservice;

import java.io.IOException;

public interface GameService {
    void matchFormatScheduler(String matchFormat);
    void startGame(long matchId) throws Exception;
    void startBattingAndBowling();
}