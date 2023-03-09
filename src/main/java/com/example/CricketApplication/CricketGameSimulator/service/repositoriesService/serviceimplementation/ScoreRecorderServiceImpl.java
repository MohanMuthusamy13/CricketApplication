package com.example.CricketApplication.CricketGameSimulator.service.repositoriesService.serviceimplementation;

import com.example.CricketApplication.CricketGameSimulator.entities.ScoreRecord;
import com.example.CricketApplication.CricketGameSimulator.repositories.ScoreRecorderRepository;
import com.example.CricketApplication.CricketGameSimulator.service.repositoriesService.serviceinterfaces.ScoreRecorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreRecorderServiceImpl implements ScoreRecorderService {

    private ScoreRecorderRepository scoreRecorderRepository;

    @Autowired
    public ScoreRecorderServiceImpl(ScoreRecorderRepository scoreRecorderRepository) {
        this.scoreRecorderRepository = scoreRecorderRepository;
    }

    @Override
    public ScoreRecord saveScoreRecordPerBall(ScoreRecord scoreRecorder) {
        return scoreRecorderRepository.save(scoreRecorder);
    }
}