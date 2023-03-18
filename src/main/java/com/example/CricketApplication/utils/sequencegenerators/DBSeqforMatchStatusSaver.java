package com.example.CricketApplication.utils.sequencegenerators;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "db_sequence_for_match_status_saver")
public class DBSeqforMatchStatusSaver {
    @Id
    private String id;
    private int seq;
}