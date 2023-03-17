package com.example.CricketApplication.service.serviceimpl;

import com.example.CricketApplication.entities.MatchStatusRecord;
import com.example.CricketApplication.repositories.MatchStatusRepository;
import com.example.CricketApplication.service.serviceinterfaces.MatchStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchStatusImpl implements MatchStatusService {

    private MatchStatusRepository matchStatusRepository;

    @Autowired
    public MatchStatusImpl(MatchStatusRepository matchStatusRepository) {
        this.matchStatusRepository = matchStatusRepository;
    }

    @Override
    public MatchStatusRecord save(MatchStatusRecord matchStatusRecord) {
        return matchStatusRepository.save(matchStatusRecord);
    }

    @Override
    public MatchStatusRecord getMatchStatus(String matchId) {
        return matchStatusRepository.getMatchStatus(matchId);
    }
}