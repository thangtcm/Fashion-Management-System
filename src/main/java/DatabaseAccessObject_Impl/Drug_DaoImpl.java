/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseAccessObject_Impl;

import DatabaseAccessObject_DAO.Drug_Dao;
import Model.Drug;
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
public class Drug_DaoImpl implements Drug_Dao{
    Connection conn = null;
    PreparedStatement prepStatement= null;
    ResultSet resultSet = null;
    
    public Drug_DaoImpl()
    {
        conn = new DBConnect().getConnection();
    }

    @Override
    public ArrayList<Drug> getDrugList(Drug drug) {
        ArrayList<Drug> list = new ArrayList<>();
        
        StringBuilder sql = new StringBuilder("SELECT * FROM [Drugs]");

        if(drug != null)
        {
            Integer id = drug.getID();
            if(id != null)
            {
                sql.append(" AND ID LIKE '%").append(drug.getID()).append("%' ");  
            }
            if(drug.getDrugName() != null)
            {
                sql.append(" AND DrugName LIKE N'").append(StringHandle.addWildcards(drug.getDrugName())).append("' ");
            }
            
        }
        try{
            prepStatement = conn.prepareStatement(sql.toString().replaceFirst("AND", "WHERE"));
            resultSet = prepStatement.executeQuery();
            while (resultSet.next())
            {
                Drug table_Drug = new Drug();
                table_Drug.setID(resultSet.getInt("ID"));
                table_Drug.setDrugName(resultSet.getString("DrugName").trim());
                table_Drug.setDrugType(resultSet.getString("DrugType").trim());
                table_Drug.setQuantity(resultSet.getInt("Quantity"));
                table_Drug.setPrice(resultSet.getDouble("DrugPrice"));
                table_Drug.setDescription(resultSet.getString("Description"));

                list.add(table_Drug);
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
    public boolean AddDrug(Drug drug) {
        String sql = "INSERT INTO [Drugs] (DrugName, DrugType, Quantity, DrugPrice, Description) VALUES (?,?,?,?,?,?,?,?)";
        
        try{
            prepStatement = conn.prepareStatement(sql);
            int index = 1;
            prepStatement.setString(index++, drug.getDrugName().trim());
            prepStatement.setString(index++, drug.getDrugType().trim());
            prepStatement.setInt(index++, drug.getQuantity());
            prepStatement.setDouble(index++, drug.getPrice());
            prepStatement.setString(index++, drug.getDescription().trim());
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
    public void Delete_Drug(int ID) {
        try {
            String query = "DELETE FROM [Drugs] WHERE ID=?";
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
    public boolean Update_Drug(Drug drug) {
        String query = "UPDATE [Drugs] SET DrugName = ?, DrugType = ?, Quantity = ?, DrugPrice = ?, Description =? WHERE ID = ?";
        try{
            prepStatement = conn.prepareStatement(query);
            int index = 1;
            prepStatement.setString(index++, drug.getDrugName().trim());
            prepStatement.setString(index++, drug.getDrugType().trim());
            prepStatement.setInt(index++, drug.getQuantity());
            prepStatement.setDouble(index++, drug.getPrice());
            prepStatement.setString(index++, drug.getDescription().trim());
            prepStatement.setInt(index++, drug.getID());
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
    public Drug getDrug(int ID)
    {
        try {
            String query = "SELECT * FROM [Drugs] Where ID = ?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, ID);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next())
            {
                Drug table_Drug = new Drug();
                table_Drug.setID(resultSet.getInt("ID"));
                table_Drug.setDrugName(resultSet.getString("DrugName").trim());
                table_Drug.setDrugType(resultSet.getString("DrugType").trim());
                table_Drug.setQuantity(resultSet.getInt("Quantity"));
                table_Drug.setPrice(resultSet.getDouble("DrugPrice"));
                table_Drug.setDescription(resultSet.getString("Description"));
                
                return table_Drug;
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
        String queryString = "SELECT COUNT(*) FROM [Drugs]";
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
