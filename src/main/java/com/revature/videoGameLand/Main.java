package com.revature.videoGameLand;
import com.revature.videoGameLand.daos.CustomerDAO;
import com.revature.videoGameLand.services.CustomerService;
import com.revature.videoGameLand.ui.LoginMenu;

public class Main {
    public static void main(String[] args) {
        /*start application */
        new LoginMenu(new CustomerService(new CustomerDAO())).start();
    }
}