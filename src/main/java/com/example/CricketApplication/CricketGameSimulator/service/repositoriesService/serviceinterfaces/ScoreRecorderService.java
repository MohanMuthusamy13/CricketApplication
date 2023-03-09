package com.example.CricketApplication.CricketGameSimulator.service.repositoriesService.serviceinterfaces;

import com.example.CricketApplication.CricketGameSimulator.entities.ScoreRecord;

public interface ScoreRecorderService {
    ScoreRecord saveScoreRecordPerBall(ScoreRecord scoreRecorder);
}