package com.example.CricketApplication.entities;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@Builder
@Document(collection = "match_status")
@CompoundIndexes(
        @CompoundIndex(name = "compound_index_id_status",
                       def = "{matchId : 1, matchStatus : 1}")
)
public final class MatchStatusRecord {
    public static final String SEQUENCE_NAME = "match_status_recorder_seq";

    @Id
    private final long matchStatusRecordId;

    private final long matchId;

    private final Map<Long, Integer> scoreOfBothTeams;

    private final String matchStatus;
}