/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseDaoImpl;

import DatabaseDao.User_Dao;
import Enum.TypeNotification;
import Enum.TypeRoleName;
import Model.User;
import static Services.Notification.showMessage;
import Services.StringHandle;
import dao.Convert;
import dao.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author couni
 */
public class User_DaoImpl implements User_Dao{
 
    Connection conn = null;
    PreparedStatement prepStatement= null;
    ResultSet resultSet = null;
    
    public User_DaoImpl()
    {
        conn = new DBConnect().getConnection();
    }
    
    @Override
    public boolean AddUser(User user)
    {
        try
        {
            String query = "INSERT INTO [User] ([UserName], Password, Email, FullName, Birthday, Phone, Address, RoleName, AvatarUrl, Gender) VALUES (?,?,?,?,?,?,?,?,?,?)";
            addFunction(user, query);
            int rowsInserted = prepStatement.executeUpdate(); 
            return rowsInserted > 0;
        }
        catch(SQLException e) {
            if(e.getErrorCode() == 2601) // Unique constraint violation error number
            {
                showMessage("Tên tài khoản đã tồn tại", TypeNotification.Error);
            }
            else
            {
                showMessage("Đăng ký tài khoản thất bại", TypeNotification.Error);
                System.out.println("DatabaseDaoImpl.User_DaoImpl.AddUser() " + e.getMessage());
            }
            return false;
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
   

    @Override
    public ArrayList<User> getUserList(User user) {
        ArrayList<User> list = new ArrayList<>();
        list.clear();
        StringBuilder sql = new StringBuilder("SELECT * FROM [User]");
        if(user != null)
        {
            if(user.getFullName() != null)
            {
                sql.append(" AND FullName LIKE N'").append(StringHandle.addWildcards(user.getFullName())).append("'");
            }
            if(!user.getRoleName().trim().equals(TypeRoleName.None.toString()))
            {
                sql.append(" AND RoleName LIKE N'").append(StringHandle.addWildcards(user.getRoleName())).append("'");
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
    public void Delete_User(int Id) {
        try {
            String query = "DELETE FROM [User] WHERE ID=?";
            prepStatement = (PreparedStatement) conn.prepareStatement(query);
            prepStatement.setInt(1, Id);
            prepStatement.executeUpdate();
            showMessage("Delete User Successfully.", TypeNotification.Success);
        } catch (SQLException throwables) {
            showMessage("Đã có lỗi xảy ra, vui lòng liên hệ đội ngũ hỗ trợ để được hỗ trợ.", TypeNotification.Success);
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
    public void Update_User(User user) {
        try {
            String query = "UPDATE [User] SET Email=?,FullName=?,Birthday=?,Phone=?,Address=?,RoleName=?,AvatarUrl=?,Gender=? WHERE ID = ?";
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
            prepStatement.setInt(i++, user.getID());
            prepStatement.executeUpdate();
            showMessage("Updated Information Successfully.", TypeNotification.Success);
        } catch (SQLException throwables) {
            showMessage("Đã có lỗi xảy ra, vui lòng liên hệ đội ngũ hỗ trợ để được hỗ trợ.", TypeNotification.Error);
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
    public User LoginUser(User user) {
        String SQL_ADMINISTRATOR_LOGIN = "SELECT * FROM [User] WHERE UserName = ? AND Password = ?";
        try{
            prepStatement = conn.prepareStatement(SQL_ADMINISTRATOR_LOGIN);

            prepStatement.setString(1, user.getUserName());
            prepStatement.setString(2, user.getPassword());
            resultSet = prepStatement.executeQuery();
            if (resultSet.next())
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
            prepStatement = conn.prepareStatement(SQL_ChangePassword);
            prepStatement.setString(1, Password);
            prepStatement.setInt(2, user.getID());
            int result = prepStatement.executeUpdate();
            if (result > 0)
            {
                prepStatement.executeUpdate();
                showMessage("Password has been changed.", TypeNotification.Success);
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
            showMessage("Warning : the old passwrod is error !", TypeNotification.Error);
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
    public User getUser(int Id)
    {
        try {
            String query = "SELECT * FROM [User] Where ID = ?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, Id);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next())
            {
                User table_user = new User();
                table_user.setID(resultSet.getInt("ID"));
                table_user.setEmail(resultSet.getString("Email"));
                table_user.setFullName(resultSet.getString("FullName").trim());
                table_user.setBirthday(resultSet.getDate("BirthDay"));
                table_user.setPhone(resultSet.getString("Phone").trim());
                table_user.setAddress(resultSet.getString("Address").trim());
                table_user.setRoleName(resultSet.getString("RoleName").trim());
                table_user.setAvartarUrl(resultSet.getString("AvatarUrl"));
                table_user.setGender(resultSet.getString("Gender").trim());
                return table_user;
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
        return null;
    }
}
