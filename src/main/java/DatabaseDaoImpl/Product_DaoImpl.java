/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseDaoImpl;

import DatabaseDao.Category_Dao;
import DatabaseDao.ProductImage_Dao;
import DatabaseDao.Product_Dao;
import DatabaseDao.Supply_Dao;
import Enum.TypeNotification;
import Model.Products;
import Sevices.Notification;
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
public class Product_DaoImpl implements Product_Dao{
    Connection conn = null;
    PreparedStatement prepStatement= null;
    Statement statement = null;
    ResultSet resultSet = null;
    
    Notification notification= new Notification();
    public Product_DaoImpl()
    {
        try {
            conn = new DBConnect().getConnection();
            statement = conn.createStatement();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    @Override
    public boolean AddProduct(Products product)
    {
        try
        {
            String query = "INSERT INTO [Products] (ProductName, SupplerID, CategoryID, Description, Status, Sale) VALUES (?,?,?,?,?,?)";

            addFunction(product, query);
            int rowsInserted = prepStatement.executeUpdate();
            if (rowsInserted > 0) {
                notification.showMessage("New Product has been added.", TypeNotification.Susscess.toString());
                return true;
            }
            else {
                notification.showMessage("Failed to add new Product.", TypeNotification.Error.toString());
            }
            
        }
        catch(SQLException e) {
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
        return false;
    }
    
    public void addFunction(Products product, String query) {
        try {
            prepStatement = conn.prepareStatement(query);
            int i = 1;
            prepStatement.setString(i++, product.getProductName().trim());
            prepStatement.setInt(i++, product.getSupplerID().getID());
            prepStatement.setInt(i++, product.getCategoryID().getID());
            prepStatement.setString(i++, product.getDescription().trim());
            prepStatement.setBoolean(i++, product.getStatus());
            prepStatement.setDouble(i++, product.getSale());
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    

    @Override
    public List<Products> getProductList() {
        List<Products> list = new ArrayList<>();
        //Lấy Toàn bộ Products và Categories và Supplyer có liên quan
        String sql = """
                     SELECT p.*, c.*, s.* 
                     FROM [Products] p LEFT JOIN Categories c ON p.CategoryID = c.ID 
                     LEFT JOIN Suppliers s ON p.SupplyerID = s.ID""";
        try{
            prepStatement = conn.prepareStatement(sql);  
            resultSet = prepStatement.executeQuery();
            while (resultSet.next())
            {
                    Products table_product = new Products();
                    table_product.setID(resultSet.getInt("p.ID"));
                    table_product.setProductName(resultSet.getString("ProductName"));
                    
                    //Get all information Category của product
                    Category_Dao category = new Category_DaoImpl();
                    table_product.setCategoryID(category.getCategory(resultSet.getInt("c.ID")));
                    
                    //Get all information Supply của Product
                    Supply_Dao supply =new Supply_DaoImpl();
                    table_product.setSupplerID(supply.getSupply(resultSet.getInt("s.ID")));
                    
                    ProductImage_Dao productImage = new ProductImage_DaoImpl();
                    table_product.setProductImages(productImage.getProductImageList(resultSet.getInt("p.ID")));
                    
                    table_product.setDescription(resultSet.getString("Description"));
                    table_product.setStatus(resultSet.getBoolean("Status"));
                    table_product.setSale(resultSet.getDouble("Sale"));

                    list.add(table_product);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if(prepStatement != null)
                {
                    prepStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return list;
    }

    @Override
    public Products getProducts(int ID)
    {
        try {
            String query = "SELECT * FROM [Products] Where ID = ?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, ID);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next())
            {
                Products product = new Products();
                product.setID(resultSet.getInt("ID"));
                product.setProductName(resultSet.getString("ProductName"));
                
                //Get all information Category của product
                Category_Dao category = new Category_DaoImpl();
                product.setCategoryID(category.getCategory(resultSet.getInt("CategoryID")));

                //Get all information Supply của Product
                Supply_Dao supply =new Supply_DaoImpl();
                product.setSupplerID(supply.getSupply(resultSet.getInt("SupplyerID")));
                
                product.setDescription(resultSet.getString("Description"));
                product.setStatus(resultSet.getBoolean("Status"));
                product.setSale(resultSet.getDouble("Sale"));
                
                return product;
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
    public void Delete_Product(Products product) {
        try {
            String query = "DELETE FROM [Products] WHERE ID=?";
            prepStatement = (PreparedStatement) conn.prepareStatement(query);
            prepStatement.setInt(1, product.getID());
            prepStatement.executeUpdate();
            notification.showMessage("Delete Product Successfully.", TypeNotification.Susscess.toString());
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
    public void Update_Product(Products product) {
        try {
            String query = "UPDATE [Products] SET ProductName=?,SupplyerID=?,CategoryID=?,Description=?,Status=?,Sale=? WHERE ID=?";
            prepStatement = conn.prepareStatement(query);
            int i= 1;
            prepStatement.setString(i++, product.getProductName().trim());
            prepStatement.setInt(i++, product.getSupplerID().getID());
            prepStatement.setInt(i++, product.getCategoryID().getID());
            prepStatement.setString(i++, product.getDescription().trim());
            prepStatement.setBoolean(i++, product.getStatus());
            prepStatement.setDouble(i++, product.getSale());
            prepStatement.setInt(i++, product.getID());
            prepStatement.executeUpdate();
            notification.showMessage("Updated Successfully.", TypeNotification.Susscess.toString());
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
    
}
