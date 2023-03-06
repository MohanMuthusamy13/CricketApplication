package com.example.CricketApplication.ApplicationPackages.service.repositoriesService.serviceinterfaces;

import com.example.CricketApplication.ApplicationPackages.entities.MatchStatusRecord;
import org.springframework.stereotype.Service;

@Service
public interface MatchStatusService {
    MatchStatusRecord save(MatchStatusRecord matchStatusRecord);
    MatchStatusRecord getMatchRecordByMatchId(long matchId);
    String getMatchStatusByMatchId(long matchId);
}