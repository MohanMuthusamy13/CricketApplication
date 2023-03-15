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

    @Field(type = FieldType.Keyword, name = "match_id")
    private String matchId;

    @Field(type = FieldType.Object, name = "score_of_teams")
    private Map<String, Integer> scoreOfBothTeams;

    @Field(type = FieldType.Text, name = "match_status")
    private String matchStatus;

}