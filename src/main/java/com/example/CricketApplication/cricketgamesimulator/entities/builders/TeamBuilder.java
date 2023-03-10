package com.example.CricketApplication.cricketgamesimulator.entities.builders;


import com.example.CricketApplication.cricketgamesimulator.entities.Team;
import com.example.CricketApplication.cricketgamesimulator.repositories.TeamRepository;
import com.example.CricketApplication.cricketgamesimulator.service.auxillaryservices.SequenceGeneratorService;
import com.example.CricketApplication.cricketgamesimulator.service.services.playerservice.PlayersService;
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
        Team team = Team.builder()
                .teamId(sequenceGeneratorService.getSequenceNumber(Team.SEQUENCE_NAME))
                .teamName(teamName).players(playersTeamService.getPlayers(teamName))
                .matchesPlayed(1).build();
        return team;
    }
}