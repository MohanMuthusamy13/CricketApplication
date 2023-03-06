package com.example.CricketApplication.sequencegenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "dbSequenceForMatches")
public class DBSeqForMatches {

    @Id
    private String id;
    private int seq;
}