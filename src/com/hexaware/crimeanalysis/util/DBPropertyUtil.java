package com.hexaware.crimeanalysis.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {

    public static String getConnectionString(String fileName) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(fileName)) {
            props.load(fis);
            String driver = props.getProperty("driverClass");
            String url = props.getProperty("url");
            String username = props.getProperty("username");
            String password = props.getProperty("password");

            return driver + ";" + url + ";" + username + ";" + password;
        } catch (IOException e) {
            System.out.println("Error reading property file: " + e.getMessage());
            return null;
        }
    }
}
