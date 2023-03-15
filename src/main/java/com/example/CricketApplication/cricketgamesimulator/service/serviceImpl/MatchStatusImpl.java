package com.example.CricketApplication.cricketgamesimulator.service.serviceImpl;

import com.example.CricketApplication.cricketgamesimulator.entities.MatchStatusRecord;
import com.example.CricketApplication.cricketgamesimulator.repositories.MatchStatusRepository;
import com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceinterfaces.MatchStatusService;
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
}