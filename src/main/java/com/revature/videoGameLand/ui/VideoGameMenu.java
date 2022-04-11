package com.revature.videoGameLand.ui;

import com.revature.videoGameLand.daos.CrudDAO;
import com.revature.videoGameLand.daos.VideoGameDAO;
import com.revature.videoGameLand.models.VideoGame;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class VideoGameMenu implements IMenu {
    CrudDAO<VideoGame> crudDAO = new VideoGameDAO();
    @Override
    public void start() {
        char input = ' ';
        boolean exit = false;
        Scanner scan = new Scanner(System.in);

        while (!exit) {
            System.out.println("\nWelcome to Video Game Section!");
            System.out.println("[1] View all video games");
            System.out.println("[2] Search for games");
            System.out.println("[3] Create new video game");
            System.out.println("[x] Exit");

            System.out.print("\nEnter: ");
            input = scan.next().charAt(0);
            switch (input) {
                case '1':
                    viewAllVideoGames();
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
        boolean confirm = false;
        Scanner scan = new Scanner(System.in);
        VideoGame game = new VideoGame();

        while (!exit) {
            System.out.print("\nEnter in name of new game: ");
            game.setGameName(scan.nextLine().toLowerCase());
            scan = new Scanner(System.in);

            System.out.print("\nEnter in amount of game in stock: ");
            game.setStock(scan.nextInt());
            scan.nextLine();

            System.out.print("\nEnter in price of new game: ");
            game.setGamePrice(scan.nextFloat());
            scan.nextLine();

            System.out.println("\nEnter in console version of the game: ");
            game.setConsoleVersion(scan.nextLine().toLowerCase());

            System.out.println("\nEnter in ID number of the store holding the game: ");
            game.setGame_dept_id(scan.nextInt());

            while (!confirm) {
                System.out.println("\nIs this correct? (y/n)");
                System.out.println(game);

                input = scan.next().charAt(0);
                switch (input) {
                    case 'y':
                        crudDAO.save(game);
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
        List<VideoGame> videoGameList = crudDAO.findAll();
        System.out.println();
        if (videoGameList.isEmpty()) {
            System.out.println("No video games in database.");
        }
        else {
            while (true) {
                for (int i = 0; i < videoGameList.size(); i++) {
                    System.out.println("\n[" + i + "] " + videoGameList.get(i).getGameName());
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

            //System.out.println(videoGameList.get(input));

            /*for (VideoGame game : videoGameList) {
                System.out.println(game);
            }
            */
        }
    }
    /*
    private void listFiveVideoGames() {
        int count = 5;
        List<VideoGame> videoGameList = crudDAO.findAll();

        for (int i = 0; i < videoGameList.size(); i++) {
            if (i < count) {
                System.out.println(videoGameList.get(i));
            }
        }
    }
    */
}
