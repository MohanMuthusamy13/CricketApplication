package com.example.CricketApplication.CricketGameSimulator.service.repositoriesService.serviceinterfaces;

import com.example.CricketApplication.CricketGameSimulator.entities.MatchStatusRecord;
import org.springframework.stereotype.Service;

@Service
public interface MatchStatusService {
    MatchStatusRecord save(MatchStatusRecord matchStatusRecord);
    MatchStatusRecord getMatchRecordByMatchId(long matchId);
    String getMatchStatusByMatchId(long matchId);
}