package com.example.CricketApplication.cricketgamesimulator.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "score_recorder")
public class ScoreRecord {

    @Id
    private String ballId;

    @Field(type = FieldType.Keyword, name = "MatchId")
    private String matchId;

    @Field(type = FieldType.Text, name = "OverCount")
    private String overCount;

    @Field(type = FieldType.Keyword, name = "BallOutCome")
    private String ballOutcome;

    @Field(type = FieldType.Integer, name = "Innings")
    private int innings;

    @Field(type = FieldType.Integer, name = "TotalRunsScored")
    private int totalRunsScoredByBattingTeam;

    @Field(type = FieldType.Integer, name = "TotalWicketsDown")
    private int totalWicketsDown;

    @Field(type = FieldType.Object, name = "Batsman")
    private Player batsman;

    @Field(type = FieldType.Object, name = "Bowler")
    private Player bowler;
}