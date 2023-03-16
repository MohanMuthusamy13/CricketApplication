package com.example.CricketApplication.service.serviceimplementation;

import com.example.CricketApplication.entities.MatchStatusRecord;
import com.example.CricketApplication.exceptionhandler.NotFoundException;
import com.example.CricketApplication.repositories.repositoryImpl.MatchStatusRepositoryImpl;
import com.example.CricketApplication.service.serviceinterfaces.MatchStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatchStatusServiceImpl implements MatchStatusService {

    private MatchStatusRepositoryImpl matchStatusRepository;

    @Autowired
    public MatchStatusServiceImpl(MatchStatusRepositoryImpl matchStatusRepository) {
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