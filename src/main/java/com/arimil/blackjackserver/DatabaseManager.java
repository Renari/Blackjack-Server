package com.arimil.blackjackserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {
    static Connection conn = null;

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Properties connectionProps = new Properties();
                connectionProps.put("user", "blackjackserver");
                connectionProps.put("password", "BlackjackDatabase");

                conn = DriverManager.getConnection("jdbc:mysql://blackjackdatabase.cg0iztsxwxpo.us-east-1.rds.amazonaws.com:3306/blackjackserver", connectionProps);

                System.out.println("Connected to database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
