package com.revature.videoGameLand.ui;

import java.util.Scanner;

public class NintendoSwitchMenu implements IMenu {

    @Override
    public void start() {
        char input = ' ';
        boolean exit = false;
        Scanner scan = new Scanner(System.in);

        while (!exit) {
            System.out.println("\nWelcome to Nintendo Switch Store!");
            System.out.println("[1] List all Switch games");
            System.out.println("[2] Search for games");
            System.out.println("[3] Create new Nintendo game");
            System.out.println("[x] Exit");

            System.out.print("\nEnter: ");
            input = scan.next().charAt(0);
            switch (input) {
                case '1':
                    break;
                case '2':
                    break;
                case '3':
                    createNintendoGame();
                    break;
                case 'x':
                    exit = true;
                    break;
                default:
                    System.out.println("\nInvalid input!");
                    break;
            }
        }

    }
    private void createNintendoGame() {
        System.out.println("\nCreating a Nintendo game...");
    }
}
