/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseDaoImpl;

import DatabaseDao.ProductImage_Dao;
import DatabaseDao.Product_Dao;
import Enum.TypeNotification;
import Model.ProductImage;
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
public class ProductImage_DaoImpl implements ProductImage_Dao{
    Connection conn = null;
    PreparedStatement prepStatement= null;
    Statement statement = null;
    ResultSet resultSet = null;
    
    Notification notification= new Notification();
    public ProductImage_DaoImpl()
    {
        try {
            conn = new DBConnect().getConnection();
            statement = conn.createStatement();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<ProductImage> getProductImageList(int ID) {
        List<ProductImage> list = new ArrayList<>();
        //Lấy Toàn bộ Products và Categories và Supplyer có liên quan
        
        try{
            String query = "SELECT * FROM [ProductImage] Where ProductID = ?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, ID);
            resultSet = prepStatement.executeQuery();
            while (resultSet.next())
            {
                    ProductImage table_img = new ProductImage();
                    table_img.setID(resultSet.getInt("ID"));
                    //
                    Product_Dao product = new Product_DaoImpl();
                    table_img.setProduct(product.getProducts(ID));
                    
                    table_img.setImageUrl(resultSet.getString("ImageUrl"));

                    list.add(table_img);
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
    public boolean AddProductImage(ProductImage productImage) {
        try
        {
            String query = "INSERT INTO [ProductImage] (ProductID, ImageUrl) VALUES (?,?)";
            addFunction(productImage, query);
            int rowsInserted = prepStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("New ProductImage has been added.");
                //notification.showMessage("New ProductImage has been added.", TypeNotification.Susscess.toString());
                return true;
            }else {
                notification.showMessage("Failed to add new ProductImage.", TypeNotification.Error.toString());
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
    
    public void addFunction(ProductImage productImage, String query) {
        try {
            prepStatement = conn.prepareStatement(query);
            int i = 1;
            //prepStatement.setInt(i++, productImage.getProduct().getID());
            prepStatement.setString(i++, productImage.getImageUrl().trim());
            prepStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void Delete_ProductImage(ProductImage productImage) {
        try {
            String query = "DELETE FROM [ProductImage] WHERE ID=?";
            prepStatement = (PreparedStatement) conn.prepareStatement(query);
            prepStatement.setInt(1, productImage.getID());
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
                if (resultSet != null) {
                    resultSet.close();
                }
                } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
