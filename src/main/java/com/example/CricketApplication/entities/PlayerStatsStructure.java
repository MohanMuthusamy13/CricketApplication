package com.example.CricketApplication.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerStatsStructure {
    private long _id;

    private String name;

    private int score;

    private int wicketsTaken;

    private String teamName;

    private int ballsFaced;

    private int ballsBowled;

    private float strikeRate;

}