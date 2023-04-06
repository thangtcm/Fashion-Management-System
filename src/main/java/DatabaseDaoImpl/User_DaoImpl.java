/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseDaoImpl;

import DatabaseDao.User_Dao;
import Enum.TypeNotification;
import Enum.TypeRoleName;
import Model.User;
import Sevices.Notification;
import dao.Convert;
import dao.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author couni
 */
public class User_DaoImpl implements User_Dao{
 
    Connection conn = null;
    PreparedStatement prepStatement= null;
    ResultSet resultSet = null;
    Notification notification = new Notification();
    
    public User_DaoImpl()
    {
        conn = new DBConnect().getConnection();
    }
    
    @Override
    public boolean AddUser(User user)
    {
        try
        {
            String query = "INSERT INTO [User] (UserName, Password, Email, FullName, Birthday, Phone, Address, RoleName, AvatarUrl, Gender) VALUES (?,?,?,?,?,?,?,?,?,?)";
            addFunction(user, query);
            int rowsInserted = prepStatement.executeUpdate(); 
            if (rowsInserted > 0) {
                notification.showMessage("New user has been added.", TypeNotification.Susscess.toString());
                return true;
            } else {
                notification.showMessage("Failed to add new user.", TypeNotification.Error.toString());
            }
            
        }
        catch(SQLException e) {
            System.out.println(e.getMessage()); 
        }finally {
            try {
                if (prepStatement != null) {
                    prepStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }
    
    public void addFunction(User user, String query) {
        try {
            prepStatement = conn.prepareStatement(query);
            int i = 1;
            prepStatement.setString(i++, user.getUserName().trim());
            prepStatement.setString(i++, user.getPassword().trim());
            prepStatement.setString(i++, user.getEmail().trim());
            prepStatement.setString(i++, user.getFullName().trim());
            prepStatement.setDate(i++, Convert.convertDate(user.getBirthday()));
            prepStatement.setString(i++, user.getPhone());
            prepStatement.setString(i++, user.getAddress().trim());
            prepStatement.setString(i++, TypeRoleName.Custom.toString());
            prepStatement.setString(i++, user.getAvartarUrl().trim());
            prepStatement.setString(i++, user.getGender().trim());
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
   

    @Override
    public ArrayList<User> getUserList(User user) {

        ArrayList<User> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM [User]");
        if(user != null)
        {
            if(user.getNumberPhone() != null)
            {
                sql.append(" AND Phone LIKE '%").append(user.getNumberPhone()).append("%'");
            }

            if(user.getFullName() != null)
            {
                sql.append(" AND FullName LIKE N'").append(StringHandle.addWildcards(user.getFullName())).append("'");
            } 
        }
        try{
            prepStatement = conn.prepareStatement(sql.toString().replaceFirst("AND", "WHERE")); 
            resultSet = prepStatement.executeQuery();
            while (resultSet.next())
            {
                    User table_user = new User();
                    table_user.setID(resultSet.getInt("ID"));
                    table_user.setUserName(resultSet.getString("UserName").trim());
                    table_user.setPassword(resultSet.getString("Password").trim());
                    table_user.setEmail(resultSet.getString("Email"));
                    table_user.setFullName(resultSet.getString("FullName").trim());
                    table_user.setBirthday(resultSet.getDate("BirthDay"));
                    table_user.setPhone(resultSet.getString("Phone").trim());
                    table_user.setAddress(resultSet.getString("Address").trim());
                    table_user.setRoleName(resultSet.getString("RoleName").trim());
                    table_user.setAvartarUrl(resultSet.getString("AvatarUrl"));
                    table_user.setGender(resultSet.getString("Gender").trim());
                    
                    list.add(table_user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                if (prepStatement != null) {
                    prepStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return list;
    }

    @Override
    public void Delete_User(User user) {
        try {
            String query = "DELETE FROM [User] WHERE ID=?";
            prepStatement = (PreparedStatement) conn.prepareStatement(query);
            prepStatement.setInt(1, user.getID());
            prepStatement.executeUpdate();
            notification.showMessage("Delete User Successfully.", TypeNotification.Susscess.toString());
        } catch (SQLException throwables) {
            notification.showMessage("Đã có lỗi xảy ra, vui lòng liên hệ đội ngũ hỗ trợ để được hỗ trợ.", TypeNotification.Susscess.toString());
            System.out.println(throwables.getMessage());
        }finally {
            try {
                if (prepStatement != null) {
                    prepStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    @Override
    public boolean Update_User(User user) {
        try {
            String query = "UPDATE [User] SET Email=?,FullName=?,Birthday=?,Phone=?,Address=?,RoleName=?,AvatarUrl=?,Gender=?, WHERE ID=?";
            prepStatement = conn.prepareStatement(query);
            int i= 1;
            prepStatement.setString(i++, user.getEmail().trim());
            prepStatement.setString(i++, user.getFullName().trim());
            prepStatement.setDate(i++, Convert.convertDate(user.getBirthday()));
            prepStatement.setString(i++, user.getPhone());
            prepStatement.setString(i++, user.getAddress().trim());
            prepStatement.setString(i++, user.getRoleName());
            prepStatement.setString(i++, user.getAvartarUrl().trim());
            prepStatement.setString(i++, user.getGender().trim());
            return prepStatement.executeUpdate() > 0;
            //notification.showMessage("Updated Successfully.", TypeNotification.Susscess.toString());
        } catch (SQLException throwables) {
            //notification.showMessage("Đã có lỗi xảy ra, vui lòng liên hệ đội ngũ hỗ trợ để được hỗ trợ.", TypeNotification.Susscess.toString());
            System.out.println(throwables.getMessage());
        }finally {
            try {
                if (prepStatement != null) {
                    prepStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    @Override
    public User LoginUser(User user) {
        String SQL_ADMINISTRATOR_LOGIN = "SELECT * FROM [User] WHERE UserName = ? AND Password = ?";
        /*
         * In order to initialize the main interface though the user's personal information.
         */
        try{
            /*
             * ------------------------------------------------------------------------------
             * 'connection' : Pass SQL statements to objects that manipulate the database	|
             *  From Connection com.YUbuntu.dao.BasicDao.connection 						|
             * ------------------------------------------------------------------------------
             */
            
            prepStatement = conn.prepareStatement(SQL_ADMINISTRATOR_LOGIN);

            prepStatement.setString(1, user.getUserName());
            prepStatement.setString(2, user.getPassword());
            resultSet = prepStatement.executeQuery();

            //Store the user information
            if (resultSet.next())
            {		
                    /*---------------------------------------------------------------------------------------------------------------------------------
                     * Stores the data of Student_ID and .. so that in order to initialize the main interface though the user's personal information. |
                     *---------------------------------------------------------------------------------------------------------------------------------
                     *///Such as it's be used when change user's password !
                    User table_user = new User();
                    table_user.setID(resultSet.getInt("ID"));
                    table_user.setUserName(resultSet.getString("UserName").trim());
                    table_user.setPassword(resultSet.getString("Password").trim());
                    table_user.setEmail(resultSet.getString("Email"));
                    table_user.setFullName(resultSet.getString("FullName").trim());
                    table_user.setBirthday(resultSet.getDate("BirthDay"));
                    table_user.setPhone(resultSet.getString("Phone").trim());
                    table_user.setAddress(resultSet.getString("Address").trim());
                    table_user.setRoleName(resultSet.getString("RoleName").trim());
                    table_user.setAvartarUrl(resultSet.getString("AvatarUrl"));
                    table_user.setGender(resultSet.getString("Gender").trim());
                    //...
                    return table_user;
            }
        } catch (SQLException e)
        {
                System.err.println("ERROR : Fail to check specified information of user from the SQL database !\n");
                System.out.println(e.getMessage());
        }finally {
            try {
                if (prepStatement != null) {
                    prepStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }		
        return null;
    }

    @Override
    public void ChangePassword(User user, String Password) {
        String SQL_ChangePassword = "UPDATE [User] SET Password = ? WHERE ID = ?";
        try {
            //The second step: Change the password of user
            prepStatement = conn.prepareStatement(SQL_ChangePassword);

            //The value passed in : The new password
            prepStatement.setString(1, Password);
            prepStatement.setInt(2, user.getID());

            int result = prepStatement.executeUpdate();
            if (result > 0)
            {
                prepStatement.executeUpdate();
                notification.showMessage("Password has been changed.", TypeNotification.Susscess.toString());
            }
            
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
            notification.showMessage("Warning : the old passwrod is error !", TypeNotification.Error.toString());
        }finally {
            try {
                if (prepStatement != null) {
                    prepStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
