package com.example.CricketApplication.entities;

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

    @Field(type = FieldType.Keyword, name = "match_id")
    private String matchId;

    @Field(type = FieldType.Text, name = "over_count")
    private String overCount;

    @Field(type = FieldType.Keyword, name = "ball_outCome")
    private String ballOutcome;

    @Field(type = FieldType.Integer, name = "innings")
    private int innings;

    @Field(type = FieldType.Integer, name = "total_runs_scored")
    private int totalRunsScoredByBattingTeam;

    @Field(type = FieldType.Integer, name = "total_wickets_down")
    private int totalWicketsDown;

    @Field(type = FieldType.Object, name = "batsman")
    private Player batsman;

    @Field(type = FieldType.Object, name = "bowler")
    private Player bowler;
}