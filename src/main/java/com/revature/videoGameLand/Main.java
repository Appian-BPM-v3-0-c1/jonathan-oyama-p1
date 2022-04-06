package com.revature.videoGameLand;
import com.revature.videoGameLand.connection.DatabaseConnection;
import com.revature.videoGameLand.ui.MainMenu;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        //new MainMenu().start();
        Connection con = DatabaseConnection.getCon();

        System.out.println(con);
    }
}