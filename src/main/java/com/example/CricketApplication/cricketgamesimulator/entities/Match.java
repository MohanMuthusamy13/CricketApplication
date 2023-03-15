package com.example.CricketApplication.cricketgamesimulator.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "matches")
public class Match {

    @Transient
    public static final String SEQUENCE_NAME = "sequenceForMatches";

    @Id
    private Long matchId;

    private String matchFormat;

    private List<Team> teamsPlayed;

    private String matchStatus;
}