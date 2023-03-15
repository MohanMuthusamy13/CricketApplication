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
@Document(indexName = "teams")
public class Team {

    @Transient
    public static final String SEQUENCE_NAME = "sequenceForTeams";

    @Id
    private Long teamId;

    private String teamName;

    private int matchesPlayed;

    private List<Player> players;

}