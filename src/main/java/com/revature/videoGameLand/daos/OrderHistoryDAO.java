package com.revature.videoGameLand.daos;

import com.revature.videoGameLand.connection.DatabaseConnection;
import com.revature.videoGameLand.models.OInventory;
import com.revature.videoGameLand.models.OrderHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryDAO implements CrudDAO<OrderHistory> {
    Connection con = DatabaseConnection.getCon();

    @Override
    public int save(OrderHistory newObj) {
        int n = 0;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO order_history ("
                + "customer_id, oinventory_id, total) VALUES (?, ?, ?)");
            ps.setInt(1, newObj.getCustomer_id());
            ps.setInt(2, newObj.getOinventory_id());
            ps.setFloat(3, newObj.getTotal());
            n = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public List<OrderHistory> findAll() {
        List<OrderHistory> orderHistoryList = new ArrayList<>();

        try {PreparedStatement ps = con.prepareStatement("SELECT * FROM order_history");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderHistory orderHistory = new OrderHistory();

                orderHistory.setId(rs.getInt("id"));
                orderHistory.setCustomer_id(rs.getInt("customer_id"));
                orderHistory.setOinventory_id(rs.getInt("oinventory_id"));
                orderHistory.setTotal(rs.getFloat("total"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }

    @Override
    public OrderHistory findById(String Id) {
        return null;
    }

    @Override
    public boolean update(OrderHistory updatedObj) {
        return false;
    }

    @Override
    public boolean removeById(String Id) {
        return false;
    }
}
