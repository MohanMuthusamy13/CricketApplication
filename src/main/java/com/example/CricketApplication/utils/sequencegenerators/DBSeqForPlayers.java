package com.example.CricketApplication.utils.sequencegenerators;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "DBSequenceForPlayers")
public class DBSeqForPlayers {

    @Id
    private String id;
    private int seq;

}