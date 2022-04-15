package com.revature.videoGameLand.daos;

import com.revature.videoGameLand.connection.DatabaseConnection;
import com.revature.videoGameLand.models.ShoppingCart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDAO implements CrudDAO<ShoppingCart> {
    Connection con = DatabaseConnection.getCon();
    @Override
    public int save(ShoppingCart newObj) {
        int n = 0;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO shopping_cart (" +
                    "order_id, customer_id, scinventory_id, total) VALUES (?, ?, ?, ?)");
            ps.setInt(1, newObj.getOrder_id());
            ps.setInt(2, newObj.getCustomer_id());
            ps.setInt(3, newObj.getScInventory_Id());
            ps.setFloat(4, newObj.getTotal());
            n = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public List<ShoppingCart> findAll() {
        List<ShoppingCart> shoppingCartList = new ArrayList<>();

        try {PreparedStatement ps = con.prepareStatement("SELECT * FROM shopping_cart");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ShoppingCart shoppingCart = new ShoppingCart();

                shoppingCart.setId(rs.getInt("id"));
                shoppingCart.setOrder_id(rs.getInt("order_id"));
                shoppingCart.setCustomer_id(rs.getInt("customer_id"));
                shoppingCart.setScInventory_Id(rs.getInt("scinventory_id"));
                shoppingCart.setTotal(rs.getFloat("total"));
                shoppingCartList.add(shoppingCart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shoppingCartList;
    }

    @Override
    public ShoppingCart findById(String Id) {
        return null;
    }

    @Override
    public boolean update(ShoppingCart updatedObj) {
        return false;
    }

    @Override
    public boolean removeById(String Id) {
        return false;
    }
}
