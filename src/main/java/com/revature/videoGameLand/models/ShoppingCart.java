package com.revature.videoGameLand.models;

public class ShoppingCart {
    private int id;
    private int game_id;
    private int customer_id;

    public ShoppingCart() {
    }

    public ShoppingCart(int id, int game_id, int customer_id) {
        this.id = id;
        this.game_id = game_id;
        this.customer_id = customer_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", game_id=" + game_id +
                ", customer_id=" + customer_id +
                '}';
    }
}