package com.example.CricketApplication.CricketGameSimulator.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Players")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {
    public static final String SEQUENCE_NAME = "player_seq";

    @Id
    private Long id;

    private String name;

    private int score;

    @ToString.Exclude
    private int ballsFaced;

    @ToString.Exclude
    private int ballsBowled;

    private int wicketsTaken;

    private String baseAbility;

    private int matchesPlayed;

    private int halfCenturies;

    private int centuries;

    @ToString.Exclude
    private String activeStatus = "inactive";

    private String teamName;

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed += matchesPlayed;
    }

    public void setCenturies(int centuries) {
        this.centuries += centuries;
    }

    public void setHalfCenturies(int halfCenturies) {
        this.halfCenturies += halfCenturies;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public void setBallsFaced(int ballsFaced) {
        this.ballsFaced += ballsFaced;
    }

    public void setBallsBowled(int ballsBowled) {
        this.ballsBowled += ballsBowled;
    }

    public void setWicketsTaken(int wicketsTaken) {
        this.wicketsTaken += wicketsTaken;
    }
}