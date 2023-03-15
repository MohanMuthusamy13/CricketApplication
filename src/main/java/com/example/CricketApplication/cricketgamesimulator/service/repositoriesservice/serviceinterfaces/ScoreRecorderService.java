package com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceinterfaces;

import com.example.CricketApplication.cricketgamesimulator.entities.ScoreRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScoreRecorderService {
    ScoreRecord saveScoreRecordPerBall(ScoreRecord scoreRecorder);

}