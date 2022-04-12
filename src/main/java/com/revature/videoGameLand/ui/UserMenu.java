package com.revature.videoGameLand.ui;

import com.revature.videoGameLand.daos.CrudDAO;
import com.revature.videoGameLand.daos.UserDAO;
import com.revature.videoGameLand.models.User;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.*;

public class UserMenu implements IMenu {
    CrudDAO<User> crudDAO = new UserDAO();
    @Override
    public void start() {
        char input = ' ';
        boolean exit = false;
        Scanner scan = new Scanner(System.in);
        while (!exit) {
            System.out.println("\nWelcome to the User Menu!");
            if (firstTimeCheck()) {
                input = '1';
            }
            else {
                System.out.println("[1] Create new user account");
                //System.out.println("[2] Change password");
                System.out.println("[x] Exit");

                System.out.println("\nEnter: ");
                input = scan.next().charAt(0);
            }
            switch (input) {
                case '1':
                    createUser();
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

    private void createUser() {
        char input = ' ';
        boolean exit = false;
        boolean validEntry = false;
        boolean confirm = false;
        String dataString = "";
        int dataInt = 0;
        Scanner scan = new Scanner(System.in);
        User user = new User();
        while (!exit) {
            if (firstTimeCheck()) {
                System.out.println("\nCreating new admin account...");
                user.setAdmin(true);
            }
            else {
                user.setAdmin(false);
            }
            while (true) {
                System.out.println("\nEnter in first name: ");
                dataString = scan.nextLine();
                scan = new Scanner(System.in);
                if (isValidName(dataString)) {
                        user.setFirstName(dataString);
                        break;
                }
                System.out.println("\nInvalid first name!");
            }
            while (true) {
                System.out.println("\nEnter in last name: ");
                dataString = scan.nextLine();
                scan = new Scanner(System.in);
                if (isValidName(dataString)) {
                    user.setLastName(dataString);
                    break;
                }
                System.out.println("\nInvalid last name!");
            }
            while (true) {
                System.out.println("\nEnter in e-mail address: ");
                dataString = scan.nextLine();
                scan = new Scanner(System.in);
                if (isValidEmail(dataString)) {
                    user.setEmail(dataString);
                    break;
                }
                System.out.println("\nInvalid e-mail address!");
            }
            while (true) {
                System.out.println("\nEnter in user name: ");
                dataString = scan.nextLine();
                scan = new Scanner(System.in);
                if (isValidName(dataString)) {
                    user.setUserName(dataString);
                    break;
                }
                System.out.println("\nInvalid user name!");
            }
            while (true) {
                System.out.println("\nEnter in user password: ");
                dataString = scan.nextLine();
                scan = new Scanner(System.in);
                if (isValidPassword(dataString)) {
                    user.setPassword(dataString);
                    break;
                }
                System.out.println("\nInvalid password!");
                System.out.println("\nPassword must contain at least one digit, "
                                + "one lower-case letter, "
                                + "one upper-case letter, and "
                                + "one special character.");
                System.out.println("\nPassword cannot contain white spaces.");
            }
            while (true) {
                System.out.println("\nEnter in house/P.O. Box number: ");
                dataInt = scan.nextInt();
                scan.nextLine();
                if (dataInt > 0) {
                    user.setHouseNumber(dataInt);
                    break;
                }
                System.out.println("\nInvalid house/P.O. Box number!");
            }
            while (true) {
                System.out.println("\nEnter in street name: ");
                dataString = scan.nextLine();
                scan = new Scanner(System.in);
                if ((dataString != null) && dataString.length() > 0) {
                    user.setStreetName(dataString);
                    break;
                }
                System.out.println("\nInvalid street name!");
            }
            while (true) {
                System.out.println("\nEnter in city name: ");
                dataString = scan.nextLine();
                scan = new Scanner(System.in);
                if ((dataString != null) && dataString.length() > 0) {
                    user.setCity(dataString);
                    break;
                }
                System.out.println("\nInvalid city name!");
            }
            while (true) {
                System.out.println("\nEnter in state abbreviation: ");
                dataString = scan.nextLine().toUpperCase();
                scan = new Scanner(System.in);
                if ((dataString != null) && (dataString.length() == 2)) {
                    user.setState(dataString);
                    break;
                }
                System.out.println("\nInvalid state abbreviation!");
            }
            while (true) {
                System.out.println("\nEnter in five-digit zip code: ");
                dataInt = scan.nextInt();
                scan.nextLine();
                if (dataInt > 9999 && dataInt < 100000) {
                    user.setZipCode(dataInt);
                    break;
                }
                System.out.println("\nInvalid zip code!");
            }
            while (!confirm) {
                System.out.println("\nIs this correct? (y/n)");
                System.out.println(user);

                input = scan.next().charAt(0);
                switch (input) {
                    case 'y':
                        crudDAO.save(user);
                        System.out.println("New user account created successfully!");
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

    private boolean firstTimeCheck() {
        int input = 0;
        List<User> userList = crudDAO.findAll();
        System.out.println();
        if (userList.isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean isValidName(String name)
    {
        String regex = "^[a-zA-Z]{2,}+$";
        Pattern p = Pattern.compile(regex);
        if (name == null) {
            return false;
        }
        Matcher m = p.matcher(name);

        return m.matches();
    }

    private boolean isValidEmail(String email)
    {
        String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern p = Pattern.compile(regex);
        if (email == null) {
            return false;
        }
        Matcher m = p.matcher(email);

        return m.matches();
    }
    private boolean isValidPassword(String password)
    {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
        Pattern p = Pattern.compile(regex);
        if (password == null) {
            return false;
        }
        Matcher m = p.matcher(password);

        return m.matches();
    }
    private boolean isValidStreet(String street)
    {
        String regex = "";
        Pattern p = Pattern.compile(regex);
        if (street == null) {
            return false;
        }
        Matcher m = p.matcher(street);
        return m.matches();
    }
}
