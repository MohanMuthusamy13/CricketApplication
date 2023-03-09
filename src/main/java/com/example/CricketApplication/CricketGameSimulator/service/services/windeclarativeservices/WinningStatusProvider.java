package com.example.CricketApplication.CricketGameSimulator.service.services.windeclarativeservices;

import com.example.CricketApplication.CricketGameSimulator.service.services.scoreservice.ScoreModel;
import com.example.CricketApplication.CricketGameSimulator.service.services.majorgameservice.GameServiceImpl;
import com.example.CricketApplication.CricketGameSimulator.service.services.overservice.OverService;

public class WinningStatusProvider {

    ScoreModel score = new ScoreModel();

    public byte checkWinningTeamCondition() {
        if (score.getScoreOfBothTeams()[0] > score.getScoreOfBothTeams()[1]) {
            return 1;
        } else if (score.getScoreOfBothTeams()[0] < score.getScoreOfBothTeams()[1]){
            return 2;
        }
        return 3;
    }


    public String checkWinningStatusForSecondInnings() {
        if ((GameServiceImpl.getInnings() == 2)
                && ((GameServiceImpl.getScoreTeams()[GameServiceImpl.getBatting()] >
                GameServiceImpl.getScoreTeams()[Math.abs(1 - GameServiceImpl.getBatting())]))){
            return "Current Team Wins";
        }
        else if (WicketStatusProvider.isWicketFlag()) {
            return "Current Team Loses";
        }
        return "";
    }

    public static int winningWicketsDifference() {
        return 10 - WicketStatusProvider.getWicketLose();
    }

    public static int winningRunsDifference() {
        return GameServiceImpl.getScoreTeams()[Math.abs(1 - GameServiceImpl.getBatting())] -
                GameServiceImpl.getScoreTeams()[GameServiceImpl.getBatting()];
    }

    public static String diffProvider(int winningTeam) {
        String diffReveler = "";
        if (GameServiceImpl.getFlagForTeamWinningIndicationOnSecondInnings().equals("Current Team Wins")) {
            diffReveler = String.format("Team %d won by %d wickets"
                    , winningTeam, winningWicketsDifference());
        }
        else if (GameServiceImpl.getFlagForTeamWinningIndicationOnSecondInnings().equals("Current Team Loses")){
            diffReveler = String.format("Team %d won by %d runs"
                   , winningTeam, winningRunsDifference());
        }
        return diffReveler;
    }


    public byte checkWinningStatusNumber() {
        // IF OVERS GOT COMPLETED
        if ((OverService.getOverCount() == GameServiceImpl.getTotalOvers()) || (GameServiceImpl.getFlagForTeamWinningIndicationOnSecondInnings().equals("Game Over"))) {
            if (checkWinningTeamCondition() == 1) {
                return 1;
            }
            else if (checkWinningTeamCondition() == 2){
                return 2;
            }
        }
        return 3;
    }

    public void checkWinningStatus() {
        if (checkWinningStatusNumber() == 1) {
            System.out.println("The Game is over :)"+ "\n" +"Team 1 Wins");
            System.out.println(GameServiceImpl.getFlagForTeamWinningIndicationOnSecondInnings());
            System.out.println(diffProvider(1));
        } else if (checkWinningStatusNumber() == 2) {
            System.out.println("The Game is over :)"+ "\n" +"Team 2 Wins");
            System.out.println(diffProvider(2));
            System.out.println(GameServiceImpl.getFlagForTeamWinningIndicationOnSecondInnings());

        }
        else {
            System.out.println("The Game is over :)" + "\n" +"Game is drawn");
        }
    }
}