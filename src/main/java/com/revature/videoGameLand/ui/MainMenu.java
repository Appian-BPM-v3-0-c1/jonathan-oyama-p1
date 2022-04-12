package com.revature.videoGameLand.ui;
import com.revature.videoGameLand.models.Customer;

import java.util.Scanner;

public class MainMenu implements IMenu {
    private final Customer customer;

    public MainMenu(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void start() {
        /* get user input */
        char input = ' ';

        /* exit flag */
        boolean exit = false;

        /* to get user input */
        Scanner scan = new Scanner(System.in);

        /* while exit is not true */
        while (!exit) {
            System.out.println("\nWelcome to Jon's VideoGameLand!");
            System.out.println("[1] Go to video games menu");
            System.out.println("[2] Go to dept admin menu");
            System.out.println("[x] Exit");

            System.out.print("\nEnter: ");
            input = scan.next().charAt(0);

            switch (input) {
                case '1':
                    new VideoGameMenu().start();
                    break;
                case '2':
                    if (!customer.isAdmin()) {
                        System.out.println("Cannot enter dept admin menu.");
                        System.out.println("User is not a department store database administrator!");
                    }
                    else {
                        System.out.println("Entering dept admin menu...");
                        System.out.println("Exiting dept admin menu...");
                    }
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
