package main;

import match.Match;
import model.Team;
import util.JdbcUtil;

import java.util.Scanner;
public class Main {

    public static void main(String args[]) {
        System.out.println("Game starting...");
        //System.out.println("Pick your first team: LGD IG VG Liquid");
        //System.out.println("Make your choice : ");
        Team teamBlue = new Team("LGD");
        Team teamRed = new Team("Liquid");
        Match match = new Match(teamBlue, teamRed);
        match.start();
    }
}
