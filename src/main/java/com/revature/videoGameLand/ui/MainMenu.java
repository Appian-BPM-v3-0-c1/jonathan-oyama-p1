package com.revature.videoGameLand.ui;

import java.util.Scanner;

public class MainMenu implements IMenu {
    @Override
    public void start() {
        char input = ' ';
        boolean exit = false;
        Scanner scan = new Scanner(System.in);

        while (!exit) {
            System.out.println("\nWelcome to Jon's VideoGameLand!");
            System.out.println("[1] View all Nintendo Switch video games");
            System.out.println("[2] Leave a review");
            System.out.println("[x] Exit");

            System.out.print("\nEnter: ");
            input = scan.next().charAt(0);
            switch (input) {
                case '1':
                    new NintendoSwitchMenu().start();
                    break;
                case '2':
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
}
