package com.example.CricketApplication.service.serviceinterfaces;

import com.example.CricketApplication.entities.ScoreRecord;

import java.util.List;

public interface ScoreRecorderService {
    ScoreRecord saveScoreRecordPerBall(ScoreRecord scoreRecorder);
    void saveAllRecords(List<ScoreRecord> scoreRecords);
    List<ScoreRecord> getStatsOnParticularBall(long matchId, String over);
    List<ScoreRecord> getStatsOnParticularBallAndInnings(long matchId, String over, int innings);

}