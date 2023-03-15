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

    @Field(type = FieldType.Keyword, name = "TeamName")
    private String teamName;

    @Field(type = FieldType.Integer, name = "MatchesPlayed")
    private int matchesPlayed;

    @Field(type = FieldType.Nested, name = "Players")
    private List<Player> players;

}