package com.example.powerpal;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class DbManager extends AppCompatActivity {

    public static boolean createNewAccount(String email, String password) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:PowerPal.db");
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("create table if not exists user (id integer primary key autoincrement, email string, password string)");
            PreparedStatement ps = conn.prepareStatement("select * from user where email == ? ");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (!rs.next())
                return false; // email already exists

            ps = conn.prepareStatement("insert into user values(?,?)");
            ps.setString(1, email);
            ps.setString(1, password);
            ps.executeQuery();
            System.out.println("Account Created!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
                return false;
            }
            return true;
        }
    }
}