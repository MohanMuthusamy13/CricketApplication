package com.example.CricketApplication.ApplicationPackages.service.repositoriesService.serviceinterfaces;

import com.example.CricketApplication.ApplicationPackages.entities.ScoreRecord;

public interface ScoreRecorderService {
    ScoreRecord saveScoreRecordPerBall(ScoreRecord scoreRecorder);
}