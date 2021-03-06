package com.revature.videoGameLand.models;

import java.sql.Array;

public class ShoppingCart {
    private int id;
    private int order_id;
    private int customer_id;
    private int scInventory_Id;
    private float total;

    public ShoppingCart() {
    }

    public ShoppingCart(int id, int order_id, int customer_id, int scInventory_Id, float total) {
        this.id = id;
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.scInventory_Id = scInventory_Id;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getScInventory_Id() {
        return scInventory_Id;
    }

    public void setScInventory_Id(int scInventory_Id) {
        this.scInventory_Id = scInventory_Id;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", order_id=" + order_id +
                ", customer_id=" + customer_id +
                ", scInventory_Id=" + scInventory_Id +
                ", total=" + total +
                '}';
    }
}