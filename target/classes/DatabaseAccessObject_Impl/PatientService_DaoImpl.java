package DatabaseAccessObject_Impl;

import DatabaseAccessObject_DAO.PatientService_Dao;
import Model.PatientService;
import dao.Convert;
import dao.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PatientService_DaoImpl implements PatientService_Dao{

    Connection conn = null;
    PreparedStatement prepStatement= null;
    ResultSet resultSet = null;
    
    public PatientService_DaoImpl()
    {
        conn = new DBConnect().getConnection();
    }

    @Override
    public ArrayList<PatientService> getServiceList(int ID){
        ArrayList<PatientService> list = new ArrayList<>();
        String query = "Select * From [PatientService] Where BillServiceID = ?";
        
        try{
            //statement = conn.createStatement();  
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, ID);
            resultSet = prepStatement.executeQuery();
            while (resultSet.next())
            {
                    PatientService object = new PatientService();
                    object.setID(resultSet.getInt("ID"));

                    object.setService(resultSet.getInt("ServiceID"));
                    
                    object.setBillService(resultSet.getInt("BillServiceID"));
                    
                    object.setStartTime(resultSet.getDate("StartTime"));
                    object.setEndTime(resultSet.getDate("EndTime"));
                    object.setDescription(resultSet.getString("Description"));
                    object.setResult(resultSet.getString("Result"));
                    object.setNote(resultSet.getString("Note"));
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
    public boolean AddServiceDetail(PatientService service){
        String sql = "INSERT INTO [PatientServices] (ServiceID, BillServiceID, StartTime, EndTime, Description, Result, Note, Price) VALUES (?,?,?,?,?,?,?,?)";
        
        try{
            prepStatement = conn.prepareStatement(sql);
            int index = 1;
            prepStatement.setInt(index++, service.getService());
            prepStatement.setInt(index++, service.getBillService());
            prepStatement.setDate(index++, Convert.convertDate(service.getStartTime()));
            prepStatement.setDate(index++, Convert.convertDate(service.getEndTime()));
            prepStatement.setString(index++, service.getDescription());
            prepStatement.setString(index++, service.getResult());
            prepStatement.setString(index++, service.getNote());
            prepStatement.setDouble(index++, service.getPrice());
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
    public void Delete_ServiceDetail(int ID){
        try {
            String query = "DELETE FROM [PatientServices] WHERE ID=?";
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
    public boolean Update_ServiceDetail(PatientService service){
        String query = "UPDATE [PatientServices] SET ServiceID =?, BillServiceID =?, StartTime =?, EndTime =?, Description =?, Result =?, Note =?, Price =? WHERE ID = ?";
        try{
            prepStatement = conn.prepareStatement(query);
            int index = 1;
            prepStatement.setInt(index++, service.getService());
            prepStatement.setInt(index++, service.getBillService());
            prepStatement.setDate(index++, Convert.convertDate(service.getStartTime()));
            prepStatement.setDate(index++, Convert.convertDate(service.getEndTime()));
            prepStatement.setString(index++, service.getDescription());
            prepStatement.setString(index++, service.getNote());
            prepStatement.setDouble(index++, service.getPrice());
            prepStatement.setInt(index++, service.getID());
            return prepStatement.executeUpdate() > 0;
        } catch (SQLException e)
        {
            System.out.println("Failed to add Service: " + e.getMessage());
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