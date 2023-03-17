package com.example.CricketApplication.service.serviceinterfaces;

import com.example.CricketApplication.entities.ScoreRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScoreRecorderService {
    ScoreRecord saveScoreRecordPerBall(ScoreRecord scoreRecorder);

    ScoreRecord getBallOutcome(String matchId, String overCount, int innings);
    List<ScoreRecord> getStatsOnParticularBall(String matchId, String over);
}