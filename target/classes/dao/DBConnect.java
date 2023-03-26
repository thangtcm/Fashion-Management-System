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

/**
 *
 * @author couni
 */
public class DBConnect {
    public static Connection getConnection() throws SQLException
    {
        Connection connect = null;
        String url = "jdbc:sqlserver://localhost:54499;instance=SQLEXPRESS;databaseName=Fashtion;encrypt=true;trustServerCertificate=true";
        String username = "sa";
        String password = "04082002";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connect = DriverManager.getConnection(url, username, password);
            System.out.println("Connect to SQL SERVER Susscess");
            return connect;
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("Connect Database ERROR : ");
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connect;
    }
    public static void main(String[] arg) throws SQLException
    {
        Connection connect = getConnection();
            System.out.println(connect.toString());
            connect.close();
    }
}
