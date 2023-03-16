package com.example.CricketApplication.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("teams")
public class Team {

    @Transient
    public static final String SEQUENCE_NAME = "sequence_for_teams";

    @Id
    private Long teamId;

    private String teamName;

    private int matchesPlayed;

    private List<Player> players;

}