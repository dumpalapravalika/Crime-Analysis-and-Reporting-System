package com.hexaware.crimeanalysis.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {

    public static Connection getConnection(String propertyFile) {
        String connString = DBPropertyUtil.getConnectionString(propertyFile);
        if (connString == null) return null;

        String[] parts = connString.split(";");
        if (parts.length != 4) {
            System.out.println("Invalid connection string format");
            return null;
        }

        try {
            Class.forName(parts[0]); // Load driver
            return DriverManager.getConnection(parts[1], parts[2], parts[3]);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("DB Connection Error: " + e.getMessage());
            return null;
        }
    }
}
