package com.example.CricketApplication.repositories.repository;

import com.example.CricketApplication.entities.ScoreRecord;

import java.util.List;

public interface ScoreRecorderRepository {
    List<ScoreRecord> getStatsOnParticularBall(long matchId, String over);
    List<ScoreRecord> getStatsOnParticularBallAndInnings(long matchId,
                                                         String over, int innings);
}