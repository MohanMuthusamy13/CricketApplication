package com.example.CricketApplication.cricketgamesimulator.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "match_status")
public class MatchStatusRecord {

    @Id
    private String matchStatusRecordId;

    @Field(type = FieldType.Keyword, name = "MatchId")
    private String matchId;

    @Field(type = FieldType.Object, name = "Score Of Teams")
    private Map<String, Integer> scoreOfBothTeams;

    @Field(type = FieldType.Text, name = "MatchStatus")
    private String matchStatus;

}