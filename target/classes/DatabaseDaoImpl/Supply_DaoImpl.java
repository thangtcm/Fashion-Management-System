/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseDaoImpl;

import DatabaseDao.Supply_Dao;
import Enum.TypeNotification;
import Model.Suppliers;
import Services.Notification;
import static Services.Notification.showMessage;
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
public class Supply_DaoImpl implements Supply_Dao{

    Connection conn = null;
    PreparedStatement prepStatement= null;
    Statement statement = null;
    ResultSet resultSet = null;
    
    public Supply_DaoImpl()
    {
        try {
            conn = new DBConnect().getConnection();
            statement = conn.createStatement();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
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
    public List<Suppliers> getSupplyList() {
        List<Suppliers> list = new ArrayList<>();
        //Lấy Toàn bộ Products và Categories và Supplyer có liên quan
        String sql = "SELECT * FROM [Suppliers]";
        try{
            prepStatement = conn.prepareStatement(sql);  
            resultSet = prepStatement.executeQuery();
            while (resultSet.next())
            {
                    Suppliers table_supply = new Suppliers();
                    table_supply.setID(resultSet.getInt("ID"));
                    table_supply.setCompayName(resultSet.getString("CompanyName"));
                    table_supply.setAddress(resultSet.getString("Address"));
                    table_supply.setPhone(resultSet.getString("Phone"));

                    list.add(table_supply);
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
    public boolean AddSupply(Suppliers supply) {
        try
        {
            String query = "INSERT INTO [Suppliers] (CompanyName, Address, Phone) VALUES (?,?,?)";
           
            addFunction(supply, query);
            int rowsInserted = prepStatement.executeUpdate();
            if (rowsInserted > 0) {
                showMessage("New Supply has been added.", TypeNotification.Success);
                return true;
            }else {
                showMessage("Failed to add new Supply.", TypeNotification.Error);
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
    
    public void addFunction(Suppliers supply, String query) {
        try {
            prepStatement = conn.prepareStatement(query);
            int i = 1;
            prepStatement.setString(i++, supply.getCompayName().trim());
            prepStatement.setString(i++, supply.getAddress().trim());
            prepStatement.setString(i++, supply.getPhone().trim());
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void Delete_Supply(Suppliers supply) {
        try {
            String query = "DELETE FROM [Suppliers] WHERE ID=?";
            prepStatement = (PreparedStatement) conn.prepareStatement(query);
            prepStatement.setInt(1, supply.getID());
            prepStatement.executeUpdate();
            showMessage("Delete User Successfully.", TypeNotification.Success);
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
    public Suppliers getSupply(int ID) {
        try {
            String query = "SELECT * FROM [Suppliers] Where ID = ?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, ID);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next())
            {
                Suppliers suppliers = new Suppliers();
                suppliers.setID(resultSet.getInt("ID"));
                suppliers.setCompayName(resultSet.getString("CompayName"));
                suppliers.setAddress(resultSet.getString("Address"));
                suppliers.setPhone(resultSet.getString("Phone"));
                return suppliers;
            }
        } catch (SQLException e) {
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
        return null;
    }

    @Override
    public void Update_Supply(Suppliers supply) {
        try {
            String query = "UPDATE [Products] SET CompayName=?,Address=?,Phone=? WHERE ID=?";
            prepStatement = conn.prepareStatement(query);
            int i= 1;
            prepStatement.setString(i++, supply.getCompayName().trim());
            prepStatement.setString(i++, supply.getAddress().trim());
            prepStatement.setString(i++, supply.getPhone().trim());
            prepStatement.setInt(i, supply.getID());
            prepStatement.executeUpdate();
            showMessage("Updated Successfully.", TypeNotification.Success);
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
    
}
