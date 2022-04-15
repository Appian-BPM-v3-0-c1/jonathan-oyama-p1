package com.revature.videoGameLand.daos;

import com.revature.videoGameLand.connection.DatabaseConnection;
import com.revature.videoGameLand.models.OInventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OInventoryDAO implements CrudDAO<OInventory> {
    Connection con = DatabaseConnection.getCon();
    @Override
    public int save(OInventory newObj) {
        int n = 0;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO oinventory ("
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
    public List<OInventory> findAll() {
        List<OInventory> oInventoryList = new ArrayList<>();
        try {PreparedStatement ps = con.prepareStatement("SELECT * FROM oinventory");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OInventory oInventory = new OInventory();
                oInventory.setId(rs.getInt("id"));
                oInventory.setName(rs.getString("name"));
                oInventory.setPrice(rs.getFloat("price"));
                oInventory.setQuantity(rs.getInt("quantity"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oInventoryList;
    }

    @Override
    public OInventory findById(String Id) {
        return null;
    }

    @Override
    public boolean update(OInventory updatedObj) {
        return false;
    }

    @Override
    public boolean removeById(String Id) {
        return false;
    }
}
