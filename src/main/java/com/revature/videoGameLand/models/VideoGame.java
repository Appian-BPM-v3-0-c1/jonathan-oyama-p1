package com.revature.videoGameLand.models;

public class VideoGame {
    private int id;
    private String gameName;
    private int stock;
    private float gamePrice;
    private int game_dept_id;

    public VideoGame() {
    }

    public VideoGame(int id, String gameName, int stock, float gamePrice) {
        this.id = id;
        this.gameName = gameName;
        this.stock = stock;
        this.gamePrice = gamePrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getGamePrice() {
        return gamePrice;
    }

    public void setGamePrice(float gamePrice) {
        this.gamePrice = gamePrice;
    }

    public int getGame_dept_id() {
        return game_dept_id;
    }

    public void setGame_dept_id(int game_dept_id) {
        this.game_dept_id = game_dept_id;
    }

    @Override
    public String toString() {
        return "VideoGame{" +
                "id=" + id +
                ", gameName='" + gameName + '\'' +
                ", stock=" + stock +
                ", gamePrice=" + gamePrice +
                ", game_dept_id=" + game_dept_id +
                '}';
    }
}