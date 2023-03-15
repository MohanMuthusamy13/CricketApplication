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
@Document(indexName = "matches")
public class Match {

    @Id
    private String matchId;

    @Field(type = FieldType.Keyword, name = "MatchFormat")
    private String matchFormat;

    @Field(type = FieldType.Nested, name = "TeamsPlayed")
    private List<Team> teamsPlayed;

    @Field(type = FieldType.Text, name = "MatchStatus")
    private String matchStatus;
}