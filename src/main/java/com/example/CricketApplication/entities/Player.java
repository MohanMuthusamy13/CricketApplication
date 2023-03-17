package com.example.CricketApplication.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Document(indexName = "players_stats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {

    @Id
    private String id;

    @Field(type = FieldType.Keyword, name = "name")
    private String name;

    @Field(type = FieldType.Integer, name = "score")
    private int score;

    @Field(type = FieldType.Integer, name = "balls_faced")
    @ToString.Exclude
    private int ballsFaced;

    @Field(type = FieldType.Integer, name = "balls_bowled")
    @ToString.Exclude
    private int ballsBowled;

    @Field(type = FieldType.Integer, name = "wickets_taken")
    private int wicketsTaken;

    @Field(type = FieldType.Keyword, name = "base_ability")
    private String baseAbility;

    @Field(type = FieldType.Integer, name = "matches_played")
    private int matchesPlayed;

    @Field(type = FieldType.Integer, name = "no_of_fours")
    private int noOfFours;

    @Field(type = FieldType.Integer, name = "no_of_sixes")
    private int noOfSixes;

    @Field(type = FieldType.Integer, name = "halfCenturies_count")
    private int halfCenturies;

    @Field(type = FieldType.Integer, name = "centuries_count")
    private int centuries;

    @Field(type = FieldType.Text, name = "active_status")
    @Builder.Default @ToString.Exclude
    private String activeStatus = "inactive";

    @Field(type = FieldType.Keyword, name = "team_name")
    private String teamName;

    public Player(String name, int score, int ballsFaced, int ballsBowled, int wicketsTaken, String baseAbility, int matchesPlayed, int noOfFours, int noOfSixes, int halfCenturies, int centuries, String activeStatus, String teamName) {
        this.name = name;
        this.score = score;
        this.ballsFaced = ballsFaced;
        this.ballsBowled = ballsBowled;
        this.wicketsTaken = wicketsTaken;
        this.baseAbility = baseAbility;
        this.matchesPlayed = matchesPlayed;
        this.noOfFours = noOfFours;
        this.noOfSixes = noOfSixes;
        this.halfCenturies = halfCenturies;
        this.centuries = centuries;
        this.activeStatus = activeStatus;
        this.teamName = teamName;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed += matchesPlayed;
    }

    public void setNoOfFours(int noOfFours) {
        this.noOfFours += noOfFours;
    }

    public void setNoOfSixes(int noOfSixes) {
        this.noOfSixes += noOfSixes;
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