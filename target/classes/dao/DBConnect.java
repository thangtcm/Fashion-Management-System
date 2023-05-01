/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnect {
    static String url = "jdbc:sqlserver://localhost:54499;instance=SQLEXPRESS;databaseName=Fashtion;encrypt=true;trustServerCertificate=true";
    static String username = "sa";
    static String password = "04082002";
    
    Connection conn = null;
    
    public DBConnect(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Connect Database ERROR : ");
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Connect Database ERROR : ");
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, e);
        }
        return conn;
    }
}
