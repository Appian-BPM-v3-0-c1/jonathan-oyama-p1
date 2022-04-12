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
            PreparedStatement ps = con.prepareStatement("INSERT INTO customers (admin, firstname, lastname, email, username, password, housenumber, streetname, city, state, zipcode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setBoolean(1, newObj.isAdmin());
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

            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }


    @Override
    public List<Customer> findAll() {
        List<Customer> customerList = new ArrayList<>();

        try {PreparedStatement ps = con.prepareStatement("SELECT * FROM customers");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();

                customer.setId(rs.getInt("id"));
                customer.setAdmin(rs.getBoolean("admin"));
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
        return customerList;
    }

    @Override
    public Customer findById(String Id) {
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
            PreparedStatement ps = con.prepareStatement("SELECT (username) FROM customers");
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
            PreparedStatement ps = con.prepareStatement("SELECT (id) FROM customers where username = ?");
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

    public boolean getAdmin(String username) {
        boolean adminPass = false;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT (admin) FROM customers where username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                adminPass = rs.getBoolean("admin");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminPass;
    }
}