package com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceinterfaces;

import com.example.CricketApplication.cricketgamesimulator.entities.MatchStatusRecord;
import com.example.CricketApplication.cricketgamesimulator.repositories.MatchStatusRepository;
import org.springframework.stereotype.Service;

@Service
public interface MatchStatusService {
    MatchStatusRecord save(MatchStatusRecord matchStatusRecord);
    MatchStatusRecord getMatchStatus(String matchId);


}