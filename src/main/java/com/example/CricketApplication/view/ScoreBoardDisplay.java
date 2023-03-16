package com.example.CricketApplication.view;

import com.example.CricketApplication.service.services.scoreservice.ScoreModel;
import com.example.CricketApplication.entities.Team;
import org.springframework.stereotype.Component;
import com.example.CricketApplication.service.services.majorgameservice.GameServiceImpl;
import com.example.CricketApplication.service.services.overservice.OverService;
import com.example.CricketApplication.service.services.windeclarativeservices.WicketStatusProvider;
import com.example.CricketApplication.service.services.windeclarativeservices.WinningStatusProvider;

import java.util.List;

@Component
public class ScoreBoardDisplay {

    public static void printFirstInningsFinalScore() {
        System.out.printf(
                """
                   END OF FIRST INNINGS :
                        
                   TOTAL RUNS SCORED BY TEAM %d : %d
  
                   """,
                GameServiceImpl.getBatting() + 1, GameServiceImpl.getScoreTeams()[GameServiceImpl.getBatting()]);
    }

    public String runsForDisplayProvider(int runsScorePerBall) {
        if (runsScorePerBall < 0) {
            if (runsScorePerBall == -1) {
                return "WICKET";
            }
            else if (runsScorePerBall == -2) {
                return "WIDE";
            }
            else {
                return "NO BALL";
            }
        }
        else {
            return Integer.toString(runsScorePerBall);
        }
    }


    public void showStatusPerBall() {
        System.out.printf(
                """
                        Runs Scored :     %s
                        Overs       :     %s
                        Total Score :     %d
                        Wickets Lose:     %d
                        Current Active Batter : %s\t\t\t\t\tCurrent Active Batter Score : %d
                        Current Active Bowler : %s\t\t\t\t\tCurrent Active Bowler Wickets : %d

                        %n""",
                runsForDisplayProvider(GameServiceImpl.getRunsScorePerBall()),
                OverService.getOverInString(), ScoreModel.getScoreOfBothTeams()[GameServiceImpl.getBatting()],
                WicketStatusProvider.getWicketLose(),
                GameServiceImpl.getBattingPlayer().getName(), GameServiceImpl.getBattingPlayer().getScore(),
                GameServiceImpl.getBowlingPlayer().getName(), GameServiceImpl.getBowlingPlayer().getWicketsTaken()
        );
    }

    public String finalScoreBoard() {
        return String.format("""
                SCORE BOARD
                Score board of Team 1
                %s
                Score board of Team 2
                %s""", GameServiceImpl.getPlayingTeamsPlayers().get(0), GameServiceImpl.getPlayingTeamsPlayers().get(1));
    }

    public void showFinalScoreBoard() {
        System.out.println(finalScoreBoard());
    }

    public String getScoreBoard() {
        return finalScoreBoard();
    }

    public String showScoreOfBothTeams() {
        List<Team> playingTeams = GameServiceImpl.getTeams();
        String result = String.format(
                """
                        FINAL SCORE
                        %s  : %d
                        %s  : %d
                        """,
                playingTeams.get(0).getTeamName(),
                ScoreModel.getScoreOfBothTeams()[0],
                playingTeams.get(1).getTeamName(),
                ScoreModel.getScoreOfBothTeams()[1]);
        System.out.println(result);
        return result;
    }

    public String getResults() {
        List<Team> playingTeams = GameServiceImpl.getTeams();
        if (ScoreModel.getScoreOfBothTeams()[0] > ScoreModel.getScoreOfBothTeams()[1]) {
            String result = WinningStatusProvider.diffProvider(1);
            return String.format("%s Wins", playingTeams.get(0).getTeamName());
        }
        else if (ScoreModel.getScoreOfBothTeams()[0] < ScoreModel.getScoreOfBothTeams()[1]) {
            String result = WinningStatusProvider.diffProvider(2);
            return String.format("%s Wins", playingTeams.get(1).getTeamName());
        }
        return "Draw";
    }

    public void showStatusPerBallForTesting() {
        System.out.println("SCORE BOARD OF BATTING");
        System.out.println(GameServiceImpl.getPlayingTeamsPlayers().get(GameServiceImpl.getBatting()));

        System.out.println("SCORE BOARD OF BOWLING");
        System.out.println(GameServiceImpl.getPlayingTeamsPlayers().get(Math.abs(1 - GameServiceImpl.getBatting())));
    }
}