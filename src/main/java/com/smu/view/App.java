package com.smu.view;

import com.smu.StateStorage;
import com.smu.model.Challenge;
import com.smu.model.ChallengeFactory;
import com.smu.network.*;
import com.sun.corba.se.spi.orbutil.fsm.Input;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by WaiTuck on 06/02/2016.
 */
public class App {
    public static void main(String[] args) throws Exception{
        if(args.length > 0 && args[0] != null){
            HTTPRequest.BASE_URL = args[0];
            if(args.length > 1 && args[1] != null){
                StateStorage.adminMode = true;
            }
        }
        clearScreen();
        showSplashScreen();
        enterName();
        printName();
        initialize();
        getOptions();
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
        System.out.println("Welcome to BitCoin!");
    }

    public static void printName(){
        StateStorage ss = StateStorage.getInstance();
        System.out.printf("Welcome %s!\n", ss.getName());
    }

    public static void getOptions(){
        StateStorage ss = StateStorage.getInstance();
        printOptions();
        int option = readOptions();
        clearScreen();
        switch(option){
            case 1:
                ss.toggleMining();
                if(ss.isMining()) {
                    System.out.println("Work it hard, mining started!");
                    Poller.startPollingTransactions();
                } else {
                    System.out.println("Taking a break? Mining stopped!");
                    Poller.stopPollingTransactions();
                }
                break;
            case 2:
                ss.toggleEvilMode();
                if(ss.isEvilMode()) {
                    new Thread(new GoEvilHTTPRequest(ss.getWallet()));
                    System.out.println("Hehehehe, Moriarty is here >:)");
                } else {
                    new Thread(new StopEvilHTTPRequest(ss.getWallet()));
                    System.out.println("No more Moriarty :(");
                }
                break;
            case 3:
                ss.quit();
                break;
            default:
                break;
        }
        getOptions();
    }

    public static void printOptions(){
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
    }

    public static int readOptions(){
        //reprompt until receive int
        int options = -1;
        Scanner sc = new Scanner(System.in);

        while(options < 1 || options > 3) {
            try{
                options = sc.nextInt();
            } catch(InputMismatchException e){
                clearScreen();
                System.out.println("Invalid option, try again!");
                printOptions();
                sc.nextLine();
            }
        }

        return options;
    }


    public static void clearScreen(){
        for(int i = 0; i < 60; i++){
            System.out.println("");
        }
    }
}
