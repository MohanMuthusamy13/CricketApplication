package com.example.CricketApplication.service.auxilaryservices.balltrackerservice;

import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.WicketStatusProvider;
import com.example.CricketApplication.service.auxilaryservices.runtrackerservice.ScoreService;
import com.example.CricketApplication.service.auxilaryservices.majorgameservice.GameServiceImpl;
import com.example.CricketApplication.service.auxilaryservices.balltrackerservice.OverService;
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
        if ((GameServiceImpl.getInnings() == Constants.SECOND_INNINGS)
                && ((GameServiceImpl.getScoreTeams()[GameServiceImpl.getBatting()] >
                GameServiceImpl.getScoreTeams()[Math.abs(1 - GameServiceImpl.getBatting())]))){
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
        return GameServiceImpl.getScoreTeams()[Math.abs(1 - GameServiceImpl.getBatting())] -
                GameServiceImpl.getScoreTeams()[GameServiceImpl.getBatting()];
    }

    public static String diffProvider(int winningTeam) {
        String diffReveler = "";
        if (GameServiceImpl
                .getFlagForTeamWinningIndicationOnSecondInnings().equals("Current Team Wins")) {
            diffReveler = String.format(
                    "Team %d won by %d wickets"
                    , winningTeam, winningWicketsDifference()
            );
        }
        else if (GameServiceImpl
                .getFlagForTeamWinningIndicationOnSecondInnings().equals("Current Team Loses")){
            diffReveler = String.format(
                    "Team %d won by %d runs"
                   , winningTeam, winningRunsDifference()
            );
        }
        return diffReveler;
    }


    public byte checkWinningStatusNumber() {
        if ((OverService.getOverCount() == GameServiceImpl.getTotalOvers()) || (GameServiceImpl.getFlagForTeamWinningIndicationOnSecondInnings().equals("Game Over"))) {
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
            System.out.println("The Game is over :)"+ "\n" +"Team 1 Wins");
            System.out.println(GameServiceImpl.getFlagForTeamWinningIndicationOnSecondInnings());
            System.out.println(diffProvider(1));
        } else if (checkWinningStatusNumber() == Constants.SECOND_TEAM_WINNING_INDICATION) {
            System.out.println("The Game is over :)"+ "\n" +"Team 2 Wins");
            System.out.println(diffProvider(2));
            System.out.println(GameServiceImpl.getFlagForTeamWinningIndicationOnSecondInnings());
        }
        else {
            System.out.println("The Game is over :)" + "\n" +"Game is drawn");
        }
    }
}