package com.arimil.blackjackserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {
    static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Properties connectionProps = new Properties();
            connectionProps.put("user", "");
            connectionProps.put("password", "");

            try {
                conn = DriverManager.getConnection("", connectionProps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Connected to database");
        }
        return conn;
    }
}
