package com.revature.videoGameLand.daos;

import com.revature.videoGameLand.connection.DatabaseConnection;
import com.revature.videoGameLand.models.VideoGame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VideoGameDAO implements CrudDAO<VideoGame> {
    Connection con = DatabaseConnection.getCon();

    @Override
    public int save(VideoGame newObj) {
        int n = 0;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO videogames (name, stock, price, console, dept_id) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, newObj.getName());
            ps.setInt(2, newObj.getStock());
            ps.setFloat(3, newObj.getPrice());
            ps.setString(4, newObj.getConsoleVersion());
            ps.setInt(5, newObj.getDept_id());

            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public List<VideoGame> findAll() {
        List<VideoGame> videoGameList = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM videogames");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                VideoGame videogame = new VideoGame();

                videogame.setId(rs.getInt("id"));
                videogame.setName(rs.getString("name"));
                videogame.setStock(rs.getInt("stock"));
                videogame.setPrice(rs.getFloat("price"));
                videogame.setConsoleVersion(rs.getString("console"));
                videogame.setDept_id(rs.getInt("dept_id"));

                videoGameList.add(videogame);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return videoGameList;
    }

    @Override
    public VideoGame findById(String Id) {
        return null;
    }

    @Override
    public boolean update(VideoGame updatedObj) {
        return false;
    }

    @Override
    public boolean removeById(String Id) {
        return false;
    }
}
