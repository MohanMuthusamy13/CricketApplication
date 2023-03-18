package com.example.CricketApplication.utils.builders;

import com.example.CricketApplication.entities.Match;
import com.example.CricketApplication.utils.SequenceGeneratorService;
import com.example.CricketApplication.service.serviceimplementation.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MatchBuilder {

    private SequenceGeneratorService sequenceGeneratorService;
    private TeamServiceImpl teamRepositoryService;

    @Autowired
    public void setSequenceGeneratorService(SequenceGeneratorService sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Autowired
    public void setTeamRepositoryService(TeamServiceImpl teamRepositoryService) {
        this.teamRepositoryService = teamRepositoryService;
    }

    public Match build(String format, long teamId1, long teamId2) throws IOException {
        return Match.builder()
                .matchId(sequenceGeneratorService.getSequenceNumber(Match.SEQUENCE_NAME))
                .teamsPlayed(
                        List.of(
                                teamRepositoryService.getTeamById(teamId1),
                                teamRepositoryService.getTeamById(teamId2)
                        ))
                .matchFormat(format)
                .isMatchFinished(false)
                .build();
    }
}