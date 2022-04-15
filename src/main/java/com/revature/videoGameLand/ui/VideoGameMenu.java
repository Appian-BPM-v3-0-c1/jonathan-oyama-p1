package com.revature.videoGameLand.ui;

import com.revature.videoGameLand.models.*;
import com.revature.videoGameLand.models.VideoGame;
import com.revature.videoGameLand.services.*;

import java.util.List;
import java.util.Scanner;

public class VideoGameMenu implements IMenu {
    private final ShoppingCartService shoppingCartService;
    private final ScInventoryService scInventoryService;
    private final OrderHistoryService orderHistoryService;
    private final OInventoryService oInventoryService;
    private final VideoGameService videoGameService;
    private final Customer customer;
    // Code to implement shopping cart
    private final ShoppingCart shoppingCart;


    public VideoGameMenu(ShoppingCartService shoppingCartService,
                         ScInventoryService scInventoryService,
                         OrderHistoryService orderHistoryService,
                         OInventoryService oInventoryService,
                         VideoGameService videoGameService,
                         Customer customer, ShoppingCart shoppingCart) {
        this.shoppingCartService = shoppingCartService;
        this.scInventoryService = scInventoryService;
        this.orderHistoryService = orderHistoryService;
        this.oInventoryService = oInventoryService;
        this.videoGameService = videoGameService;
        this.customer = customer;
        this.shoppingCart = shoppingCart;
    }

    @Override
    public void start() {
        char input = ' ';
        boolean exit = false;
        Scanner scan = new Scanner(System.in);

        while (!exit) {
            System.out.println("\nWelcome to Video Game Section!");
            System.out.println("[1] View all video games");
            if (customer.isManager()) {
                System.out.println("[2] Create new video game");
            }
            System.out.println("[x] Exit");

            System.out.print("\nEnter: ");
            input = scan.next().charAt(0);
            switch (input) {
                case '1':
                    viewAllVideoGames();
                    break;
                case '2':
                    if (customer.isManager()) {
                        createGame();
                    } else {
                        System.out.println("\nAccess denied.");
                        System.out.println("\nOnly managers can add video games "
                            + "to the database!");
                    }
                    break;
                case '3':

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
    private void createGame() {
        char input = ' ';
        boolean exit = false;
        boolean confirm = false;
        Scanner scan = new Scanner(System.in);
        VideoGame game = new VideoGame();

        while (!exit) {
            System.out.print("\nEnter in name of new game: ");
            game.setName(scan.nextLine().toLowerCase());
            scan = new Scanner(System.in);

            System.out.print("\nEnter in amount of game in stock: ");
            game.setStock(scan.nextInt());
            scan.nextLine();

            System.out.print("\nEnter in price of new game: ");
            game.setPrice(scan.nextFloat());
            scan.nextLine();

            System.out.println("\nEnter in console version of the game: ");
            game.setConsoleVersion(scan.nextLine().toLowerCase());
            scan = new Scanner(System.in);

            //System.out.println("\nEnter in ID number of the store holding the game: ");
            //game.setDept_id(scan.nextInt());
            game.setDept_id(1);

            while (!confirm) {
                System.out.println("\nIs this correct? (y/n)");
                System.out.println(game);

                input = scan.next().charAt(0);
                switch (input) {
                    case 'y':
                        videoGameService.getVideoGameDAO().save(game);
                        System.out.println("Video game created successfully!");
                        exit = true;
                        confirm = true;
                        break;
                    case 'n':
                        confirm = true;
                        break;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }

            }

        }
    }

    private void viewAllVideoGames() {
        int input = 0;
        Scanner scan = new Scanner(System.in);
        List<VideoGame> videoGameList = videoGameService.getVideoGameDAO().findAll();
        System.out.println();
        if (videoGameList.isEmpty()) {
            System.out.println("No video games in database.");
        }
        else {
            while (true) {
                for (int i = 0; i < videoGameList.size(); i++) {
                    System.out.println("\n[" + i + "] " + videoGameList.get(i).getName());
                }
                System.out.println("\nSelect a video game to view amount in stock");
                input = scan.nextInt();
                if (input >= videoGameList.size()) {
                    System.out.println("Invalid input.");
                } else {
                    System.out.println(videoGameList.get(input));
                    break;
                }
            }
        }
    }
    private void viewShoppingCart() {

    }
}
