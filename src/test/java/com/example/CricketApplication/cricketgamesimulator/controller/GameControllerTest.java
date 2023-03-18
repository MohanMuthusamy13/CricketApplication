package com.example.CricketApplication.cricketgamesimulator.controller;

import com.example.CricketApplication.controller.GameController;
import com.example.CricketApplication.service.auxilaryservices.majorgameservice.GameStarter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
class GameControllerTest {

    private MockMvc mockMvc;

    @Mock
    private GameStarter cricket;

    @InjectMocks
    private GameController gameController;

    @BeforeEach
    void setUp() {
        this.mockMvc =
                MockMvcBuilders.standaloneSetup(gameController).build();
    }

    private final long matchId = 1L;
    private final String matchFormat = "ODI";

    @Test
    void startMatch() throws Exception {
        Mockito.when(cricket.startGame(matchId)).thenReturn("Match has Completed");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/cricketGame/startMatch?matchId=1")
                .param("matchFormat", matchFormat);

        mockMvc.perform(mockRequest)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()));

    }
}