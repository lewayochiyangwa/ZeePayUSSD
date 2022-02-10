package com.example.ZeePayUSSD.pojos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {

   public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlserver://DESKTOP-FM7TJMK\\\\SQLEXPRESS:1433;databaseName=ZEEPAYUSSD;integratedSecurity=true";
        //String username = "test";
       // String password = "test";
        return DriverManager.getConnection(url);
    }
}
