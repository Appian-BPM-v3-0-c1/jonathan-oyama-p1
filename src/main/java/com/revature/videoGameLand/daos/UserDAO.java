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
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (admin, firstname, lastname, email, username, password, street, city, state, zipcode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setBoolean(1, newObj.isAdmin());
            ps.setString(2, newObj.getFirstName());
            ps.setString(3, newObj.getLastName());
            ps.setString(4, newObj.getEmail());
            ps.setString(5, newObj.getUserName());
            ps.setString(6, newObj.getPassword());
            ps.setString(7, newObj.getStreet());
            ps.setString(8, newObj.getCity());
            ps.setString(9, newObj.getState());
            ps.setInt(10, newObj.getZipCode());

            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }


    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();

        try {PreparedStatement ps = con.prepareStatement("SELECT * FROM videogames");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setAdmin(rs.getBoolean("admin"));
                user.setFirstName("firstname");
                user.setLastName("lastname");
                user.setEmail("email");
                user.setUserName("username");
                user.setPassword("password");
                user.setStreet("street");
                user.setCity("city");
                user.setState("state");
                user.setZipCode(rs.getInt("zipcode"));

                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
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