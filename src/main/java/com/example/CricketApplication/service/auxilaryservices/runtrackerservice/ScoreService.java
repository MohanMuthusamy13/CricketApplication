package com.example.CricketApplication.service.auxilaryservices.runtrackerservice;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ScoreService {

    private int currentScore;

    private int ballsTaken;

    private int totalScore;

    @Getter @Setter
    private static int[] scoreOfBothTeams = new int[2];

    public static void addScore(int current_team, int run) {
        scoreOfBothTeams[current_team] += run;
    }

}