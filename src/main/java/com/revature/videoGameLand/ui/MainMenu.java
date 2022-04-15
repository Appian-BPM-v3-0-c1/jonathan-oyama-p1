package com.revature.videoGameLand.ui;

import com.revature.videoGameLand.models.Customer;
import com.revature.videoGameLand.services.CustomerService;

import java.util.Scanner;

public class MainMenu implements IMenu {
    private final Customer customer;
    private final CustomerService customerService;

    public MainMenu(Customer customer, CustomerService customerService) {
        this.customer = customer;
        this.customerService = customerService;
    }

    @Override
    public void start() {
        /* get user input */
        char input = ' ';

        /* exit flag */
        boolean exit = false;
        boolean admin = false;

        /* to get user input */
        Scanner scan = new Scanner(System.in);

        /* while exit is not true */
        while (!exit) {
            System.out.println("\nWelcome to Jon's VideoGameLand!");
            if (customerService.isValidAdmin(customer)) {
                admin = true;
            }
            System.out.println("[1] Go to video games menu");
            if (admin) {
                System.out.println("[2] Print all user names");
            }
            System.out.println("[x] Exit");

            System.out.print("\nEnter: ");
            input = scan.next().charAt(0);

            switch (input) {
                case '1':
                    new VideoGameMenu().start();
                    break;
                case '2':
                    System.out.println(customerService.getCustomerDAO().findAll());
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
