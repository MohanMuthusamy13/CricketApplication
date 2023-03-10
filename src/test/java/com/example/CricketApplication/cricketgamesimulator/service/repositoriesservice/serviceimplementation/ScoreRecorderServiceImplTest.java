package com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceimplementation;

import com.example.CricketApplication.cricketgamesimulator.entities.Player;
import com.example.CricketApplication.cricketgamesimulator.entities.ScoreRecord;
import com.example.CricketApplication.cricketgamesimulator.repositories.ScoreRecorderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ScoreRecorderServiceImplTest {
    @Mock
    private ScoreRecorderRepository scoreRecorderRepository;

    @InjectMocks
    private ScoreRecorderServiceImpl service;

    Player batsman = Player.builder()
            .id(1L)
            .name("Virat Kohli")
            .teamName("India")
            .baseAbility("Batsman")
            .build();

    Player bowler = Player.builder()
            .id(2L)
            .name("Umesh Yadav")
            .teamName("India")
            .baseAbility("Bowler")
            .build();

    ScoreRecord scoreRecord = ScoreRecord.builder()
            .ballOutcome("6")
            .innings(1)
            .batsman(batsman)
            .bowler(bowler)
            .totalRunsScoredByBattingTeam(90)
            .totalWicketsDown(8)
            .build();

    @Test
    void saveScoreRecordPerBall() {

        Mockito.when(scoreRecorderRepository.save(scoreRecord))
                .thenReturn(scoreRecord);

        ScoreRecord scoreRecord1 = service.saveScoreRecordPerBall(scoreRecord);

        assertEquals(scoreRecord.getBatsman(), scoreRecord1.getBatsman());
        assertEquals(scoreRecord.getBallOutcome(), scoreRecord1.getBallOutcome());

    }
}