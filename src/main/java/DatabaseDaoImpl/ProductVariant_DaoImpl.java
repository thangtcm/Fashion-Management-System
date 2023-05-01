/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseDaoImpl;

import DatabaseDao.ProductVariant_Dao;
import DatabaseDao.Product_Dao;
import Enum.TypeNotification;
import Model.ProductVariants;
import Services.Notification;
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
public class ProductVariant_DaoImpl implements ProductVariant_Dao{

    Connection conn = null;
    PreparedStatement prepStatement= null;
    ResultSet resultSet = null;
    
    public ProductVariant_DaoImpl()
    {
        conn = new DBConnect().getConnection();
    }
    
    @Override
    public ArrayList<ProductVariants> getProductVariantList(int ID) {
        ArrayList<ProductVariants> list = new ArrayList<>();
        //Lấy Toàn bộ Products và Categories và Supplyer có liên quan
        String query = "Select * From [ProductVariants] Where ProductID = ?";
        
        try{
            //statement = conn.createStatement();  
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, ID);
            resultSet = prepStatement.executeQuery();
            while (resultSet.next())
            {
                    ProductVariants table_productVariant = new ProductVariants();
                    table_productVariant.setID(resultSet.getInt("ID"));
                    Product_Dao product = new Product_DaoImpl();
                    table_productVariant.setProduct(product.getProducts(ID));
                    table_productVariant.setPrice(resultSet.getDouble("Price"));
                    table_productVariant.setSize(resultSet.getString("Size"));
                    table_productVariant.setStock(resultSet.getInt("Stock"));
                    list.add(table_productVariant);
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
    public boolean AddProductVariant(ProductVariants productVariant) {
        try
        {
            String query = "INSERT INTO [ProductVariants] (ProductID, Size, Price, Stock) VALUES (?,?,?,?)";
            addFunction(productVariant, query);
            int rowsInserted = prepStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("New ProductVariants has been added.");
                //notification.showMessage("New ProductImage has been added.", TypeNotification.Success.toString());
                return true;
            }
            
        }
        catch(SQLException e) {
            System.out.println("Product Variant " + e.getMessage()); 
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
    
    public void addFunction(ProductVariants productVariant, String query) {
        try {
            prepStatement = conn.prepareStatement(query);
            int i = 1;
            //prepStatement.setInt(i++, productVariant.getProduct().getID());
            prepStatement.setInt(i++, productVariant.getProduct().getID());
            prepStatement.setString(i++, productVariant.getSize().trim());
            prepStatement.setDouble(i++, productVariant.getPrice());
            prepStatement.setInt(i++, productVariant.getStock());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void Delete_ProductVariant(ProductVariants productVariant) {
        try {
            String query = "DELETE FROM [ProductVariants] WHERE ID=?";
            prepStatement = (PreparedStatement) conn.prepareStatement(query);
            prepStatement.setInt(1, productVariant.getID());
            prepStatement.executeUpdate();
            showMessage("Delete ProductVariants Successfully.", TypeNotification.Success);
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
    public ProductVariants getProductVariant(int ID) {
        try {
            String query = "Select * From [ProductVariants] Where ID = ?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, ID);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next())
            {
                ProductVariants productVariants = new ProductVariants();
                productVariants.setID(resultSet.getInt("ID"));
                
                Product_Dao productDao = new Product_DaoImpl();
                productVariants.setProduct(productDao.getProducts(resultSet.getInt("ProductID")));
                
                productVariants.setSize(resultSet.getString("Size"));
                productVariants.setPrice(resultSet.getDouble("Price"));
                productVariants.setStock(resultSet.getInt("Stock"));
                
                return productVariants;
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
    public void Update_Category(ProductVariants productVariant) {
        try {
            String query = "UPDATE [Products] SET ProductID=?, Size=?, Price=?, Stock=? WHERE ID=?";
            prepStatement = conn.prepareStatement(query);
            addFunction(productVariant, query);
            prepStatement.setInt(5, productVariant.getID());
            prepStatement.executeUpdate();
            Notification.showMessage("Updated Successfully.", TypeNotification.Success);
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
