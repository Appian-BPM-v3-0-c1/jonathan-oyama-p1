package com.revature.videoGameLand.daos;

import com.revature.videoGameLand.connection.DatabaseConnection;
import com.revature.videoGameLand.models.ShoppingCart;

import java.sql.Connection;
import java.util.List;

public class ShoppingCartDAO implements CrudDAO<ShoppingCart> {
    //Connection con = DatabaseConnection.getCon();
    @Override
    public int save(ShoppingCart newObj) {
        return 0;
    }

    @Override
    public List<ShoppingCart> findAll() {
        return null;
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
