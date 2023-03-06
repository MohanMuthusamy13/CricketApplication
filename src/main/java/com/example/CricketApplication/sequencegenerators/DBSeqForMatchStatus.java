package com.example.CricketApplication.sequencegenerators;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "MatchStatusSeq")
public class DBSeqForMatchStatus {
    private String id;
    private int seq;
}