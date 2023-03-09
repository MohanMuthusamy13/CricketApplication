package com.example.CricketApplication.CricketGameSimulator.service.repositoriesService.serviceimplementation;

import com.example.CricketApplication.CricketGameSimulator.entities.MatchStatusRecord;
import com.example.CricketApplication.CricketGameSimulator.exceptionHandler.NotFoundException;
import com.example.CricketApplication.CricketGameSimulator.repositories.MatchStatusRepository;
import com.example.CricketApplication.CricketGameSimulator.service.repositoriesService.serviceinterfaces.MatchStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatchStatusServiceImpl implements MatchStatusService {

    private MatchStatusRepository matchStatusRepository;

    @Autowired
    public MatchStatusServiceImpl(MatchStatusRepository matchStatusRepository) {
        this.matchStatusRepository = matchStatusRepository;
    }

    @Override
    public MatchStatusRecord save(MatchStatusRecord matchStatusRecord) {
        return matchStatusRepository.save(matchStatusRecord);
    }

    @Override
    public MatchStatusRecord getMatchRecordByMatchId(long matchId) {
        Optional<MatchStatusRecord> matchStatusRecord = matchStatusRepository.getMatchRecordByMatchId(matchId);
        if (matchStatusRecord.isPresent()) {
            return matchStatusRecord.get();
        }
        else {
            throw new NotFoundException("Match Id is Invalid");
        }
    }

    @Override
    public String getMatchStatusByMatchId(long matchId) {
        Optional<String> matchStatus = matchStatusRepository.getMatchStatusByMatchId(matchId);
        if (matchStatus.isPresent()) {
            return matchStatus.get();
        }
        else {
            throw new NotFoundException("Match Id is Invalid");
        }
    }
}