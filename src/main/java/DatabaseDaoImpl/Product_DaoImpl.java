/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseDaoImpl;

import DatabaseDao.Product_Dao;
import Enum.TypeNotification;
import Model.Products;
import static Services.Notification.showMessage;
import Services.StringHandle;
import dao.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author couni
 */
public class Product_DaoImpl implements Product_Dao{
    Connection conn = null;
    PreparedStatement prepStatement= null;
    ResultSet resultSet = null;

    public Product_DaoImpl()
    {
        conn = new DBConnect().getConnection();
    }
    @Override
    public int AddProduct(Products product)
    {
        try
        {
            String query = "INSERT INTO [Products] (ProductName, CategoryID, Description, Status, Sale) VALUES (?,?,?,?,?)";
            addFunction(product, query);
            int rowsInserted = prepStatement.executeUpdate();
            if (rowsInserted > 0) {
                //showMessage("New Product has been added.", TypeNotification.Success);
                resultSet = prepStatement.getGeneratedKeys();
                if(resultSet.next())
                    return resultSet.getInt(1);
            }
        }
        catch(SQLException e) {
            System.out.println("Lỗi ở đây " + e.getMessage()); 
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
        return -1;
    }
    
    public void addFunction(Products product, String query) {
        try {
            prepStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            prepStatement.setString(i++, product.getProductName().trim());
//            prepStatement.setInt(i++, product.getSupplerID().getID());
            prepStatement.setInt(i++, product.getCategoryID());
            prepStatement.setString(i++, product.getDescription().trim());
            prepStatement.setBoolean(i++, product.getStatus());
            prepStatement.setDouble(i++, product.getSale());
        } catch (SQLException e) {
            System.out.println("Lỗi ở đây " + e.getMessage());
        }
    }
    

    @Override
    public ArrayList<Products> getProductList(Products products) {
        ArrayList<Products> list = new ArrayList<>();
        //Lấy Toàn bộ Products và Categories và Supplyer có liên quan
        StringBuilder sql = new StringBuilder("SELECT * FROM Products");
        if(products != null)
        {
            if(products.getProductName()!= null)
            {
                sql.append(" AND ProductName LIKE N'").append(StringHandle.addWildcards(products.getProductName())).append("'");
            }
            if(products.getCategoryID() != null)
            {
                sql.append(" AND CategoryID = '").append(products.getCategoryID()).append("'");
            }
        }
        String query = "SELECT SUM(pv.Stock) FROM Products p "
                + "LEFT JOIN ProductVariants pv ON pv.ProductID = p.ID "
                + "WHERE p.ID = ?";
        try{
            PreparedStatement preStatementStock = conn.prepareStatement(query);
            
            ResultSet resultSetStock;
            int ProductsStock = 0;
            
            prepStatement = conn.prepareStatement(sql.toString().replaceFirst("AND", "WHERE"));  
            resultSet = prepStatement.executeQuery();
            while (resultSet.next())
            {
                    Products table_product = new Products();
                    table_product.setID(resultSet.getInt("ID"));
                    table_product.setProductName(resultSet.getString("ProductName"));
                    
                    table_product.setCategoryID(resultSet.getInt("CategoryID"));
                   
                    preStatementStock.setInt(1, resultSet.getInt("ID"));
                    resultSetStock = preStatementStock.executeQuery();
                    if(resultSetStock.next())
                        ProductsStock = resultSetStock.getInt(1);
                    table_product.setStock(ProductsStock);
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
                
                product.setCategoryID(resultSet.getInt("CategoryID"));
                
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
    public void Delete_Product(int ID) {
        try {
            String query = "DELETE FROM [Products] WHERE ID=?";
            prepStatement = (PreparedStatement) conn.prepareStatement(query);
            prepStatement.setInt(1, ID);
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
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void Update_Product(Products product) {
        try {
            String query = "UPDATE [Products] SET ProductName=?,CategoryID=?,Description=?,Status=?,Sale=? WHERE ID=?";
            prepStatement = conn.prepareStatement(query);
            int i= 1;
            prepStatement.setString(i++, product.getProductName().trim());
            prepStatement.setInt(i++, product.getCategoryID());
            prepStatement.setString(i++, product.getDescription().trim());
            prepStatement.setBoolean(i++, product.getStatus());
            prepStatement.setDouble(i++, product.getSale());
            prepStatement.setInt(i++, product.getID());
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
