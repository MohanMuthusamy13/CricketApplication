package com.example.CricketApplication.ApplicationPackages.controller;

import com.example.CricketApplication.ApplicationPackages.entities.Player;
import com.example.CricketApplication.ApplicationPackages.service.repositoriesService.serviceimplementation.PlayerServiceImpl;
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

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PlayerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PlayerServiceImpl playerRepositoryService;

    @InjectMocks
    private PlayerController playerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(playerController).build();
    }

    Player player1 = Player.builder()
            .id(1L)
            .name("Dhoni")
            .teamName("India")
            .baseAbility("Batsman")
            .build();

    Player player2 = Player.builder()
            .id(2L)
            .name("Sachin")
            .teamName("India")
            .baseAbility("Batsman")
            .build();


    @Test
    void getPlayerById() throws Exception {

        Mockito.when(playerRepositoryService.getPlayerById(player1.getId())).thenReturn(player1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/cricketGame/player/getPlayerById?id=1");

        mockMvc.perform(mockRequest)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Dhoni")))
                .andExpect(jsonPath("$.teamName", is("India")));

    }

    @Test
    void getPlayerByName() throws Exception {
        Mockito.when(playerRepositoryService.findByName(player2.getName()))
                .thenReturn(player2);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/cricketGame/player/getPlayerByName")
                .param("name", player2.getName());

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Sachin")))
                .andExpect(jsonPath("$.baseAbility", is("Batsman")));

    }

    @Test
    void getPlayersWithTeamName() throws Exception {
        Mockito.when(playerRepositoryService.getPlayersWithTeamName("India"))
                .thenReturn(List.of(player1, player2));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/cricketGame/player/getPlayersWithTeamName")
                .param("teamName", "India");

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Dhoni")));

    }

    @Test
    void getPlayersWithBaseAbility() throws Exception {
        Mockito.when(playerRepositoryService.getPlayersWithBaseAbility("Batsman")).thenReturn(List.of(player1, player2));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/cricketGame/player/getPlayersWithBaseAbility")
                .param("baseAbility", "Batsman");

        mockMvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$[1].name", is("Sachin")))
                .andExpect(jsonPath("$[1].teamName", is("India")));
    }
}