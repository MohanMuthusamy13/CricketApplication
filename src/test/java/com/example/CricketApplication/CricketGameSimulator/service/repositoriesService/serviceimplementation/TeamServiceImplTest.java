package com.example.CricketApplication.CricketGameSimulator.service.repositoriesService.serviceimplementation;

import com.example.CricketApplication.CricketGameSimulator.entities.Player;
import com.example.CricketApplication.CricketGameSimulator.entities.Team;
import com.example.CricketApplication.CricketGameSimulator.exceptionHandler.NotFoundException;
import com.example.CricketApplication.CricketGameSimulator.repositories.TeamRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class TeamServiceImplTest {

    private MockMvc mockMvc;

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamServiceImpl teamServiceUnderTest;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(teamServiceUnderTest).build();
    }

    Team team1 = Team.builder()
            .teamId(1L)
            .teamName("India")
            .matchesPlayed(1)
            .players(List.of(
                    Player.builder().id(1L).name("Moji").teamName("India").build(),
                    Player.builder().id(2L).name("Hari").teamName("India").build()
            ))
            .build();

    Team team2 = Team.builder()
            .teamId(2L)
            .teamName("England")
            .matchesPlayed(1)
            .players(List.of(
                    Player.builder().id(1L).name("Joe Root").teamName("England").build(),
                    Player.builder().id(2L).name("Johny Bairstow").teamName("England").build()
            ))
            .build();

    Team updatedTeam = Team.builder()
            .teamId(1L)
            .teamName("India")
            .matchesPlayed(2)
            .players(List.of(
                    Player.builder().id(1L).name("Hari").teamName("India").build(),
                    Player.builder().id(2L).name("Kelvin").teamName("India").build()
            ))
                    .build();

    List<Team> teams = List.of(team1, team2);

    @Test
    void getAllTeams() {

        Mockito.when(teamRepository.findAll()).thenReturn(teams);

        List<Team> actualTeams = teamServiceUnderTest.getAllTeams();

        assertEquals(
                teams.size(), actualTeams.size()
        );
        assertEquals(
                teams.get(0).getTeamName(), actualTeams.get(0).getTeamName()
        );
        assertEquals(
                teams.get(0).getMatchesPlayed(), actualTeams.get(0).getMatchesPlayed()
        );

    }

    @Test
    void saveTeam() {
        Mockito.when(teamRepository.save(team1)).thenReturn(team1);

        Team team = teamServiceUnderTest.saveTeam(team1);

        assertEquals(team1.getTeamName(), team.getTeamName());
        assertEquals(team1.getPlayers(), team.getPlayers());
    }

    @Test
    void getTeamById() throws IOException {
        Mockito.when(teamRepository.findById(team1.getTeamId())).thenReturn(Optional.of(team1));

        Team team = teamServiceUnderTest.getTeamById(team1.getTeamId());

        assertEquals(team.getTeamName(), team1.getTeamName());
        assertEquals(team.getPlayers(), team1.getPlayers());
    }

    @Test
    void willThrowErrorIfIdIsInvalid() {
        Mockito.when(teamRepository.findById(4L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            Team team = teamServiceUnderTest.getTeamById(4L);
            assertEquals("", team.getTeamName());
        });
    }

    @Test
    void updateTeam() throws Exception {
        Mockito.when(teamRepository.findById(team1.getTeamId())).thenReturn(Optional.of(team1));
        Mockito.when(teamRepository.save(team1)).thenReturn(updatedTeam);

        Team actualTeam = teamServiceUnderTest.updateTeam(team1.getTeamId(), updatedTeam);

        assertEquals(actualTeam.getMatchesPlayed(), updatedTeam.getMatchesPlayed());
        assertEquals(actualTeam.getPlayers(), updatedTeam.getPlayers());
    }

    @Test
    void willThrowErrorIfIdIsInvalidWhileUpdating() {
        Mockito.when(teamRepository.findById(4L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            Team team = teamServiceUnderTest.updateTeam(4L, updatedTeam);
            assertEquals("", team.getTeamName());
        });
    }

}