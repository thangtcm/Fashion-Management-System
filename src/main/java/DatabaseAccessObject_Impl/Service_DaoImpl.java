/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseAccessObject_Impl;

import DatabaseAccessObject_DAO.Service_Dao;
import Model.Service;
import Services.StringHandle;
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
public class Service_DaoImpl implements Service_Dao{
    Connection conn = null;
    PreparedStatement prepStatement= null;
    ResultSet resultSet = null;
    
    public Service_DaoImpl()
    {
        conn = new DBConnect().getConnection();
    }

    @Override
    public ArrayList<Service> getServiceList(Service service) {
        ArrayList<Service> list = new ArrayList<>();
        
        StringBuilder sql = new StringBuilder("SELECT * FROM [Service]");

        if(service != null)
        {
            Integer id = service.getID();
            if(id != null)
            {
                sql.append(" AND ID = '").append(service.getID()).append("' ");  
            }
            if(service.getServiceName() != null)
            {
                sql.append(" AND ServiceName LIKE N'%").append(StringHandle.addWildcards(service.getServiceName())).append("%' ");
            }
            
        }
        try{
            prepStatement = conn.prepareStatement(sql.toString().replaceFirst("AND", "WHERE"));
            resultSet = prepStatement.executeQuery();
            while (resultSet.next())
            {
                Service object = new Service();
                object.setID(resultSet.getInt("ID"));
                object.setServiceName(resultSet.getString("ServiceName").trim());
                object.setServiceDescription(resultSet.getString("ServiceDescription").trim());
                object.setServicePrice(resultSet.getDouble("ServicePrice"));

                list.add(object);
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
    public boolean AddService(Service service) {
        String sql = "INSERT INTO [Service] (ServiceName, ServiceDescription, ServicePrice) VALUES (?,?,?)";
        
        try{
            prepStatement = conn.prepareStatement(sql);
            int index = 1;
            prepStatement.setString(index++, service.getServiceName().trim());
            prepStatement.setString(index++, service.getServiceDescription().trim());
            prepStatement.setDouble(index++, service.getServicePrice());
            return prepStatement.executeUpdate() > 0;
        }
         catch (SQLException e) {
            System.out.println("Failed to add drug: " + e.getMessage());
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
    public void Delete_Service(int ID) {
        try {
            String query = "DELETE FROM [Service] WHERE ID=?";
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
    public boolean Update_Service(Service service) {
        String query = "UPDATE [Service] SET ServiceName =?, ServiceDescription =?, ServicePrice=? WHERE ID = ?";
        try{
            prepStatement = conn.prepareStatement(query);
            int index = 1;
            prepStatement.setString(index++, service.getServiceName().trim());
            prepStatement.setString(index++, service.getServiceDescription().trim());
            prepStatement.setDouble(index++, service.getServicePrice());
            prepStatement.setInt(index++, service.getID());
            return prepStatement.executeUpdate() > 0;
        } catch (SQLException e)
        {
            System.out.println("Failed to add patient: " + e.getMessage());
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
    public Service getService(int ID)
    {
        try {
            String query = "SELECT * FROM [Service] Where ID = ?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, ID);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next())
            {
                Service object = new Service();
                object.setID(resultSet.getInt("ID"));
                object.setServiceName(resultSet.getString("ServiceName").trim());
                object.setServiceDescription(resultSet.getString("ServiceDescription").trim());
                object.setServicePrice(resultSet.getDouble("ServicePrice"));
                
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
    public int Count(String where)
    {
        String queryString = "SELECT COUNT(*) FROM [Service]";
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
