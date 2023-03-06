package com.example.CricketApplication.ApplicationPackages.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "MatchStatus")
public class MatchStatusRecord {

    public static final String SEQUENCE_NAME = "matchStatusRecorderSeq";

    @Id
    private long matchStatusRecordId;

    private long matchId;

    private Map<Long, Integer> scoreOfBothTeams;

    private String matchStatus;

}