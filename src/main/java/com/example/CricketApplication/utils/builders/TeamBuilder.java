package com.example.CricketApplication.utils.builders;


import com.example.CricketApplication.entities.Team;
import com.example.CricketApplication.repositories.repositoryImpl.TeamRepository;
import com.example.CricketApplication.utils.SequenceGeneratorService;
import com.example.CricketApplication.service.auxilaryservices.playerservice.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamBuilder {

    private SequenceGeneratorService sequenceGeneratorService;
    private PlayersService playersTeamService;
    private TeamRepository teamRepository;

    @Autowired
    public void setSequenceGeneratorService(SequenceGeneratorService sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Autowired
    public void setPlayersTeamService(PlayersService playersTeamService) {
        this.playersTeamService = playersTeamService;
    }

    @Autowired
    public void setTeamRepository(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team build(String teamName) {
        return Team.builder()
                .teamId(sequenceGeneratorService.getSequenceNumber(Team.SEQUENCE_NAME))
                .teamName(teamName).players(playersTeamService.getPlayers(teamName))
                .matchesPlayed(1).build();
    }
}