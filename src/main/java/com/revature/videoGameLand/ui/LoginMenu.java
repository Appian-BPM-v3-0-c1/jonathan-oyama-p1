package com.revature.videoGameLand.ui;
import com.revature.videoGameLand.daos.*;
import com.revature.videoGameLand.models.*;
import com.revature.videoGameLand.services.*;

import java.util.Scanner;

public class LoginMenu implements IMenu {
    private CustomerService customerService = new CustomerService(new CustomerDAO());
    private DeptService deptService = new DeptService(new DeptDAO());
    private ShoppingCartService shoppingCartService = new ShoppingCartService(new ShoppingCartDAO());
    private ScInventoryService scInventoryService = new ScInventoryService(new ScInventoryDAO());
    private OInventoryService oInventoryService = new OInventoryService(new OInventoryDAO());
    private OrderHistoryService orderHistoryService = new OrderHistoryService(new OrderHistoryDAO());
    private VideoGameService videoGameService = new VideoGameService(new VideoGameDAO());
    public LoginMenu() {
    }

    Scanner scan = new Scanner(System.in);
    Customer customer = new Customer();

    @Override
    public void start() {
        char input = ' ';
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
        /* get user input */
        char input = ' ';
        /* exit flag */
        boolean exit = false;
        boolean confirm = false;
        boolean firstAccount = false;
        String dataString = "";
        String username = "";
        String password1 = "";
        String password2 = "";
        int dataInt = 0;
        /* to get user input */
        Scanner scan = new Scanner(System.in);
        Customer customer = new Customer();
        Dept dept = new Dept();
        ShoppingCart shoppingCart = new ShoppingCart();
        ScInventory scInventory = new ScInventory();
        OInventory oInventory = new OInventory();
        OrderHistory orderHistory = new OrderHistory();
        int cartsDistributed;

        System.out.println("\nCreating account...");

        /* while exit is not true */
        while (!exit) {
            /* always creates admin account for first user created */
            if (deptService.firstTimeCheck()) {
                dept.setId(1);
                dept.setName("Video Game Land South Bay");
                dept.setCarts_distributed(0);
                cartsDistributed = dept.getCarts_distributed();
                deptService.getDeptDAO().save(dept);
                firstAccount = true;
            }
            else {
                dept = deptService.getDeptDAO().findById("1");
                cartsDistributed = dept.getCarts_distributed();
            }
            if (customerService.firstTimeCheck()) {

                System.out.println("\nCreating new admin account...");
                customer.setManager(true);
            }
            // all other users are customers unless given privileges by the
            // manager
            else {
                customer.setManager(false);
            }
            while (true) {
                System.out.println("\nEnter in first name: ");
                dataString = scan.nextLine();
                scan = new Scanner(System.in);
                if (!customerService.isValidName(dataString)) {
                    System.out.println("\nInvalid first name!");
                }
                else {
                    // If this is a valid first name with at least two characters, then
                    // set the last name to this value
                    customer.setFirstName(dataString);
                    break;
                }
            }
            while (true) {
                System.out.println("\nEnter in last name: ");
                dataString = scan.nextLine();
                scan = new Scanner(System.in);
                if (!customerService.isValidName(dataString)) {
                    System.out.println("\nInvalid last name!");
                }
                else {
                    // If this is a valid last name with at least two characters, then
                    // set the last name to this value
                    customer.setLastName(dataString);
                    break;
                }
            }
            while (true) {
                System.out.println("\nEnter in e-mail address: ");
                dataString = scan.nextLine();
                scan = new Scanner(System.in);
                if (!customerService.isValidEmail(dataString)) {
                    System.out.println("\nInvalid e-mail address!");
                }
                else {
                    // If this is a valid email, then
                    // set the email address to this value
                    customer.setEmail(dataString);
                    break;
                }
            }
            while (true) {
                System.out.println("\nEnter in username: ");
                username = scan.next();

                if (!customerService.isDupUsername(username)) {
                    if (!customerService.isValidUsername(username)) {
                        System.out.println("\nInvalid username :(");
                        System.out.println("\nUsername must contain at least eight characters");
                    } else {
                        // If this is a valid username and not a duplicate, then
                        // set the username to this value
                        customer.setUserName(username);
                        break;
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
                        cartsDistributed++;
                        dept.setCarts_distributed(cartsDistributed);
                        customer.setCartNumber(dept.getCarts_distributed());
                        customerService.getCustomerDAO().save(customer);
                        if (firstAccount) {
                            deptService.getDeptDAO().update(dept);
                        }
                        scInventory.setId(cartsDistributed);
                        scInventory.setName("empty");
                        scInventory.setPrice(0);
                        scInventory.setQuantity(0);
                        scInventoryService.getScInventoryDAO().save(scInventory);
                        oInventory.setId(cartsDistributed);
                        oInventory.setName("empty");
                        oInventory.setPrice(0);
                        oInventory.setQuantity(0);
                        oInventoryService.getOInventoryDAO().save(oInventory);
                        orderHistory.setId(cartsDistributed);
                        orderHistory.setCustomer_id(cartsDistributed);
                        orderHistory.setOinventory_id(cartsDistributed);
                        orderHistory.setTotal(0);
                        orderHistoryService.getOrderHistoryDAO().save(orderHistory);
                        shoppingCart.setOrder_id(cartsDistributed);
                        shoppingCart.setCustomer_id(cartsDistributed);
                        shoppingCart.setScInventory_Id(cartsDistributed);
                        shoppingCart.setTotal(0);
                        shoppingCartService.getShoppingCartDAO().save(shoppingCart);
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
        System.out.println("\nUsername: ");
        customer.setUserName(scan.next());

        System.out.println("\nPassword: ");
        customer.setPassword(scan.next());
        if (customerService.isValidLogin(customer)) {
            customer.setId((customerService.getCustomerDAO().getUserId(customer.getUserName())));
            customer.setManager(customerService.getCustomerDAO().getManager(customer.getUserName()));
            customer.setCartNumber(customerService.getCustomerDAO().getCartNumber(customer.getUserName()));

            new MainMenu(customer, customerService, shoppingCartService,
                    scInventoryService, orderHistoryService, oInventoryService,
                    videoGameService).start();
        } else {
            System.out.println("\nInvalid login");
        }
    }
}
