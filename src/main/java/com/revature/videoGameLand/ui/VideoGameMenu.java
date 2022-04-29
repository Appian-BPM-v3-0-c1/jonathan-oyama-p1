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
    private ShoppingCart shoppingCart;
    private ScInventory scInventory;
    private OInventory oInventory;
    private OrderHistory orderHistory;


    public VideoGameMenu(ShoppingCartService shoppingCartService,
                         ScInventoryService scInventoryService,
                         OrderHistoryService orderHistoryService,
                         OInventoryService oInventoryService,
                         VideoGameService videoGameService,
                         Customer customer, ShoppingCart shoppingCart,
                         ScInventory scInventory, OInventory oInventory,
                         OrderHistory orderHistory) {
        this.shoppingCartService = shoppingCartService;
        this.scInventoryService = scInventoryService;
        this.orderHistoryService = orderHistoryService;
        this.oInventoryService = oInventoryService;
        this.videoGameService = videoGameService;
        this.customer = customer;
        this.shoppingCart = shoppingCart;
        this.scInventory = scInventory;
        this.oInventory = oInventory;
        this.orderHistory = orderHistory;
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
            System.out.println("[3] View shopping cart");
            //System.out.println("[4] View order history");
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
                    viewShoppingCart();
                    break;
                //case '4':
                //    viewOrderHistory();
                //    break;
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
        boolean exit = false;
        Scanner scan = new Scanner(System.in);
        List<VideoGame> videoGameList = videoGameService.getVideoGameDAO().findAll();
        System.out.println();
        if (videoGameList.isEmpty()) {
            System.out.println("No video games in database.");
        }
        else {
            while (!exit) {
                for (int i = 0; i < videoGameList.size(); i++) {
                    System.out.println("\n[" + (i + 1) + "] " + videoGameList.get(i).getName());
                }
                System.out.println("\nSelect a video game to view amount in stock");
                System.out.println("\n[0] Exit");
                input = scan.nextInt();
                if (input == 0) {
                    exit = true;
                } else if (input >= videoGameList.size() + 1) {
                    System.out.println("Invalid input.");
                    exit = true;
                } else {
                    int itemIndex = input - 1;
                    System.out.println(videoGameList.get(itemIndex));
                    while (!exit) {
                        System.out.println("\nPurchase?");
                        System.out.println("[1] Yes");
                        System.out.println("[2] No");
                        input = scan.nextInt();
                        if (input == 1){
                            scInventory.setName(videoGameList.get(itemIndex).getName());
                            scInventory.setPrice(videoGameList.get(itemIndex).getPrice());
                            scInventory.setQuantity(1);
                            scInventoryService.getScInventoryDAO().update(scInventory);
                            shoppingCart.setTotal(shoppingCart.getTotal() + scInventory.getPrice());
                            System.out.println("Item in shopping cart!");
                            exit  = true;
                            break;
                        } else {
                            exit  = true;
                            break;
                        }
                    }
                }
            }
        }
    }

    private void viewOrderHistory() {
        orderHistory = orderHistoryService.getOrderHistoryDAO().findById(Integer.toString(customer.getId()));
        oInventory = oInventoryService.getOInventoryDAO().findById(Integer.toString(customer.getId()));
        if (oInventory.getQuantity() == 0) {
            System.out.println("\nShopping cart is empty");
        } else {
            System.out.println(orderHistory);
            System.out.println(oInventory);
        }
    }
    private void viewShoppingCart() {
        Scanner scan = new Scanner(System.in);
        char input = ' ';
        scInventory = scInventoryService.getScInventoryDAO().findById(Integer.toString(customer.getId()));
        //orderHistory = orderHistoryService.getOrderHistoryDAO().findById(Integer.toString(customer.getId()));
        //oInventory = oInventoryService.getOInventoryDAO().findById(Integer.toString(customer.getId()));
        if (scInventory.getQuantity() == 0) {
            System.out.println("\nShopping cart is empty");
        } else {
            System.out.println(shoppingCart);
            System.out.println(scInventory);
            System.out.println("\nPurchase?");
            System.out.println("[y]");
            System.out.println("[n]");
            System.out.println("Enter: ");
            input = scan.next().charAt(0);
            switch (input) {
                case 'y':
                    //oInventory.setPrice(scInventory.getPrice());
                    //oInventory.setName(scInventory.getName());
                    //oInventory.setQuantity(scInventory.getQuantity());
                    //orderHistory.setTotal(orderHistory.getTotal() + oInventory.getPrice());
                    scInventory.setPrice(0);
                    scInventory.setName("empty");
                    scInventory.setQuantity(scInventory.getQuantity() -1);
                    System.out.println("\nItem purchased!");
                    scInventoryService.getScInventoryDAO().update(scInventory);
                    break;
                case 'n':
                    break;
                default:
                    System.out.println("\nInvalid input.");
                    break;

            }
        }
    }
}
