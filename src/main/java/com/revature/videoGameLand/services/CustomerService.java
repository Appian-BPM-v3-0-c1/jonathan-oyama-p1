package com.revature.videoGameLand.services;

import com.revature.videoGameLand.daos.CustomerDAO;
import com.revature.videoGameLand.models.Customer;

import java.util.List;

public class CustomerService {
    private final CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public CustomerDAO getCustomerDAO() {
        return customerDAO;
    }

    public boolean isDupUsername(String username) {
        List<String> username_list = customerDAO.findAllUsernames();

        for (String u : username_list) {
            if (u.equals(username)) {
                return true;
            }
        }

        return false;
    }

    public boolean isValidName(String name)
    {
        return name.matches("^[a-zA-Z]{2,}+$");
    }

    public boolean isValidUsername(String username) {
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$");
    }

    public boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    }

    public boolean isValidLogin(Customer customer) {
        List<Customer> customerList = customerDAO.findAll();

        for (Customer u : customerList) {
            if (u.getUserName().equals(customer.getUserName()) && u.getPassword().equals(customer.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidAdmin(Customer customer) {
        List<Customer> customerList = customerDAO.findAll();

        for (Customer u : customerList) {
            if (u.getUserName().equals(customer.getUserName())
                    && u.getPassword().equals(customer.getPassword())
                    && u.isManager()) {
                return true;
            }
        }
        return false;
    }


    public boolean firstTimeCheck() {
        int input = 0;
        List<Customer> userList = customerDAO.findAll();
        System.out.println();
        if (userList.isEmpty()) {
            return true;
        }
        return false;
    }
}
