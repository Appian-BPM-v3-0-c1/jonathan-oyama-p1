package com.revature.videoGameLand.models;

public class VideoGame {
    private int id;
    private String name;
    private int stock;
    private float price;
    private String console;
    private int dept_id;

    public VideoGame() {
    }

    public VideoGame(int id, String name, int stock, float price, String console, int dept_id) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.console = console;
        this.dept_id = dept_id;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getConsoleVersion() {
        return console;
    }

    public void setConsoleVersion(String console) {
        this.console = console;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    @Override
    public String toString() {
        return "\nId: " + id + "\nGame Name: " + name + "\nStock: " + stock +
                "\nPrice: $" + price +
                "\nConsole Version: " + console +
                "\nGame_dept_id: " + dept_id;
    }
}