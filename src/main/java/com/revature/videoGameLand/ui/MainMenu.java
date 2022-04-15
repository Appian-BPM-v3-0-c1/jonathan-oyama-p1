package com.revature.videoGameLand.ui;

import com.revature.videoGameLand.models.Customer;
import com.revature.videoGameLand.models.ScInventory;
import com.revature.videoGameLand.models.ShoppingCart;
import com.revature.videoGameLand.services.*;

import java.util.Scanner;

public class MainMenu implements IMenu {
    private final Customer customer;
    private final CustomerService customerService;
    private final ShoppingCartService shoppingCartService;
    private final ScInventoryService scInventoryService;
    private final OrderHistoryService orderHistoryService;
    private final OInventoryService oInventoryService;
    private final VideoGameService videoGameService;

    public MainMenu(Customer customer, CustomerService customerService,
                    ShoppingCartService shoppingCartService,
                    ScInventoryService scInventoryService,
                    OrderHistoryService orderHistoryService,
                    OInventoryService oInventoryService,
                    VideoGameService videoGameService) {
        this.customer = customer;
        this.customerService = customerService;
        this.shoppingCartService = shoppingCartService;
        this.scInventoryService = scInventoryService;
        this.orderHistoryService = orderHistoryService;
        this.oInventoryService = oInventoryService;
        this.videoGameService = videoGameService;

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
                    ShoppingCart shoppingCart = new ShoppingCart();
                    ScInventory scInventory = new ScInventory();
                    String passedId = Integer.toString(customer.getId());
                    shoppingCart = shoppingCartService.getShoppingCartDAO().findById(passedId);
                    scInventory = scInventoryService.getScInventoryDAO().findById(passedId);
                    new VideoGameMenu(shoppingCartService, scInventoryService,
                            orderHistoryService, oInventoryService,
                            videoGameService, customer, shoppingCart).start();
                    break;
                case '2':
                    if (admin) {
                        System.out.println(customerService.getCustomerDAO().findAll());
                    } else {
                        System.out.println("\nAccess denied.");
                        System.out.println("\nOnly managers can check user information  "
                                + "\nin the database!");
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
