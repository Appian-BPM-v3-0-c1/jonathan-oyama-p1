package com.revature.videoGameLand.ui;
import com.revature.videoGameLand.models.Customer;
import com.revature.videoGameLand.services.CustomerService;

import java.util.Scanner;

public class LoginMenu implements IMenu {
    private CustomerService customerService;

    public LoginMenu(CustomerService customerService) {
        this.customerService = customerService;
    }

    Scanner scan = new Scanner(System.in);
    Customer customer = new Customer();

    @Override
    public void start() {
        char input = ' ';
        boolean exit = false;
        exit: {
            while (true) {
                System.out.println("\nWelcome to the Login Menu!");
                if (customerService.firstTimeCheck()) {
                    input = '2';
                }
                else {
                    System.out.println("[1] Log in");
                    System.out.println("[2] Create new user account");
                    System.out.println("[x] Exit");

                    System.out.println("\nEnter: ");
                    input = scan.next().charAt(0);
                }
                switch (input) {
                    case '1':
                        login();
                        break;
                    case '2':
                        createAccount();
                        break;
                    case 'x':
                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }
    }

    private void createAccount() {
        char input = ' ';
        boolean exit = false;
        boolean confirm = false;
        String dataString = "";
        String username = "";
        String password1 = "";
        String password2 = "";
        int dataInt = 0;
        Scanner scan = new Scanner(System.in);
        Customer customer = new Customer();

        System.out.println("\nCreating account...");

        while (!exit) {
            if (customerService.firstTimeCheck()) {
                System.out.println("\nCreating new admin account...");
                customer.setAdmin(true);
            }
            else {
                customer.setAdmin(false);
            }
            while (true) {
                System.out.println("\nEnter in first name: ");
                dataString = scan.nextLine();
                scan = new Scanner(System.in);
                if (customerService.isValidName(dataString)) {
                    customer.setFirstName(dataString);
                    break;
                }
                System.out.println("\nInvalid first name!");
            }
            while (true) {
                System.out.println("\nEnter in last name: ");
                dataString = scan.nextLine();
                scan = new Scanner(System.in);
                if (customerService.isValidName(dataString)) {
                    customer.setLastName(dataString);
                    break;
                }
                System.out.println("\nInvalid last name!");
            }
            while (true) {
                System.out.println("\nEnter in e-mail address: ");
                dataString = scan.nextLine();
                scan = new Scanner(System.in);
                if (customerService.isValidEmail(dataString)) {
                    customer.setEmail(dataString);
                    break;
                }
                System.out.println("\nInvalid e-mail address!");
            }
            while (true) {
                System.out.println("\nEnter in username: ");
                username = scan.next();

                if (!customerService.isDupUsername(username)) {
                    if (customerService.isValidUsername(username)) {
                        customer.setUserName(username);
                        break;
                    } else {
                        System.out.println("\nInvalid username :(");
                        System.out.println("\nUsername must contain at least eight characters");
                    }
                } else {
                    System.out.println("\nDuplicate username :(");
                }
            }
            while (true) {
                System.out.println("\nEnter in password: ");
                password1 = scan.next();
                System.out.println("\nEnter in password again: ");
                password2 = scan.next();

                if (password1.equals(password2)) {
                    if (customerService.isValidPassword(password1)) {
                        customer.setPassword(password1);
                        break;
                    } else {
                        System.out.println("\nInvalid password!");
                        System.out.println("\nPassword must contain at least one digit, "
                                + "one lower-case letter, "
                                + "one upper-case letter, and "
                                + "one special character.");
                        System.out.println("\nPassword cannot contain white spaces.");
                    }
                } else {
                    System.out.println("Password does not match!");
                }
            }
            while (true) {
                System.out.println("\nEnter in house/P.O. Box number: ");
                dataInt = scan.nextInt();
                scan.nextLine();
                if (dataInt > 0) {
                    customer.setHouseNumber(dataInt);
                    break;
                }
                System.out.println("\nInvalid house/P.O. Box number!");
            }
            while (true) {
                System.out.println("\nEnter in street name: ");
                dataString = scan.nextLine();
                scan = new Scanner(System.in);
                if ((dataString != null) && dataString.length() > 2) {
                    customer.setStreetName(dataString);
                    break;
                }
                System.out.println("\nInvalid street name!");
            }
            while (true) {
                System.out.println("\nEnter in city name: ");
                dataString = scan.nextLine();
                scan = new Scanner(System.in);
                if ((dataString != null) && dataString.length() > 2) {
                    customer.setCity(dataString);
                    break;
                }
                System.out.println("\nInvalid city name!");
            }
            while (true) {
                System.out.println("\nEnter in state abbreviation: ");
                dataString = scan.nextLine().toUpperCase();
                scan = new Scanner(System.in);
                if ((dataString != null) && (dataString.length() == 2)) {
                    customer.setState(dataString);
                    break;
                }
                System.out.println("\nInvalid state abbreviation!");
            }
            while (true) {
                System.out.println("\nEnter in five-digit zip code: ");
                dataInt = scan.nextInt();
                scan.nextLine();
                if (dataInt > 9999 && dataInt < 100000) {
                    customer.setZipCode(dataInt);
                    break;
                }
                System.out.println("\nInvalid zip code!");
            }
            while (!confirm) {
                System.out.println("\nIs this correct? (y/n)");
                System.out.println("First Name : " + customer.getFirstName());
                System.out.println("Last Name : " + customer.getLastName());
                System.out.println("E-mail : " + customer.getEmail());
                System.out.println("Username : " + customer.getUserName());
                System.out.println("Password : " + customer.getPassword());
                System.out.println("Street Address : " + customer.getHouseNumber()
                        + " " + customer.getStreetName());
                System.out.println("City : " + customer.getCity());
                System.out.println("State : " + customer.getState());
                System.out.println("Zip Code : " + customer.getZipCode());

                input = scan.next().charAt(0);
                switch (input) {
                    case 'y':
                        customerService.getCustomerDAO().save(customer);
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

    private void login() {
        while (true) {
            System.out.println("\nUsername: ");
            customer.setUserName(scan.next());

            System.out.println("\nPassword: ");
            customer.setPassword(scan.next());

            if (customerService.isValidLogin(customer)) {
                customer.setId((customerService.getCustomerDAO().getUserId(customer.getUserName())));
                customer.setAdmin(customerService.getCustomerDAO().getAdmin(customer.getUserName()));
                new MainMenu(customer).start();
                break;
            } else {
                System.out.println("\nInvalid login");
            }
        }
    }
}
