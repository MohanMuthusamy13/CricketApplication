package com.example.CricketApplication.CricketGameSimulator.repositories;

import com.example.CricketApplication.CricketGameSimulator.entities.Match;
import com.example.CricketApplication.CricketGameSimulator.entities.Player;
import com.example.CricketApplication.CricketGameSimulator.entities.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class MatchRepositoryTest {

    @Autowired
    private MatchRepository matchRepository;

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
    void getMatchesPlayedByTeamName() {

        matchRepository.save(match);

        List<Match> matches = matchRepository.getMatchesPlayedByTeamName(team1.getTeamName());

        assertEquals(matches.get(0).getMatchFormat(), match.getMatchFormat());

    }

    @Test
    void getMatchesCountPlayedByTeamName() {

        matchRepository.save(match);

        int matchCount = matchRepository.getMatchesCountPlayedByTeamName(team1.getTeamName());

        assertEquals(matchCount, 1);
    }
}