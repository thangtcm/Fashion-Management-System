/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseAccessObject_Impl;

import DatabaseAccessObject_DAO.BillService_Dao;
import DatabaseAccessObject_DAO.MedicalExamination_Dao;
import DatabaseAccessObject_DAO.Staff_Dao;
import Model.BillService;
import dao.Convert;
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
public class BillService_DaoImpl implements BillService_Dao{

    Connection conn = null;
    PreparedStatement prepStatement= null;
    ResultSet resultSet = null;
    
    public BillService_DaoImpl()
    {
        conn = new DBConnect().getConnection();
    }
    
    @Override
    public ArrayList<BillService> getBillServiceList(int ID) {
        ArrayList<BillService> list = new ArrayList<>();
        //Lấy Toàn bộ Products và Categories và Supplyer có liên quan
        String query = "Select * From [BillServices] Where MedicalExaminationID = ?";
        
        try{
            //statement = conn.createStatement();  
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, ID);
            resultSet = prepStatement.executeQuery();
            while (resultSet.next())
            {
                    BillService object = new BillService();
                    object.setID(resultSet.getInt("ID"));
                    MedicalExamination_Dao medical = new MedicalExamination_DaoImpl();
                    object.setMedicalExamination(medical.getMedicalExamination(ID));
                    
                    Staff_Dao employee = new Staff_DaoImpl();
                    object.setEmployee(employee.getEmployee(ID));
                    
                    object.setBillDate(resultSet.getDate("BillDate"));
                    object.setPrice(resultSet.getDouble("Price"));
                    object.setPaid(resultSet.getBoolean("Paid"));
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
    public int AddBillService(BillService billService) {
        String sql = "INSERT INTO [BillServices] (MedicalExaminationID, EmployeeID, BillDate) VALUES (?,?,?)";
        
        try{
            prepStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int index = 1;
            prepStatement.setInt(index++, billService.getMedicalExamination().getID());
            prepStatement.setInt(index++, billService.getEmployee().getID());
            prepStatement.setDate(index++, Convert.convertDate(billService.getBillDate()));
            if(prepStatement.executeUpdate() > 0)
            {
                resultSet = prepStatement.getGeneratedKeys();
                if(resultSet.next())
                    return resultSet.getInt(1);
            }
        }
         catch (SQLException e) {
            System.out.println("Failed to add patient Service: " + e.getMessage());
        }finally {
            try {
                if (prepStatement != null) {
                    prepStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }

    @Override
    public void Delete_BillService(int ID) {
        try {
            String query = "DELETE FROM [BillServices] WHERE ID=?";
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
    public boolean Update_BillService(BillService billService) {
        String query = "UPDATE BillServices SET MedicalExaminationID =?, EmployeeID =?, BillDate =?, Price =?, Paid =? WHERE ID = ?";
        try{
            prepStatement = conn.prepareStatement(query);
            int index = 1;
            
            
            prepStatement.setDate(index++, Convert.convertDate(billService.getBillDate()));
            prepStatement.setDouble(index++, billService.getPrice());
            prepStatement.setBoolean(index++, billService.isPaid());
            prepStatement.setInt(index++, billService.getID());
            
            return prepStatement.executeUpdate() > 0;
        } catch (SQLException e)
        {
            System.out.println("Failed to add billService: " + e.getMessage());
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
    
}
