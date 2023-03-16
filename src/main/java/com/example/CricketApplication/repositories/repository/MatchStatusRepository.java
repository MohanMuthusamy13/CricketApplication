package com.example.CricketApplication.repositories.repository;

import com.example.CricketApplication.entities.MatchStatusRecord;

import java.util.Optional;

public interface MatchStatusRepository {
    Optional<MatchStatusRecord> getMatchRecordByMatchId(long matchId);
    Optional<String> getMatchStatusByMatchId(long matchId);
}