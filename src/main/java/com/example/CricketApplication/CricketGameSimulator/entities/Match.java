package com.example.CricketApplication.CricketGameSimulator.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "Matches")
public class Match {

    @Transient
    public static final String SEQUENCE_NAME = "sequenceForMatches";

    @Id
    private Long matchId;

    private String matchFormat;

    private List<Team> teamsPlayed;

    private String matchStatus;
}