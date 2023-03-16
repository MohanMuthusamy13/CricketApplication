package com.example.CricketApplication.cricketgamesimulator.service.repositoriesservice.serviceimplementation;

import com.example.CricketApplication.entities.Match;
import com.example.CricketApplication.entities.Player;
import com.example.CricketApplication.entities.Team;
import com.example.CricketApplication.repositories.MatchRepository;
import com.example.CricketApplication.service.repositoriesservice.serviceimplementation.MatchServiceImpl;
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
class MatchServiceImplTest {

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private MatchServiceImpl matchService;

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

    Match updatedMatch = Match.builder()
            .matchId(1L)
            .matchFormat("TEST")
            .teamsPlayed(List.of(team1, team2))
            .build();


    @Test
    void saveMatch() {
        Mockito.when(matchRepository.save(match)).thenReturn(match);

        Match match1 = matchService.saveMatch(match);

        assertEquals(match1.getMatchFormat(), match.getMatchFormat());
        assertEquals(match1.getTeamsPlayed(), match.getTeamsPlayed());
    }

    @Test
    void getMatchById() throws Exception {
        Mockito.when(matchRepository.findById(match.getMatchId()))
                .thenReturn(Optional.of(match));

        Match match1 =
                matchService.getMatchById(match.getMatchId());

        assertEquals(match1.getMatchFormat(), match.getMatchFormat());
    }

    @Test
    void getMatchesPlayedByTeamName() {
        List<Match> matches = List.of(match);
        Mockito.when(matchRepository.getMatchesPlayedByTeamName(team1.getTeamName())).thenReturn(matches);

        List<Match> actualMatches = matchService.getMatchesPlayedByTeamName(team1.getTeamName());

        assertEquals(matches.get(0).getMatchFormat(), actualMatches.get(0).getMatchFormat());
        assertEquals(matches.get(0).getTeamsPlayed(), actualMatches.get(0).getTeamsPlayed());
    }

    @Test
    void getMatchesCountPlayedByTeamName() {
        Mockito.when(matchRepository.getMatchesCountPlayedByTeamName(team1.getTeamName())).thenReturn(1);

        int matchCount = matchService.getMatchesCountPlayedByTeamName(team1.getTeamName());

        assertEquals(matchCount, 1);

    }

    @Test
    void updateMatch() throws Exception {
        Mockito.when(matchRepository.findById(match.getMatchId())).thenReturn(Optional.of(match));
        Mockito.when(matchRepository.save(match)).thenReturn(updatedMatch);

        Match actualUpdatedMatch = matchService.updateMatch(match.getMatchId(), updatedMatch);

        assertEquals(actualUpdatedMatch.getMatchFormat(), updatedMatch.getMatchFormat());
    }
}