package com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceinterfaces;

import com.example.CricketApplication.cricketgamesimulator.entities.MatchStatusRecord;
import org.springframework.stereotype.Service;

@Service
public interface MatchStatusService {
    MatchStatusRecord save(MatchStatusRecord matchStatusRecord);

}