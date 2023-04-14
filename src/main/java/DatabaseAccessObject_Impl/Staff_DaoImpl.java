/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseAccessObject_Impl;

import DatabaseAccessObject_DAO.Staff_Dao;
import Model.Employee;
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
public class Staff_DaoImpl implements Staff_Dao{
    
    Connection conn = null;
    PreparedStatement prepStatement= null;
    ResultSet resultSet = null;
    
    public Staff_DaoImpl()
    {
        conn = new DBConnect().getConnection();
    }
    
    @Override
    public boolean AddStaff(Employee staff)
    {
        String SQL_AddStaff = "INSERT INTO [Employee] (UserName, Password, FirstName, MiddleName, LastName, Birthday, Gender, Address, NumberPhone, Email, RoleName) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        
        try{
            prepStatement = conn.prepareStatement(SQL_AddStaff);
            int i = 1;
            prepStatement.setString(i++, staff.getUserName().trim());
            prepStatement.setString(i++, staff.getPassword().trim());
            prepStatement.setString(i++, staff.getFirstName().trim());
            prepStatement.setString(i++, staff.getMiddleName().trim());
            prepStatement.setString(i++, staff.getLastName().trim());
            prepStatement.setDate(i++, Convert.convertDate(staff.getBirthday()));
            prepStatement.setString(i++, staff.getGender().trim());     
            prepStatement.setString(i++, staff.getAddress().trim());
            prepStatement.setString(i++, staff.getNumberPhone().trim());
            prepStatement.setString(i++, staff.getEmail().trim());
            prepStatement.setString(i++, staff.getRoleName().trim());
            return prepStatement.executeUpdate() > 0;
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

    @Override
    public ArrayList<Employee> getStaffList(Employee staff) {
        ArrayList<Employee> list = new ArrayList<>();
        /*
         * Default: displays all student information
         */
        StringBuilder sql = new StringBuilder("SELECT * FROM [Employee]");

        /*------------------------------------------------------------------------
         * getStudent_name() : Get it from the information entered by the user.  |
         * -----------------------------------------------------------------------
         */
        if(staff != null)
        {
            Integer id = staff.getID();
            if(id != null)
            {
                sql.append(" AND ID LIKE '%").append(staff.getID()).append("%' ");  
            }

            if(staff.getFullName() != null)
            {
                sql.append(" AND CONCAT_WS(' ', FirstName, MiddleName, LastName) LIKE N'").append(StringHandle.addWildcards(staff.getFullName())).append("'");
            } 
        }

        try{
            prepStatement = conn.prepareStatement(sql.toString().replaceFirst("AND", "WHERE"));
            resultSet = prepStatement.executeQuery();
            while (resultSet.next())
            {
                Employee table_staff = new Employee();
                table_staff.setID(resultSet.getInt("ID"));
                table_staff.setUserName(resultSet.getString("UserName").trim());
                table_staff.setPassword(resultSet.getString("Password").trim());
                table_staff.setFirstName(resultSet.getString("FirstName").trim());
                table_staff.setMiddleName(resultSet.getString("MiddleName").trim());
                table_staff.setLastName(resultSet.getString("LastName").trim());
                table_staff.setBirthday(resultSet.getDate("BirthDay"));
                table_staff.setGender(resultSet.getString("Gender").trim());
                table_staff.setAddress(resultSet.getString("Address").trim());
                table_staff.setNumberPhone(resultSet.getString("NumberPhone"));
                table_staff.setEmail(resultSet.getString("Email").trim());
                table_staff.setRoleName(resultSet.getString("RoleName").trim());

                list.add(table_staff);
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
    public void Delete_Staff(int ID) {
        try {
            String query = "DELETE FROM [Employee] WHERE ID=?";
            prepStatement = (PreparedStatement) conn.prepareStatement(query);
            prepStatement.setInt(1, ID);
            prepStatement.executeUpdate();
        } catch (SQLException throwables) {
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
    public Employee getNameEmployee(int ID){
        try {
            String query = "SELECT * FROM [Employee] Where ID = ?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, ID);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next())
            {
                Employee object = new Employee();
                object.setID(resultSet.getInt("ID"));
                object.setFirstName(resultSet.getString("FirstName").trim());
                object.setMiddleName(resultSet.getString("MiddleName").trim());
                object.setLastName(resultSet.getString("LastName").trim());
                return object;
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

    @Override
    public boolean Update_Staff(Employee staff) {
        try{
            String query = "Update [Employee] SET UserName =?, Password =?, FirstName =?, MiddleName =?, LastName =?, Birthday =?, Gender =?, Address =?, NumberPhone =?, Email =?, RoleName =? WHERE ID = ?";
            prepStatement = conn.prepareStatement(query);
            int i= 1;
            prepStatement.setString(i++, staff.getUserName().trim());
            prepStatement.setString(i++, staff.getPassword().trim());
            prepStatement.setString(i++, staff.getFirstName().trim());
            prepStatement.setString(i++, staff.getMiddleName().trim());
            prepStatement.setString(i++, staff.getLastName().trim());
            prepStatement.setDate(i++, Convert.convertDate(staff.getBirthday()));
            prepStatement.setString(i++, staff.getGender().trim());     
            prepStatement.setString(i++, staff.getAddress().trim());
            prepStatement.setString(i++, staff.getNumberPhone().trim());
            prepStatement.setString(i++, staff.getEmail().trim());
            prepStatement.setString(i++, staff.getRoleName().trim());
            prepStatement.setInt(i++, staff.getID());
            return prepStatement.executeUpdate() > 0;
        }catch(SQLException e)
        {  
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

    @Override
    public Employee Login_Staff(Employee staff) {
        String SQL_ADMINISTRATOR_LOGIN = "SELECT * FROM [Employee] WHERE UserName = ? AND Password = ?";
        /*
         * In order to initialize the main interface though the user's personal information.
         */
        Employee table_Staff_temp = null;
        try{
            prepStatement = conn.prepareStatement(SQL_ADMINISTRATOR_LOGIN);
            prepStatement.setString(1, staff.getUserName());
            prepStatement.setString(2, staff.getPassword());
            resultSet = prepStatement.executeQuery();
            //Store the user information
            if (resultSet.next())
            {		
                table_Staff_temp = new Employee();
                table_Staff_temp.setID(resultSet.getInt("ID"));
                table_Staff_temp.setUserName(resultSet.getString("UserName").trim());
                table_Staff_temp.setPassword(resultSet.getString("Password").trim());
                table_Staff_temp.setFirstName(resultSet.getString("FirstName").trim());
                table_Staff_temp.setMiddleName(resultSet.getString("MiddleName").trim());
                table_Staff_temp.setLastName(resultSet.getString("LastName").trim());
                table_Staff_temp.setBirthday(resultSet.getDate("BirthDay"));
                table_Staff_temp.setGender(resultSet.getString("Gender").trim());
                table_Staff_temp.setAddress(resultSet.getString("Address").trim());
                table_Staff_temp.setNumberPhone(resultSet.getString("NumberPhone"));
                table_Staff_temp.setEmail(resultSet.getString("Email").trim());
                table_Staff_temp.setRoleName(resultSet.getString("RoleName"));
            }
        } catch (SQLException e)
        {
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
        return table_Staff_temp;	
    }

    @Override
    public Employee getEmployee(int ID)
    {
        try {
            String query = "SELECT * FROM [Employee] Where ID = ?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, ID);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next())
            {
                Employee object = new Employee();
                object.setID(resultSet.getInt("ID"));
                object.setUserName(resultSet.getString("UserName").trim());
                object.setPassword(resultSet.getString("Password").trim());
                object.setFirstName(resultSet.getString("FirstName").trim());
                object.setMiddleName(resultSet.getString("MiddleName").trim());
                object.setLastName(resultSet.getString("LastName").trim());
                object.setBirthday(resultSet.getDate("BirthDay"));
                object.setGender(resultSet.getString("Gender").trim());
                object.setAddress(resultSet.getString("Address").trim());
                object.setNumberPhone(resultSet.getString("NumberPhone"));
                object.setEmail(resultSet.getString("Email").trim());
                object.setNumberPhone(resultSet.getString("RoleName"));
                
                return object;
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
    
    @Override
    public void ChangePassword(Employee staff, String Password) {
        String SQL_ChangePassword = "UPDATE [Employee] SET Password = ? WHERE ID = ?";
        try {
            //The second step: Change the password of user
            prepStatement = conn.prepareStatement(SQL_ChangePassword);

            //The value passed in : The new password
            prepStatement.setString(1, Password);
            prepStatement.setInt(2, staff.getID());

            int result = prepStatement.executeUpdate();
            if (result > 0)
            {
                prepStatement.executeUpdate();
            }
            
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
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
    public int Count(String where)
    {
        String queryString = "SELECT COUNT(*) FROM [Employee]";
        if(where != null || !"".equals(where))
        {
            queryString += " WHERE " + where;
        }
        try {
            prepStatement = conn.prepareStatement(queryString);
            resultSet = prepStatement.executeQuery();
            return resultSet.getInt(1);
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }finally {
            try {
                if (prepStatement != null) {
                    prepStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return 0;
    }
}
