package com.example.CricketApplication.service.services.tossservice;


import com.example.CricketApplication.enums.Toss;
import com.example.CricketApplication.service.services.majorgameservice.GameServiceImpl;
import com.example.CricketApplication.utils.Constants;
import com.example.CricketApplication.view.TossDisplay;

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
            GameServiceImpl.setBatting(Constants.SECOND_TEAM);
            GameServiceImpl.setBowling(Constants.FIRST_TEAM);
        }
        else {
            GameServiceImpl.setBatting(Constants.FIRST_TEAM);
            GameServiceImpl.setBowling(Constants.SECOND_TEAM);
        }
    }
}