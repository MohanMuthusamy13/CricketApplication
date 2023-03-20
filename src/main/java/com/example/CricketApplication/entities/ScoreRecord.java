package com.example.CricketApplication.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@Builder
@Document(collection = "score_recorder")
public final class ScoreRecord {

    @Transient
    public static final String SEQUENCE_NAME = "score_recorder_seq";

    @Id
    private final long ballId;

    private final long matchId;

    private final String overCount;

    private final String ballOutcome;

    private final int innings;

    private final int totalRunsScoredByBattingTeam;

    private final int totalWicketsDown;

    private final Player batsman;

    private final Player bowler;
}