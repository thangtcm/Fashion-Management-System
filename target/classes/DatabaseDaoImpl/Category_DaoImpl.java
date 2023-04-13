/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseDaoImpl;

import DatabaseDao.Category_Dao;
import Enum.TypeNotification;
import Model.Categories;
import static Services.Notification.showMessage;
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
public class Category_DaoImpl implements Category_Dao{

    Connection conn = null;
    PreparedStatement prepStatement= null;
    //Statement statement = null;
    ResultSet resultSet = null;
    
    public Category_DaoImpl()
    {
        conn = new DBConnect().getConnection();
            //statement = conn.createStatement();
    }
    @Override
    public boolean AddCategory(Categories category) {
        try
        {
            String query = "INSERT INTO [Categories] (CategoryName, Description) VALUES (?,?)";

            addFunction(category, query);
            int rowsInserted = prepStatement.executeUpdate();
            if (rowsInserted > 0) {
                showMessage("New Category has been added.", TypeNotification.Success);
                return true;
            }
            
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            showMessage("Đã có lỗi xảy ra", TypeNotification.Error);
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
    
    public void addFunction(Categories category, String query) {
        try {
            prepStatement = conn.prepareStatement(query);
            int i = 1;
            prepStatement.setString(i++, category.getCategoriesName().trim());
            prepStatement.setString(i++, category.getDescription().trim());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Categories> getCategoryList() {
        ArrayList<Categories> list = new ArrayList<>();
        //Lấy Toàn bộ Products và Categories và Supplyer có liên quan
        String sql = "SELECT * FROM [Categories]";
        try{
            prepStatement = conn.prepareStatement(sql);  
            resultSet = prepStatement.executeQuery();
            while (resultSet.next())
            {
                    Categories table_category = new Categories();
                    table_category.setID(resultSet.getInt("ID"));
                    table_category.setCategoriesName(resultSet.getString("CategoryName"));
                    table_category.setDescription(resultSet.getString("Description"));

                    list.add(table_category);
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
    public void Delete_Category(Categories category) {
        try {
            String query = "DELETE FROM [Categories] WHERE ID=?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, category.getID());
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
    public Categories getCategory(int ID) {
        try {
            String query = "Select * From [Categories] Where ID = ?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, ID);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next())
            {
                Categories category = new Categories();
                category.setID(resultSet.getInt("ID"));
                category.setCategoriesName(resultSet.getString("CategoryName"));
                category.setDescription(resultSet.getString("Description"));
                return category;
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
    public void Update_Category(Categories category) {
        try {
            String query = "UPDATE [Categories] SET CategoryName=?,Description=? WHERE ID=?";
            prepStatement = conn.prepareStatement(query);
            int i= 1;
            prepStatement.setString(i++, category.getCategoriesName().trim());
            prepStatement.setString(i++, category.getDescription().trim());
            prepStatement.setInt(i, category.getID());
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
