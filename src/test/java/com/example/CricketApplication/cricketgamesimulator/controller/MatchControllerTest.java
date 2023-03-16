package com.example.CricketApplication.cricketgamesimulator.controller;

import com.example.CricketApplication.controller.MatchController;
import com.example.CricketApplication.entities.Match;
import com.example.CricketApplication.entities.Player;
import com.example.CricketApplication.entities.Team;
import com.example.CricketApplication.utils.builders.MatchBuilder;
import com.example.CricketApplication.service.repositoriesservice.serviceimplementation.MatchServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MatchControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private MatchBuilder matchBuilder;

    @Mock
    private MatchServiceImpl matchRepositoryService;

    @InjectMocks
    private MatchController matchController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(matchController).build();
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

    Match match = Match.builder()
            .matchId(1L)
            .matchFormat("ODI")
            .teamsPlayed(List.of(team1, team2))
            .build();


    @Test
    void getMatchesPlayedByTeamName() throws Exception {
        Mockito.when(matchRepositoryService.getMatchesPlayedByTeamName(team1.getTeamName()))
                .thenReturn(List.of(match));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/cricketGame/match/getMatchesPlayedByTeamName")
                .param("teamName", team1.getTeamName());

        mockMvc.perform(mockRequest)
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getMatchesCountPlayedByTeamName() throws Exception {
        Mockito.when(matchRepositoryService.getMatchesCountPlayedByTeamName(team1.getTeamName())).thenReturn(1);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/cricketGame/match/getMatchesCountPlayedByTeamName")
                .param("teamName", team1.getTeamName());

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", is(1)));
    }

    @Test
    void createMatch() throws Exception {
        Mockito.when(matchBuilder.build(
                match.getMatchFormat(),
                match.getTeamsPlayed().get(0).getTeamId(), match.getTeamsPlayed().get(1).getTeamId()))
                .thenReturn(match);
        Mockito.when(matchRepositoryService.saveMatch(match)).thenReturn(match);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/cricketGame/match/createMatch?id1=1&id2=2")
                .param("format", match.getMatchFormat());

        mockMvc.perform(mockRequest)
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$", notNullValue()));


    }
}