package com.example.CricketApplication.service.serviceinterfaces;

import com.example.CricketApplication.entities.MatchStatusRecord;
import org.springframework.stereotype.Service;

@Service
public interface MatchStatusService {
    MatchStatusRecord save(MatchStatusRecord matchStatusRecord);
    MatchStatusRecord getMatchRecordByMatchId(long matchId);
    String getMatchStatusByMatchId(long matchId);
}