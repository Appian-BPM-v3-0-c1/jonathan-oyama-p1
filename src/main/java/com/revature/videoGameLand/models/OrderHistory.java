package com.revature.videoGameLand.models;

public class OrderHistory {
    private int id;
    private int customer_id;
    private int oinventory_id;
    private float total;

    public OrderHistory() {
    }

    public OrderHistory(int id, int customer_id, int oinventory_id, float total) {
        this.id = id;
        this.customer_id = customer_id;
        this.oinventory_id = oinventory_id;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getOinventory_id() {
        return oinventory_id;
    }

    public void setOinventory_id(int oinventory_id) {
        this.oinventory_id = oinventory_id;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderHistory{" +
                "id=" + id +
                ", customer_id=" + customer_id +
                ", oinventory_id=" + oinventory_id +
                ", total=" + total +
                '}';
    }
}
