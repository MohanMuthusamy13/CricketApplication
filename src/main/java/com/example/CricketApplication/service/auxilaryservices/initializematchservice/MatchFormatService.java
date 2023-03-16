package com.example.CricketApplication.service.auxilaryservices.initializematchservice;

import com.example.CricketApplication.enums.MatchFormat;
import com.example.CricketApplication.service.auxilaryservices.majorgameservice.GameServiceImpl;
import lombok.Getter;
import lombok.Setter;

public class MatchFormatService {

    @Getter @Setter
    private static String plannedMatchFormat;

    private static final MatchFormat[] matchFormatSpecifier =
            new MatchFormat[]{MatchFormat.T20, MatchFormat.ODI, MatchFormat.TEST};

    public static int oversScheduler(String matchFormat) {
        switch (matchFormat) {
            case "T20" :
                System.out.println("\nSCHEDULED : T20 MATCH\n");
                return matchFormatSpecifier[0].getOvers();
            case "ODI" :
                System.out.println("\nSCHEDULED : ODI MATCH\n");
                return matchFormatSpecifier[1].getOvers();
            case "TEST" :
                System.out.println("\nSCHEDULED : TEST MATCH\n");
                return matchFormatSpecifier[2].getOvers();
            default :
                throw new RuntimeException("Unknown match format: " + matchFormat);
        }
    }

    public static String matchFormatScheduler(String matchFormat) {
        plannedMatchFormat = matchFormat.toUpperCase();
        GameServiceImpl.setTotalOvers(MatchFormatService.oversScheduler(plannedMatchFormat));
        return plannedMatchFormat;
    }

}