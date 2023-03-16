package com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceimplementation;

import com.example.CricketApplication.entities.Player;
import com.example.CricketApplication.exceptionhandler.NotFoundException;
import com.example.CricketApplication.repositories.repositoryImpl.PlayerRepositoryImpl;
import com.example.CricketApplication.service.serviceimplementation.PlayerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {

    @Mock
    private PlayerRepositoryImpl playerRepository;

    @InjectMocks
    private PlayerServiceImpl playerServiceUnderTest;

    @BeforeEach
    void setUp() {

    }

    Player player1 = Player.builder()
            .id(1L)
            .name("Virat Kohli")
            .teamName("India")
            .baseAbility("Batsman")
            .build();

    Player player2 = Player.builder()
            .id(2L)
            .name("Umesh Yadav")
            .teamName("India")
            .baseAbility("Bowler")
            .build();

    @Test
    void getPlayerById_success() throws Exception {
        Mockito.when(playerRepository.findById(player1.getId()))
                .thenReturn(Optional.of(player1));

        Player actualPlayer =
                playerServiceUnderTest.getPlayerById(player1.getId());

        assertEquals(player1.getName(), actualPlayer.getName());
    }

    @Test
    void getPlayerById_failure(){
        Mockito.when(playerRepository.findById(5L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            Player actual = playerServiceUnderTest.getPlayerById(5L);

             assertNull(actual.getName());
        });
    }

    @Test
    void findByName_success() {

        Mockito.when(playerRepository.findByName(player1.getName())).thenReturn(player1);

        Player actualPlayer = playerServiceUnderTest.findByName(player1.getName());

        assertEquals(player1.getName(), actualPlayer.getName());
        assertEquals(player1.getBaseAbility(), actualPlayer.getBaseAbility());

    }

    @Test
    void findByName_failure() {
        Mockito.when(playerRepository.findByName("Mohammed Shami")).thenReturn(null);

        assertThrows(NotFoundException.class, () -> {
            Player actual = playerServiceUnderTest.findByName("Mohammed Shami");
            assertNull(actual.getName());
        });
    }

    @Test
    void getPlayersWithTeamName() {
        List<Player> exceptedPlayers = List.of(player1, player2);
        Mockito.when(playerRepository.findByTeamName("India")).thenReturn(exceptedPlayers);

        List<Player> players = playerServiceUnderTest.getPlayersWithTeamName("India");

        assertEquals(exceptedPlayers.get(1).getName(), players.get(1).getName());
        assertEquals(exceptedPlayers.get(0).getTeamName(), players.get(0).getTeamName());
    }

    @Test
    void getPlayersWithBaseAbility() {
        List<Player> exceptedPlayers = List.of(player1);
        Mockito.when(playerRepository.findByBaseAbility("Batsman")).thenReturn(exceptedPlayers);

        List<Player> players = playerServiceUnderTest.getPlayersWithBaseAbility("Batsman");

        assertEquals(exceptedPlayers.get(0).getName(), players.get(0).getName());
        assertEquals(exceptedPlayers.get(0).getTeamName(), players.get(0).getTeamName());
    }

    @Test
    void savePlayer() {
        Mockito.when(playerRepository.save(player1)).thenReturn(player1);

        Player actualPlayer = playerServiceUnderTest.savePlayer(player1);

        assertEquals(player1.getName(), actualPlayer.getName());
        assertEquals(player1.getTeamName(), actualPlayer.getTeamName());

    }
}