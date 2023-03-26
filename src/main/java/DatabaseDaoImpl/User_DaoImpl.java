/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseDaoImpl;

import DatabaseDao.User_Dao;
import Model.User;
import dao.Convert;
import dao.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author couni
 */
public class User_DaoImpl implements User_Dao{
    Connection connection = null;
    
    public boolean AddUser(User user)
    {
        PreparedStatement preparedStatement = null;
        String SQL_AddStaff = "INSERT INTO [User] VALUES (?,?,?,?,?,?,?)";
        
        try{
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement(SQL_AddStaff);
            preparedStatement.setString(1, user.getUserName().trim());
            preparedStatement.setString(2, user.getPassword().trim());
            preparedStatement.setString(3, user.getFulName().trim());
            preparedStatement.setDate(4, Convert.convertDate(user.getBirthday()));
            preparedStatement.setInt(6, user.getPhone());
            preparedStatement.setString(6, user.getAddress().trim());
            preparedStatement.setInt(7, 1);
            
        }
        catch(SQLException e) {
            System.out.println(e.getMessage()); 
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(User_DaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public List<User> getStaffList(User user) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<User> list = new ArrayList<User>();

        /*
         * Default: displays all student information
         */
        StringBuilder SQL_GetStaffInformation = new StringBuilder("SELECT * FROM [User]");

        /*------------------------------------------------------------------------
         * getStudent_name() : Get it from the information entered by the user.  |
         * -----------------------------------------------------------------------
         */
        if (!user.getFulName().isEmpty())
        {
                // If 'getStudent_name()' is null ¡ª¡ª> false (Not execute)
            SQL_GetStaffInformation.append(" AND FirstName LIKE '%").append(user.getFulName()).append("%' ");

        }

        try
        {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement(SQL_GetStaffInformation.toString().replaceFirst("AND","WHERE"));

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                    User table_user = new User();
                    table_user.setID(resultSet.getInt("ID"));
                    table_user.setFulName(resultSet.getString("FullName").trim());
                    table_user.setBirthday(resultSet.getDate("BrithDay"));
                    table_user.setAddress(resultSet.getString("Address").trim());
                    table_user.setPhone(resultSet.getInt("Phone"));

                    list.add(table_user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(User_DaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public boolean Delete_Staff(int ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean Update_Staff(User user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public User Login_Staff(User user) {
        String SQL_ADMINISTRATOR_LOGIN = "SELECT * FROM [User] WHERE UserName = ? AND Password = ?";
        /*
         * In order to initialize the main interface though the user's personal information.
         */
        User table_Staff_temp = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            /*
             * ------------------------------------------------------------------------------
             * 'connection' : Pass SQL statements to objects that manipulate the database	|
             *  From Connection com.YUbuntu.dao.BasicDao.connection 						|
             * ------------------------------------------------------------------------------
             */
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement(SQL_ADMINISTRATOR_LOGIN);

            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            resultSet = preparedStatement.executeQuery();

            //Store the user information
            if (resultSet.next())
            {		
                    /*---------------------------------------------------------------------------------------------------------------------------------
                     * Stores the data of Student_ID and .. so that in order to initialize the main interface though the user's personal information. |
                     *---------------------------------------------------------------------------------------------------------------------------------
                     *///Such as it's be used when change user's password !
                    table_Staff_temp = new User();
                    table_Staff_temp.setID(resultSet.getInt("ID"));
                    table_Staff_temp.setUserName(resultSet.getString("UserName").trim());
                    table_Staff_temp.setPassword(resultSet.getString("Password").trim());
                    table_Staff_temp.setFulName(resultSet.getString("FullName").trim());
                    table_Staff_temp.setBirthday(resultSet.getDate("BrithDay"));
                    table_Staff_temp.setAddress(resultSet.getString("Address").trim());
                    table_Staff_temp.setPhone(resultSet.getInt("Phone"));
                    //...
            }
        } catch (Exception e)
        {
                System.err.println("ERROR : Fail to check specified information of student from the SQL database !\n");
                e.printStackTrace();
        }		
        return table_Staff_temp;	
    }

    @Override
    public String ChangePassword(User user, String Password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public boolean ResignerUser(User user)
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean AddStaff(User user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User LoginUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
