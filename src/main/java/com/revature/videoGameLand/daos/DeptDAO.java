package com.revature.videoGameLand.daos;

import com.revature.videoGameLand.connection.DatabaseConnection;
import com.revature.videoGameLand.models.Dept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeptDAO  implements CrudDAO<Dept> {
    Connection con = DatabaseConnection.getCon();
    @Override
    public int save(Dept newObj) {
        int n = 0;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO dept (" +
                    "name, carts_distributed) VALUES (?, ?)");
                    ps.setString(1, newObj.getName());
                    ps.setInt(2, newObj.getCarts_distributed());

                    n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public List<Dept> findAll() {
        List<Dept> deptList = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM dept");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Dept dept = new Dept();

                dept.setId(rs.getInt("id"));
                dept.setName(rs.getString("name"));
                dept.setCarts_distributed(rs.getInt("carts_distributed"));

                deptList.add(dept);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deptList;
    }

    @Override
    public Dept findById(String Id) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM dept WHERE id = " + Id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Dept dept = new Dept();
            dept.setId(rs.getInt("id"));
            dept.setName(rs.getString("name"));
            dept.setCarts_distributed(rs.getInt("carts_distributed"));


            return dept;
        } catch (Exception e) {
            e.printStackTrace();
        }
       return null;
    }

    @Override
    public boolean update(Dept updatedObj) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE dept SET " +
                    "name = ?, carts_distributed = ?");
            ps.setString(1, updatedObj.getName());
            ps.setInt(2, updatedObj.getCarts_distributed());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeById(String Id) {
        return false;
    }


}
