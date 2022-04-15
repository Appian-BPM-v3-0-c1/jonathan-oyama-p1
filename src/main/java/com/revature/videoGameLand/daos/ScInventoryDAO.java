package com.revature.videoGameLand.daos;

import com.revature.videoGameLand.connection.DatabaseConnection;
import com.revature.videoGameLand.models.ScInventory;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScInventoryDAO implements CrudDAO<ScInventory> {
    Connection con = DatabaseConnection.getCon();

    @Override
    public int save(ScInventory newObj) {
        int n = 0;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO scinventory ("
                + "name, price, quantity) VALUES (?, ?, ?)");
            ps.setString(1, newObj.getName());
            ps.setFloat(2, newObj.getPrice());
            ps.setInt(3, newObj.getQuantity());
            n = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public List<ScInventory> findAll() {
        List<ScInventory> scInventoryList = new ArrayList<>();

        try {PreparedStatement ps = con.prepareStatement("SELECT * FROM scinventory");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ScInventory scInventory = new ScInventory();

                scInventory.setId(rs.getInt("id"));
                scInventory.setName(rs.getString("name"));
                scInventory.setPrice(rs.getFloat("price"));
                scInventory.setQuantity(rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scInventoryList;
    }

    @Override
    public ScInventory findById(String Id) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM scinventory WHERE id = " + Id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            ScInventory scInventory = new ScInventory();
            scInventory.setId(rs.getInt("id"));
            scInventory.setName(rs.getString("name"));
            scInventory.setPrice(rs.getFloat("price"));
            scInventory.setQuantity(rs.getInt("quantity"));
            return scInventory;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(ScInventory updatedObj) {
        return false;
    }

    @Override
    public boolean removeById(String Id) {
        return false;
    }
}
