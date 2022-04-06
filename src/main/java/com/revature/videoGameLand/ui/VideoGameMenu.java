package com.revature.videoGameLand.ui;

import com.revature.videoGameLand.models.VideoGame;

import java.util.Scanner;

public class VideoGameMenu implements IMenu {

    @Override
    public void start() {
        char input = ' ';
        boolean exit = false;
        Scanner scan = new Scanner(System.in);

        while (!exit) {
            System.out.println("\nWelcome to Video Game Section!");
            System.out.println("[1] List all Nintendo games");
            System.out.println("[2] Search for games");
            System.out.println("[3] Create new video game");
            System.out.println("[x] Exit");

            System.out.print("\nEnter: ");
            input = scan.next().charAt(0);
            switch (input) {
                case '1':
                    break;
                case '2':
                    break;
                case '3':
                    createGame();
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
        Scanner scan = new Scanner(System.in);
        VideoGame game = new VideoGame();

        while (!exit) {
            System.out.println("\nCreating a new game...");
            System.out.print("Enter in new game id: ");
            game.setId(scan.nextInt());
            scan = new Scanner(System.in);

            System.out.print("\nEnter in name of new game: ");
            game.setGameName(scan.nextLine());
            scan = new Scanner(System.in);

            System.out.print("\nEnter in amount of game in stock: ");
            game.setStock(scan.nextInt());
            scan = new Scanner(System.in);

            System.out.print("\nEnter in price of new game: ");
            game.setGamePrice(scan.nextFloat());
            scan = new Scanner(System.in);

            while (true) {
                System.out.println("\nIs this correct? (y/n)");
                System.out.println(game);

                input = scan.next().charAt(0);
                switch (input) {
                    case 'y':
                        game.setGame_dept_id(1);
                        break;
                    case 'n':
                        break;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }

            }

        }
    }
}
