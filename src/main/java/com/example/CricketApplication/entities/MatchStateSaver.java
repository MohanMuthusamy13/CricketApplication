package com.example.CricketApplication.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "matchStatusSaver")
public class MatchStateSaver {

    @Transient
    public static final String SEQUENCE_NAME = "match_status_saver_sequence";

    @Id
    private long id;

    private long matchId;

    private int overCount;

    private int ballCount;

    private int innings;

    private int wicketsLose;

    private int[] score = new int[2];

    private int totalOvers;

    private String matchFormat;

    private int batting;

    private int bowling;

    private int currentBatter;

    private int currentBowler;
}