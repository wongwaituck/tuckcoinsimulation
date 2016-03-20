package com.smu.view;

import com.smu.StateStorage;
import com.smu.model.Challenge;
import com.smu.model.ChallengeFactory;
import com.smu.network.GoOnlineHTTPRequest;
import com.smu.network.Poller;
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
        printName();
        initialize();
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

    private static void initialize() {
        StateStorage ss = StateStorage.getInstance();
        Poller.startPollingBlocks();
    }

    public static void enterName(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        StateStorage.getInstance().setWallet(name);
        new Thread(new GoOnlineHTTPRequest(StateStorage.getInstance().getWallet())).run();
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

    public static void printName(){
        StateStorage ss = StateStorage.getInstance();
        System.out.printf("Welcome %s!\n", ss.getName());
    }

    public static void getOptions(){
        StateStorage ss = StateStorage.getInstance();
        System.out.println("Select the following options: ");
        if(!ss.isMining()) {
            System.out.println("1. Enable Mining ");
        } else {
            System.out.println("1. Disable Mining ");
        }
        if(!ss.isEvilMode()) {
            System.out.println("2. Activate Moriarty Mode ");
        } else {
            System.out.println("2. Disable Moriarty Mode ");
        }
        System.out.println("3. Quit ");
        int option = readOptions();

        switch(option){
            case 1:
                ss.toggleMining();
                if(ss.isMining()) {
                    Poller.startPollingTransactions();
                } else {
                    Poller.stopPollingTransactions();
                }
                break;
            case 2:
                ss.toggleEvilMode();
                break;
            case 3:
                ss.quit();
                break;
            default:
                break;
        }
        getOptions();
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


    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
