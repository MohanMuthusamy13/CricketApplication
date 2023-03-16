package com.example.CricketApplication.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "score_recorder")
public class ScoreRecord {

    @Transient
    public static final String SEQUENCE_NAME = "score_recorder_seq";

    @Id
    private long ballId;

    private long matchId;

    private String overCount;

    private String ballOutcome;

    private int innings;

    private int totalRunsScoredByBattingTeam;

    private int totalWicketsDown;

    private Player batsman;

    private Player bowler;
}