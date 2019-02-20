package org.dgby.gatorpos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static String url = "jdbc:sqlite::memory";
    private static Connection con = null;

    public static Connection getConnection() {
        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            System.out.println("Failed to connect to database.");
            con = null;
        }

        return con;
    }
}