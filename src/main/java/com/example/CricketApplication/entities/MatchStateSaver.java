package com.example.CricketApplication.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@Builder
@Document(collection = "matchStatusSaver")
public final class MatchStateSaver {

    @Transient
    public static final String SEQUENCE_NAME = "match_status_saver_sequence";

    @Id
    private final long id;

    private final long matchId;

    private final int overCount;

    private final int ballCount;

    private final int innings;

    private final int wicketsLose;

    private final int[] score;

    private final int totalOvers;

    private final String matchFormat;

    private final int batting;

    private final int bowling;

    private final int currentBatter;

    private final int currentBowler;
}