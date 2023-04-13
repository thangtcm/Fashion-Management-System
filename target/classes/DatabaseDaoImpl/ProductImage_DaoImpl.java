/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseDaoImpl;

import DatabaseDao.ProductImage_Dao;
import DatabaseDao.Product_Dao;
import Enum.TypeNotification;
import Model.ProductImage;
import static Services.Notification.showMessage;
import dao.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author couni
 */
public class ProductImage_DaoImpl implements ProductImage_Dao{
    Connection conn = null;
    PreparedStatement prepStatement= null;
    ResultSet resultSet = null;
    
    public ProductImage_DaoImpl()
    {
        conn = new DBConnect().getConnection();
    }

    @Override
    public ArrayList<ProductImage> getProductImageList(int ID) {
        ArrayList<ProductImage> list = new ArrayList<>();
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
            return (rowsInserted > 0) ;

        }
        catch(SQLException e) {
            System.out.println("Lỗi add Img "  + e.getMessage());
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
            prepStatement.setInt(i++, productImage.getProduct().getID());
            prepStatement.setString(i++, productImage.getImageUrl().trim());
        } catch (SQLException e) {
            System.out.println("Lỗi add Img "  + e.getMessage());
        }
    }

    @Override
    public void Delete_ProductImage(ProductImage productImage) {
        try {
            String query = "DELETE FROM [ProductImage] WHERE ID=?";
            prepStatement = (PreparedStatement) conn.prepareStatement(query);
            prepStatement.setInt(1, productImage.getID());
            prepStatement.executeUpdate();
            showMessage("Delete Product Successfully.", TypeNotification.Success);
        } catch (SQLException throwables) {
            showMessage("Đã có lỗi xảy ra, vui lòng liên hệ đội ngũ hỗ trợ để được hỗ trợ.", TypeNotification.Success);
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
