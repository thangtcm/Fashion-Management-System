package DatabaseAccessObject_Impl;

import DatabaseAccessObject_DAO.BillPrescription_Dao;
import DatabaseAccessObject_DAO.PrescriptionDetail_Dao;
import Model.PrescriptionDetail;
import dao.Convert;
import dao.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PrescriptionDetail_DaoImpl implements PrescriptionDetail_Dao{

    Connection conn = null;
    PreparedStatement prepStatement= null;
    ResultSet resultSet = null;
    
    public PrescriptionDetail_DaoImpl()
    {
        conn = new DBConnect().getConnection();
    }

    @Override
    public ArrayList<PrescriptionDetail> getPrescriptionList(int ID){
        ArrayList<PrescriptionDetail> list = new ArrayList<>();
        
        String query = "Select * From [PrescriptionDetail] Where PrescriptionID = ?";
        
        try{
            //statement = conn.createStatement();  
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, ID);
            resultSet = prepStatement.executeQuery();
            while (resultSet.next())
            {
                    PrescriptionDetail object = new PrescriptionDetail();
                    object.setID(resultSet.getInt("ID"));

                    object.setPrescription(resultSet.getInt("PrescriptionID"));
                    
                    object.setPrescription(resultSet.getInt("DrugID"));
                    
                    object.setQuantity(resultSet.getInt("Quantity"));
                    object.setPrice(resultSet.getDouble("Price"));
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
    public boolean AddPrescriptionDetail(PrescriptionDetail prescription){
        String sql = "INSERT INTO [PrescriptionDetail] (PrescriptionID, DrugID, Quantity, TotalPrice) VALUES (?,?,?,?)";
        
        try{
            prepStatement = conn.prepareStatement(sql);
            int index = 1;
            prepStatement.setInt(index++, prescription.getPrescription());
            prepStatement.setInt(index++, prescription.getDrug());
            prepStatement.setInt(index++, prescription.getQuantity());
            prepStatement.setDouble(index++, prescription.getPrice());
            return prepStatement.executeUpdate() > 0;
        }
         catch (SQLException e) {
            System.out.println("Failed to add PrescriptionDetail: " + e.getMessage());
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
    public void Delete_PrescriptionDetail(int ID){
        try {
            String query = "DELETE FROM [PrescriptionDetail] WHERE ID=?";
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

    //public Patient getPrescriptionDetail(int ID);
    
    @Override
    public boolean Update_PrescriptionDetail(PrescriptionDetail prescription){
        String query = "UPDATE [PrescriptionDetail] SET EmployeeID =?, MedicalExaminationID =?, CreateDate =?, ReceivedDate =?, TotalPrice =?, Note =? WHERE ID = ?";
        try{
            prepStatement = conn.prepareStatement(query);
            int index = 1;
            prepStatement.setInt(index++, prescription.getPrescription());
            prepStatement.setInt(index++, prescription.getDrug());
            prepStatement.setInt(index++, prescription.getQuantity());
            prepStatement.setDouble(index++, prescription.getPrice());
            prepStatement.setDouble(index++, prescription.getID());
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
}