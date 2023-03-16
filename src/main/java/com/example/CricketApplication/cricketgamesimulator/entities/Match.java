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
@Document(indexName = "match")
public class Match {

    @Id
    private String matchId;

    @Field(type = FieldType.Keyword, name = "match_format")
    private String matchFormat;

    @Field(type = FieldType.Nested, name = "teams_played")
    private List<Team> teamsPlayed;

    @Field(type = FieldType.Text, name = "match_status")
    private String matchStatus;
}