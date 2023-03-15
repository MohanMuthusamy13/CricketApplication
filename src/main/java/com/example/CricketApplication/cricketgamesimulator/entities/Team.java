package com.example.CricketApplication.cricketgamesimulator.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "teams")
public class Team {

    @Id
    private String teamId;

    @Field(type = FieldType.Keyword, name = "team_name")
    private String teamName;

    @Field(type = FieldType.Integer, name = "matches_played")
    private int matchesPlayed;

    @Field(type = FieldType.Nested, name = "players")
    private List<Player> players;

}