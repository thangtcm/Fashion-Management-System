/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseAccessObject_Impl;

import DatabaseAccessObject_DAO.BillPrescription_Dao;
import DatabaseAccessObject_DAO.MedicalExamination_Dao;
import DatabaseAccessObject_DAO.Staff_Dao;
import Model.Prescription;
import dao.Convert;
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
public class BillPrescription_DaoImpl implements BillPrescription_Dao{
    Connection conn = null;
    PreparedStatement prepStatement= null;
    ResultSet resultSet = null;
    
    public BillPrescription_DaoImpl()
    {
        conn = new DBConnect().getConnection();
    }
    
    @Override
    public ArrayList<Prescription> getBillPrescriptionList(int ID) {
        ArrayList<Prescription> list = new ArrayList<>();
        //Lấy Toàn bộ Products và Categories và Supplyer có liên quan
        String query = "Select * From [BillPrescription] Where MedicalExaminationID = ?";
        
        try{
            //statement = conn.createStatement();  
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, ID);
            resultSet = prepStatement.executeQuery();
            while (resultSet.next())
            {
                    Prescription object = new Prescription();
                    object.setID(resultSet.getInt("ID"));
                    MedicalExamination_Dao medical = new MedicalExamination_DaoImpl();
                    object.setMedicalExamination(medical.getMedicalExamination(ID));
                    
                    Staff_Dao employee = new Staff_DaoImpl();
                    object.setEmployee(employee.getEmployee(ID));
                    
                    object.setCreateTime(resultSet.getDate("CreateTime"));
                    object.setReceivedTime(resultSet.getDate("ReceivedTime"));
                    object.setPrice(resultSet.getDouble("Price"));
                    object.setNote(resultSet.getString("Note"));
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
    public boolean AddBillPrescription(Prescription prescription) {
        String sql = "INSERT INTO [BillPrescription] (MedicalExaminationID, EmployeeID, CreateTime, ReceivedTime, Price, Note, Paid) VALUES (?,?,?,?,?,?,?)";
        
        try{
            prepStatement = conn.prepareStatement(sql);
            int index = 1;
            prepStatement.setInt(index++, prescription.getMedicalExamination().getID());
            prepStatement.setInt(index++, prescription.getEmployee().getID());
            prepStatement.setDate(index++, Convert.convertDate(prescription.getCreateTime()));
            prepStatement.setDate(index++, Convert.convertDate(prescription.getReceivedTime()));
            prepStatement.setDouble(index++, prescription.getPrice());
            prepStatement.setString(index++, prescription.getNote().trim());
            prepStatement.setBoolean(index++, prescription.isPaid());
            return prepStatement.executeUpdate() > 0;
        }
         catch (SQLException e) {
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
    public void Delete_BillPrescription(int ID) {
        try {
            String query = "DELETE FROM [BillPrescription] WHERE ID=?";
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
    public boolean Update_BillPrescription(Prescription prescription) {
        String query = "UPDATE [BillPrescription] SET MedicalExaminationID =?, EmployeeID =?, CreateTime =?, ReceivedTime =?, Price =?, Note =? , Paid =? WHERE ID = ?";
        try{
            prepStatement = conn.prepareStatement(query);
            int index = 1;
            prepStatement.setInt(index++, prescription.getMedicalExamination().getID());
            prepStatement.setInt(index++, prescription.getEmployee().getID());
            prepStatement.setDate(index++, Convert.convertDate(prescription.getCreateTime()));
            prepStatement.setDate(index++, Convert.convertDate(prescription.getReceivedTime()));
            prepStatement.setDouble(index++, prescription.getPrice());
            prepStatement.setString(index++, prescription.getNote().trim());
            prepStatement.setBoolean(index++, prescription.isPaid());
            prepStatement.setInt(index++, prescription.getID());
            
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

    @Override
    public Prescription getBillPrescription(int ID)
    {
        try {
            String query = "SELECT * FROM [BillPrescription] Where ID = ?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, ID);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next())
            {
                Prescription object = new Prescription();
                object.setID(resultSet.getInt("ID"));
                MedicalExamination_Dao medical = new MedicalExamination_DaoImpl();
                object.setMedicalExamination(medical.getMedicalExamination(ID));
                
                Staff_Dao employee = new Staff_DaoImpl();
                object.setEmployee(employee.getEmployee(ID));
                
                object.setCreateTime(resultSet.getDate("CreateTime"));
                object.setReceivedTime(resultSet.getDate("ReceivedTime"));
                object.setPrice(resultSet.getDouble("Price"));
                object.setNote(resultSet.getString("Note"));
                object.setPaid(resultSet.getBoolean("Paid"));
                
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
}
