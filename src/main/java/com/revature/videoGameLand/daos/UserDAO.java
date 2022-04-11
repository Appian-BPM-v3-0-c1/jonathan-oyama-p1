package com.revature.videoGameLand.daos;

import com.revature.videoGameLand.connection.DatabaseConnection;
import com.revature.videoGameLand.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CrudDAO<User>{
    Connection con = DatabaseConnection.getCon();

    @Override
    public int save(User newObj) {
        int n = 0;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (admin, firstname, lastname, email, username, password, streetaddress, city, state, zip) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setBoolean(1, newObj.isAdmin());
            ps.setString(2, newObj.getFirstName());
            ps.setString(3, newObj.getLastName());
            ps.setString(4, newObj.getEmail());
            ps.setString(5, newObj.getUserName());
            ps.setString(6, newObj.getPassword());
            ps.setString(7, newObj.getStreetAddress());
            ps.setString(8, newObj.getCity());
            ps.setString(9, newObj.getState());
            ps.setInt(10, newObj.getZip());

            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }


    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(String Id) {
        return null;
    }

    @Override
    public boolean update(User updatedObj) {
        return false;
    }

    @Override
    public boolean removeById(String Id) {
        return false;
    }
}