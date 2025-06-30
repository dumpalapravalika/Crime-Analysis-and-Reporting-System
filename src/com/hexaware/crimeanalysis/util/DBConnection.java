package com.hexaware.crimeanalysis.util;

import java.sql.Connection;

public class DBConnection {
    private static final String PROP_FILE = "db.properties"; // path to your .properties file

    public static Connection getConnection() {
        return DBConnUtil.getConnection(PROP_FILE);
        
    }
}
