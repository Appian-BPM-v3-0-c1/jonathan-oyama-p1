package com.revature.videoGameLand.ui;

import com.revature.videoGameLand.daos.CrudDAO;
import com.revature.videoGameLand.daos.UserDAO;
import com.revature.videoGameLand.models.User;

import java.util.List;
import java.util.Scanner;

public class MainMenu implements IMenu {
    CrudDAO<User> crudDAO = new UserDAO();
    @Override
    public void start() {
        char input = ' ';
        boolean exit = false;
        Scanner scan = new Scanner(System.in);
        //new UserMenu();

        while (!exit) {
            System.out.println("\nWelcome to Jon's VideoGameLand!");
            // Implement code below after UserDAO/User menu is finished
            /*if (firstTimeCheck()) {
                input = 2;
            }
            else {*/
                System.out.println("[1] Go to video games menu");
                System.out.println("[2] Go to user profile menu");
                System.out.println("[x] Exit");

                System.out.print("\nEnter: ");
                input = scan.next().charAt(0);
            // Implement code below after UserDAO/User menu is finished
            //}

            switch (input) {
                case '1':
                    new VideoGameMenu().start();
                    break;
                case '2':
                    new UserMenu().start();
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

    private boolean firstTimeCheck() {
        int input = 0;
        List<User> userList = crudDAO.findAll();
        System.out.println();
        if (userList.isEmpty()) {
            return true;
        }
        return false;
    }

}
