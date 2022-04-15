package com.revature.videoGameLand.services;

import com.revature.videoGameLand.daos.ShoppingCartDAO;
import com.revature.videoGameLand.models.ShoppingCart;

import java.util.List;

public class ShoppingCartService {
    private final ShoppingCartDAO shoppingCartDAO;

    public ShoppingCartService(ShoppingCartDAO shoppingCartDAO) {
        this.shoppingCartDAO = shoppingCartDAO;
    }

    public ShoppingCartDAO getShoppingCartDAO() {
        return shoppingCartDAO;
    }

    public boolean firstTimeCheck() {
        List<ShoppingCart> shoppingCartList = shoppingCartDAO.findAll();
        System.out.println();
        if (shoppingCartList.isEmpty()) {
            return true;
        }
        return false;
    }
}