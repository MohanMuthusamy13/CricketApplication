package com.example.CricketApplication.cricketgamesimulator.service.serviceImpl;

import com.example.CricketApplication.cricketgamesimulator.entities.ScoreRecord;
import com.example.CricketApplication.cricketgamesimulator.repositories.ScoreRecorderRepository;
import com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceinterfaces.ScoreRecorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreRecorderImpl implements ScoreRecorderService {

    private ScoreRecorderRepository scoreRecorderRepository;

    @Autowired
    public ScoreRecorderImpl(ScoreRecorderRepository scoreRecorderRepository) {
        this.scoreRecorderRepository = scoreRecorderRepository;
    }

    @Override
    public ScoreRecord saveScoreRecordPerBall(ScoreRecord scoreRecorder) {
        return scoreRecorderRepository.save(scoreRecorder);
    }

    @Override
    public ScoreRecord getBallOutcome(String matchId, String overCount, int innings) {
        return scoreRecorderRepository.getBallOutcome(matchId, overCount, innings);
    }

    @Override
    public List<ScoreRecord> getStatsOnParticularBall(String matchId, String over) {
        return scoreRecorderRepository.getStatsOnParticularBallOnBothInnings(matchId, over);
    }
}