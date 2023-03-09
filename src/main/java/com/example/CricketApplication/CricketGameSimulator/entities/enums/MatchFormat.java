package com.example.CricketApplication.CricketGameSimulator.entities.enums;

import lombok.Getter;

@Getter
public enum MatchFormat {
    T20(20),
    ODI(50),
    TEST(80);

    private final int overs;
    MatchFormat(int matchFormat) {
        this.overs = matchFormat;
    }
}