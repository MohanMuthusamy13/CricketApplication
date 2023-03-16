package com.example.CricketApplication.service.repositoriesservice.serviceimplementation;

import com.example.CricketApplication.entities.ScoreRecord;
import com.example.CricketApplication.repositories.ScoreRecorderRepository;
import com.example.CricketApplication.service.repositoriesservice.serviceinterfaces.ScoreRecorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<ScoreRecord> getStatsOnParticularBall(long matchId, String over) {
        return scoreRecorderRepository.getStatsOnParticularBall(matchId, over);
    }

    @Override
    public List<ScoreRecord> getStatsOnParticularBallAndInnings(long matchId, String over, int innings) {
        return scoreRecorderRepository.getStatsOnParticularBallAndInnings(matchId, over, innings);
    }
}