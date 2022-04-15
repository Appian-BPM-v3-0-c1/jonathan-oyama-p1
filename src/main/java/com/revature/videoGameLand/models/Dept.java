package com.revature.videoGameLand.models;

public class Dept {
    private int id;
    private String name;
    private int carts_distributed;

    public Dept() {
    }

    public Dept(int id, String name, int carts_distributed) {
        this.id = id;
        this.name = name;
        this.carts_distributed = carts_distributed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCarts_distributed() {
        return carts_distributed;
    }

    public void setCarts_distributed(int carts_distributed) {
        this.carts_distributed = carts_distributed;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", carts_distributed=" + carts_distributed +
                '}';
    }
}
