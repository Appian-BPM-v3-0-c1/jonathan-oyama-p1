package com.revature.videoGameLand.daos;

import com.revature.videoGameLand.connection.DatabaseConnection;
import com.revature.videoGameLand.models.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements CrudDAO<Customer>{
    Connection con = DatabaseConnection.getCon();

    @Override
    public int save(Customer newObj) {
        int n = 0;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO customer (manager, firstname, lastname, " +
                    "email, username, password, housenumber, streetname, city, state, zipcode, cartnumber" +
                    ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setBoolean(1, newObj.isManager());
            ps.setString(2, newObj.getFirstName());
            ps.setString(3, newObj.getLastName());
            ps.setString(4, newObj.getEmail());
            ps.setString(5, newObj.getUserName());
            ps.setString(6, newObj.getPassword());
            ps.setInt(7, newObj.getHouseNumber());
            ps.setString(8, newObj.getStreetName());
            ps.setString(9, newObj.getCity());
            ps.setString(10, newObj.getState());
            ps.setInt(11, newObj.getZipCode());
            ps.setInt(12, newObj.getCartNumber());

            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customerList = new ArrayList<>();

        try {PreparedStatement ps = con.prepareStatement("SELECT * FROM customer");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();

                customer.setId(rs.getInt("id"));
                customer.setManager(rs.getBoolean("manager"));
                customer.setFirstName(rs.getString("firstname"));
                customer.setLastName(rs.getString("lastname"));
                customer.setEmail(rs.getString("email"));
                customer.setUserName(rs.getString("username"));
                customer.setPassword(rs.getString("password"));
                customer.setHouseNumber(rs.getInt("housenumber"));
                customer.setStreetName(rs.getString("streetname"));
                customer.setCity(rs.getString("city"));
                customer.setState(rs.getString("state"));
                customer.setZipCode(rs.getInt("zipcode"));
                customer.setCartNumber(rs.getInt("cartnumber"));

                customerList.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    @Override
    public Customer findById(String Id) {
        /*
        Customer customer = new Customer();
        int customerID = Integer.parseInt(Id);
        try {PreparedStatement ps = con.prepareStatement("SELECT * FROM customers");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();

                customer.setId(rs.getInt("id"));
                customer.setManager(rs.getBoolean("manager"));
                customer.setFirstName(rs.getString("firstname"));
                customer.setLastName(rs.getString("lastname"));
                customer.setEmail(rs.getString("email"));
                customer.setUserName(rs.getString("username"));
                customer.setPassword(rs.getString("password"));
                customer.setStreetName(rs.getString("streetname"));
                customer.setCity(rs.getString("city"));
                customer.setState(rs.getString("state"));
                customer.setZipCode(rs.getInt("zipcode"));

                customerList.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;*/
        return null;
    }

    @Override
    public boolean update(Customer updatedObj) {
        return false;
    }

    @Override
    public boolean removeById(String Id) {
        return false;
    }

    public List<String> findAllUsernames() {
        List<String> username_list = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT (username) FROM customer");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                username_list.add(rs.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username_list;
    }

    public int getUserId(String username) {
        int id = 0;

        try {
            PreparedStatement ps = con.prepareStatement("SELECT (id) FROM customer where username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean getManager(String username) {
        boolean managerPass = false;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT (manager) FROM customer where username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                managerPass = rs.getBoolean("manager");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return managerPass;
    }
    public int getCartNumber(String username) {
        int cartnumber = 0;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT (cartnumber) FROM customer where username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cartnumber = rs.getInt("cartnumber");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartnumber;
    }
}