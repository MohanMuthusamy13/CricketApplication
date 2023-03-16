package com.example.CricketApplication.utils.sequencegenerators;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "match_status_seq")
public class DBSeqForMatchStatus {
    private String id;
    private int seq;
}