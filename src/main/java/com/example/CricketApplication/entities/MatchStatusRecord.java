package com.example.CricketApplication.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "MatchStatus")
@CompoundIndexes(
        @CompoundIndex(name = "compound_index_id_status",
                       def = "{matchId : 1, matchStatus : 1}")
)
public class MatchStatusRecord {
    public static final String SEQUENCE_NAME = "matchStatusRecorderSeq";

    @Id
    private long matchStatusRecordId;

    private long matchId;

    private Map<Long, Integer> scoreOfBothTeams;

    private String matchStatus;
}