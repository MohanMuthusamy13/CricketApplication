package com.example.CricketApplication.ApplicationPackages.controller;

import com.example.CricketApplication.ApplicationPackages.view.ScoreBoardDisplay;
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

import static org.hamcrest.Matchers.notNullValue;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ScoreBoardDisplayControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ScoreBoardDisplay scoreBoardDisplay;

    @InjectMocks
    private ScoreBoardDisplayController scoreBoardDisplayController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(scoreBoardDisplayController).build();
    }

    @Test
    void scoreDisplay() throws Exception {

        Mockito.when(scoreBoardDisplay.showScoreOfBothTeams()).thenReturn("Score of Both Teams");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/cricketGame/display/getFinalScore");

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));

    }

    @Test
    void scoreBoard() throws Exception {
        Mockito.when(scoreBoardDisplay.getScoreBoard()).thenReturn("Final Score Board");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/cricketGame/display/getFinalScoreBoard");

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    void results() throws Exception {
        Mockito.when(scoreBoardDisplay.getResults()).thenReturn("Final Result");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/cricketGame/display/getResults");

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

}