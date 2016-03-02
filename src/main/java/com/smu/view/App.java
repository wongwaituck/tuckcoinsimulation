package com.smu.view;

import com.smu.StateStorage;
import com.smu.model.Challenge;
import com.smu.model.ChallengeFactory;
import com.sun.corba.se.spi.orbutil.fsm.Input;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by WaiTuck on 06/02/2016.
 */
public class App {
    public static void main(String[] args) throws Exception{
        clearScreen();
        showSplashScreen();
        enterName();
        getOptions();
        /*
        System.out.println("Running 10 sha-256 challenges");
        for(int i = 20; i < 30; i++) {
            Challenge c = ChallengeFactory.getChallenge("SHA-256", 15);
            new Thread(c).run();
        }

        System.out.println("Running 10 argon2 challenges");
        for(int i = 0; i < 10; i++) {
            Challenge c = ChallengeFactory.getChallenge("Argon2", 5);
            new Thread(c).run();
        }
        */

    }

    public static void enterName(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        StateStorage.getInstance().setWallet(name);
    }


    public static void showSplashScreen(){
        System.out.println(
                "░░░░░░░░░▄░░░░░░░░░░░░░░▄░░░░\n" +
                        "░░░░░░░░▌▒█░░░░░░░░░░░▄▀▒▌░░░\n" +
                        "░░░░░░░░▌▒▒█░░░░░░░░▄▀▒▒▒▐░░░\n" +
                        "░░░░░░░▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐░░░\n" +
                        "░░░░░▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐░░░\n" +
                        "░░░▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌░░░ \n" +
                        "░░▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒▌░░\n" +
                        "░░▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐░░\n" +
                        "░▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄▌░\n" +
                        "░▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒▌░\n" +
                        "▀▒▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒▐░\n" +
                        "▐▒▒▐▀▐▀▒░▄▄▒▄▒▒▒▒▒▒░▒░▒░▒▒▒▒▌\n" +
                        "▐▒▒▒▀▀▄▄▒▒▒▄▒▒▒▒▒▒▒▒░▒░▒░▒▒▐░\n" +
                        "░▌▒▒▒▒▒▒▀▀▀▒▒▒▒▒▒░▒░▒░▒░▒▒▒▌░\n" +
                        "░▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▒▄▒▒▐░░\n" +
                        "░░▀▄▒▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▄▒▒▒▒▌░░\n" +
                        "░░░░▀▄▒▒▒▒▒▒▒▒▒▒▄▄▄▀▒▒▒▒▄▀░░░\n" +
                        "░░░░░░▀▄▄▄▄▄▄▀▀▀▒▒▒▒▒▄▄▀░░░░░\n" +
                        "░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▀▀░░░░░░░░");
        System.out.println("Welcome to TuckCoin!");
    }
    public static void getOptions(){
        StateStorage ss = StateStorage.getInstance();
        System.out.printf("Welcome %s!\n", ss.getName());
        System.out.println("Select the following options: ");
        System.out.println("1. Enable Mining ");
        if(!ss.isEvilMode()) {
            System.out.println("2. Enable Evil Mode ");
        } else {
            System.out.println("2. Disable Evil Mode ");
        }
        int option = readOptions();

        switch(option){
            case 1:
                break;
            case 2:
                break;
            default:
                break;
        }
    }

    public static int readOptions(){
        //reprompt until receive int
        int options = -1;
        Scanner sc = new Scanner(System.in);

        while(options < 1) {
            try{
                options = sc.nextInt();
            } catch(InputMismatchException e){
                System.out.println("Invalid option, try again!");
                sc.nextLine();
            }
        }

        return options;
    }

    public static void startMiningThread(){

    }

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
