package com.example.CricketApplication.ApplicationPackages.controller;

import com.example.CricketApplication.ApplicationPackages.entities.Player;
import com.example.CricketApplication.ApplicationPackages.entities.Team;
import com.example.CricketApplication.ApplicationPackages.entities.builders.TeamBuilder;
import com.example.CricketApplication.ApplicationPackages.service.repositoriesService.serviceimplementation.TeamServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TeamControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TeamServiceImpl teamRepositoryService;
    @Mock
    private TeamBuilder teamBuilder;

    @InjectMocks
    private TeamController teamController;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(teamController).build();
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


    List<Team> teams = new ArrayList<>(Arrays.asList(team1, team2));

    @Test
    void getALlTeams() throws Exception {

        Mockito.when(teamRepositoryService.getAllTeams()).thenReturn(teams);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/cricketGame/getAllTeams");

        mockMvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[0].teamName", is("India")))
                .andExpect(jsonPath("$[1].matchesPlayed", is(1)));
    }

    @Test
    void getTeamById() throws Exception {
        Mockito.when(teamRepositoryService.getTeamById(team1.getTeamId())).thenReturn(team1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/cricketGame/getTeamById?id=1");

        mockMvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.teamName", is("India")));
    }

    @Test
    void createTeam() throws Exception {
        Mockito.when(teamBuilder.build(team1.getTeamName())).thenReturn(team1);
        Mockito.when(teamRepositoryService.saveTeam(team1)).thenReturn(team1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/cricketGame/createTeam")
                .param("teamName", team1.getTeamName());

        mockMvc.perform(mockRequest).andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.teamName", is("India")))
                .andExpect(jsonPath("$.matchesPlayed", is(1)));
    }

    @Test
    void updateTeam() throws Exception {
        Team updatedTeam = Team.builder()
                .teamId(1L)
                .teamName("Australia")
                .matchesPlayed(1)
                .players(List.of(
                        Player.builder().id(1L).name("Joe").teamName("Australia").build(),
                        Player.builder().id(2L).name("Johny Tim").teamName("Australia").build()
                ))
                .build();

        Mockito.when(teamRepositoryService.updateTeam(team1.getTeamId(), updatedTeam))
                .thenReturn(updatedTeam);

        String content = objectWriter.writeValueAsString(updatedTeam);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/cricketGame/updateTeam?id=1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.teamName", is("Australia")));
    }

    @Test
    void deleteTeam() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .delete("/cricketGame/deleteTeam?id=1");

        mockMvc.perform(mockRequest)
                .andExpect(status().isNoContent());
    }
}