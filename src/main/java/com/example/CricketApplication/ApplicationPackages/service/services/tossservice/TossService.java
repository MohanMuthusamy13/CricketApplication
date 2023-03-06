package com.example.CricketApplication.ApplicationPackages.service.services.tossservice;


import com.example.CricketApplication.ApplicationPackages.entities.enums.Toss;
import com.example.CricketApplication.ApplicationPackages.service.services.majorgameservice.GameServiceImpl;
import com.example.CricketApplication.ApplicationPackages.view.TossDisplay;

public class TossService {

    private TossService() {}

    public static int getTossResult(int minimum, int maximum) {
        return (int)Math.floor(Math.random() * (maximum - minimum + 1) + minimum);
    }

    public static void startTossing() {

        Toss[] tossPossibilities = new Toss[] {Toss.HEAD, Toss.TAIL};
        Toss tossWinner = tossPossibilities[getTossResult(0, 1)];
        int teamSelected = tossWinner.getBattingOrderIndicator();
        TossDisplay.tossDisplayed(teamSelected);
        if (teamSelected == 0) {
            GameServiceImpl.setBatting(1);
            GameServiceImpl.setBowling(0);
        }
        else {
            GameServiceImpl.setBatting(0);
            GameServiceImpl.setBowling(1);
        }
    }
}