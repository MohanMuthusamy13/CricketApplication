package com.example.CricketApplication.service.auxilaryservices.balltrackerservice;

import com.example.CricketApplication.service.auxilaryservices.runtrackerservice.ScoreService;
import com.example.CricketApplication.service.auxilaryservices.majorgameservice.GameStarter;
import com.example.CricketApplication.utils.Constants;

public class WinningStatusProvider {

    ScoreService score = new ScoreService();

    public byte checkWinningTeamCondition() {
        if (ScoreService.getScoreOfBothTeams()[Constants.FIRST_TEAM] >
                ScoreService.getScoreOfBothTeams()[Constants.SECOND_TEAM]) {
            return Constants.FIRST_TEAM_WINNING_INDICATION;
        } else if (ScoreService.getScoreOfBothTeams()[Constants.FIRST_TEAM] <
                ScoreService.getScoreOfBothTeams()[Constants.SECOND_TEAM]){
            return Constants.SECOND_TEAM_WINNING_INDICATION;
        }
        return Constants.DRAW_INDICATION;
    }

    public String checkWinningStatusForSecondInnings() {
        if ((GameStarter.getInnings() == Constants.SECOND_INNINGS)
                && ((GameStarter.getScoreTeams()[GameStarter.getBattingTeamIndicator()] >
                GameStarter.getScoreTeams()[Math.abs(1 - GameStarter.getBattingTeamIndicator())]))){
            return "Current Team Wins";
        }
        else if (WicketStatusProvider.isAllWicketsDownInSecondInnings()) {
            return "Current Team Loses";
        }
        return "";
    }

    public static int winningWicketsDifference() {
        return Constants.TOTAL_WICKETS - WicketStatusProvider.getWicketLose();
    }

    public static int winningRunsDifference() {
        return GameStarter.getScoreTeams()[Math.abs(1 - GameStarter.getBattingTeamIndicator())] -
                GameStarter.getScoreTeams()[GameStarter.getBattingTeamIndicator()];
    }

    public static String diffProvider(int winningTeam) {
        String diffReveler = "";
        if (GameStarter
                .getFlagForTeamWinningIndicationOnSecondInnings().equals("Current Team Wins")) {
            diffReveler = String.format(
                    "Team %d won by %d wickets"
                    , winningTeam, winningWicketsDifference()
            );
        }
        else if (GameStarter
                .getFlagForTeamWinningIndicationOnSecondInnings().equals("Current Team Loses")){
            diffReveler = String.format(
                    "Team %d won by %d runs"
                   , winningTeam, winningRunsDifference()
            );
        }
        System.out.println(diffReveler);
        return diffReveler;
    }


    public byte checkWinningStatusNumber() {
        if ((OverService.getOverCount() == GameStarter.getTotalOvers()) || (GameStarter.getFlagForTeamWinningIndicationOnSecondInnings().equals("Game Over"))) {
            if (checkWinningTeamCondition() == Constants.FIRST_TEAM_WINNING_INDICATION) {
                return Constants.FIRST_TEAM_WINNING_INDICATION;
            }
            else if (checkWinningTeamCondition() == Constants.SECOND_TEAM_WINNING_INDICATION){
                return Constants.SECOND_TEAM_WINNING_INDICATION;
            }
        }
        return Constants.DRAW_INDICATION;
    }

    public void checkWinningStatus() {
        if (checkWinningStatusNumber() == Constants.FIRST_TEAM_WINNING_INDICATION) {
            System.out.println(diffProvider(1));
            System.out.print("""
                            The Game is over :)
                            Team 1 Wins
                            """);
            diffProvider(1);
        } else if (checkWinningStatusNumber() == Constants.SECOND_TEAM_WINNING_INDICATION) {
            System.out.println(diffProvider(2));
            System.out.print("""
                            The Game is over :)
                            Team 2 Wins
                            """
                    );
            diffProvider(2);
        }
        else {
            System.out.println("The Game is over :)" + "\n" +"Game is drawn");
        }
    }
}