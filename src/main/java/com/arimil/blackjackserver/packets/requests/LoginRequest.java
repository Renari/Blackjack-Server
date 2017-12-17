package com.arimil.blackjackserver.packets.requests;

import com.arimil.blackjackserver.DatabaseManager;
import com.arimil.blackjackserver.User;
import com.arimil.blackjackserver.UserManager;
import com.arimil.blackjackserver.packets.Message;
import com.arimil.blackjackserver.packets.responses.LoginResponse;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRequest extends Message {
    private String username;
    private String password;

    public LoginRequest() {

    }

    public LoginRequest(String user, String pass) {
        this.username = user;
        this.password = pass;
    }

    @Override
    public boolean Process(Connection c, Listener l) {
        Log.info("login packet received with details: " + username + ", " + password);
        try {
            String query = "SELECT COUNT(*) AS total FROM users WHERE username = ?";
            PreparedStatement countUser = DatabaseManager.getConnection().prepareStatement(query);
            countUser.setString(1, username);
            ResultSet results = countUser.executeQuery();
            results.next();
            int count = results.getInt("total");
            //register new user
            if (count == 0) {
                String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";
                PreparedStatement insertUser = DatabaseManager.getConnection().prepareStatement(insertQuery);
                insertUser.setString(1, username);
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] passhash = digest.digest(password.getBytes());
                insertUser.setString(2, new String(passhash));
                insertUser.execute();
                User u = new User(username, 50000);
                UserManager.putUser(c.getID(), u);
                c.sendTCP(new LoginResponse(50000));
            } else {
                String userQuery = "SELECT * FROM users WHERE username = ?";
                PreparedStatement selectUser = DatabaseManager.getConnection().prepareStatement(userQuery);
                selectUser.setString(1, username);
                ResultSet user = selectUser.executeQuery();
                if (user.next()) {
                    String userpass = user.getString("password");
                    MessageDigest digest = MessageDigest.getInstance("SHA-256");
                    byte[] passhash = digest.digest(password.getBytes());
                    if (new String(passhash).equals(userpass)) {
                        //correct password
                        int currency = user.getInt("currency");
                        User u = new User(username, currency);
                        UserManager.putUser(c.getID(), u);
                        c.sendTCP(new LoginResponse(currency));
                        return true;
                    } else {
                        //incorrect password
                        c.sendTCP(new LoginResponse("Incorrect username or password"));
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage());
            c.sendTCP(new LoginResponse("A SQL exception occurred"));
        } catch (NoSuchAlgorithmException e) {
            Log.error(e.getMessage());
            c.sendTCP(new LoginResponse("A SHA exception occurred"));
        }
        return false;
    }
}
