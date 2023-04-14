/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseAccessObject_Impl;

import DatabaseAccessObject_DAO.MedicalExamination_Dao;
import DatabaseAccessObject_DAO.Patient_Dao;
import DatabaseAccessObject_DAO.Staff_Dao;
import Dialog.Swal_Notification;
import Enum.TypeNotification;
import Model.MedicalExamination;
import Services.StringHandle;
import ViewForm.Main;
import dao.Convert;
import dao.DBConnect;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author couni
 */
public class MedicalExamination_DaoImpl implements MedicalExamination_Dao{

    Connection conn = null;
    PreparedStatement prepStatement= null;
    ResultSet resultSet = null;
    
    public MedicalExamination_DaoImpl()
    {
        conn = new DBConnect().getConnection();
    }
    
    @Override
    public ArrayList<MedicalExamination> getMedicalList(MedicalExamination medicalExamination) {
        ArrayList<MedicalExamination> list = new ArrayList<>();
        
        StringBuilder sql = new StringBuilder("SELECT * FROM [MedicalExamination]");
        

        if(medicalExamination != null)
        {
            Integer id = medicalExamination.getID();
            if(id != null)
            {
                sql.append(" AND m.ID LIKE '%").append(id).append("%' ");  
            }
            if(medicalExamination.getPatient() != null)
            {
                if(medicalExamination.getPatient().getFullName() != null)
                {
                    sql.append(" AND CONCAT_WS(' ', p.FirstName, p.MiddleName, p.LastName)  LIKE N'").append(StringHandle.addWildcards(medicalExamination.getPatient().getFullName())).append("'");
                }
            }
        }
        try{
            prepStatement = conn.prepareStatement(sql.toString().replaceFirst("AND", "WHERE"));
            resultSet = prepStatement.executeQuery();
            while (resultSet.next())
            {
                MedicalExamination object = new MedicalExamination();
                object.setID(resultSet.getInt("ID"));
                
                Patient_Dao patient = new Patient_DaoImpl();
                object.setPatient(patient.getNamePatient(resultSet.getInt("PatientID")));
                
                Staff_Dao employee = new Staff_DaoImpl();
                object.setEmployee(employee.getNameEmployee(resultSet.getInt("EmployeeID")));

                object.setMedicalDate(resultSet.getDate("MedicalDate"));
                object.setSymptom(resultSet.getString("Symptom"));
                if(!resultSet.getString("Illnesses").equals(""))
                    object.setIllnesses(resultSet.getString("Illnesses"));
                else
                    object.setIllnesses("Chưa rõ");
                
                object.setNote(resultSet.getString("Note"));
                list.add(object);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            new DBConnect().closeResources(conn, prepStatement, resultSet);
        }
        return list;
    }
    
    @Override
    public ArrayList<MedicalExamination> getMedicalPatientList(int ID)
    {
        ArrayList<MedicalExamination> list = new ArrayList<>();
        String query = "Select m.* From MedicalExamination m WHERE m.PatientID = ?";
        try{
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, ID);
            resultSet = prepStatement.executeQuery();
            while (resultSet.next())
            {
                MedicalExamination object = new MedicalExamination();
                object.setID(resultSet.getInt("ID"));
                
                Patient_Dao patient = new Patient_DaoImpl();
                object.setPatient(patient.getNamePatient(resultSet.getInt("PatientID")));
                
                Staff_Dao employee = new Staff_DaoImpl();
                object.setEmployee(employee.getNameEmployee(resultSet.getInt("EmployeeID")));
                
                object.setMedicalDate(resultSet.getDate("MedicalDate"));
                object.setSymptom(resultSet.getString("Symptom"));
                object.setIllnesses(resultSet.getString("Illnesses"));
                object.setNote(resultSet.getString("Note"));
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
    public boolean AddMedicalExamination(MedicalExamination medicalExamination) {
        String sql = "INSERT INTO [MedicalExamination] (PatientID, EmployeeID, MedicalDate, Symptom, Illnesses, Note) VALUES (?,?,?,?,?,?)";
        
        try{
            prepStatement = conn.prepareStatement(sql);
            int index = 1;
            prepStatement.setInt(index++, medicalExamination.getPatient().getID());
            prepStatement.setInt(index++, medicalExamination.getEmployee().getID());
            prepStatement.setDate(index++, Convert.convertDate(medicalExamination.getMedicalDate()));
            prepStatement.setString(index++, medicalExamination.getSymptom().trim());
            prepStatement.setString(index++, medicalExamination.getIllnesses().trim());
            prepStatement.setString(index++, medicalExamination.getNote().trim());
            return prepStatement.executeUpdate() > 0;
        }
         catch (SQLException e) {
            System.out.println("Failed to add medicalExamination: " + e.getMessage());
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
    public boolean Update_MedicalExamination(MedicalExamination medicalExamination) {
        String query = "UPDATE Patient SET PatientID = ?, EmployeeID = ?, MedicalDate = ?, Symptom = ?, Illnesses = ?, Note = ? WHERE ID = ?";
        try{
            prepStatement = conn.prepareStatement(query);
            int index = 1;
            prepStatement.setInt(index++, medicalExamination.getPatient().getID());
            prepStatement.setInt(index++, medicalExamination.getEmployee().getID());
            prepStatement.setDate(index++, Convert.convertDate(medicalExamination.getMedicalDate()));
            prepStatement.setString(index++, medicalExamination.getSymptom().trim());
            prepStatement.setString(index++, medicalExamination.getIllnesses().trim());
            prepStatement.setString(index++, medicalExamination.getNote().trim());
            prepStatement.setInt(index++, medicalExamination.getID());
            showMessage("Bạn vừa tạo thành công phiếu khám bệnh của bệnh nhân + " + medicalExamination.getPatient().getFullName() + " !!", TypeNotification.Success);
            return prepStatement.executeUpdate() > 0;
        } catch (SQLException e)
        {
            System.out.println("Failed to add medicalExamination: " + e.getMessage());
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
    public MedicalExamination getMedicalExamination(int ID) {
        try {
            String query = "SELECT * FROM [MedicalExamination] Where ID = ?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, ID);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next())
            {
                MedicalExamination object = new MedicalExamination();
                object.setID(resultSet.getInt("ID"));
                
                Patient_Dao patient = new Patient_DaoImpl();
                object.setPatient(patient.getPatient(resultSet.getInt("PatientID")));
                
                Staff_Dao employee = new Staff_DaoImpl();
                object.setEmployee(employee.getEmployee(resultSet.getInt("EmployeeID")));
                
                object.setMedicalDate(resultSet.getDate("MedicalDate"));
                object.setSymptom(resultSet.getString("Symptom"));
                object.setIllnesses(resultSet.getString("Illnesses"));
                object.setNote(resultSet.getString("Note"));
                
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
    
    @Override
    public int Count(String where)
    {
        String queryString = "SELECT COUNT(*) FROM [MedicalExamination]";
        int count = 0;
        if(where != null || !"".equals(where))
        {
            queryString += " WHERE " + where;
        }
        try {
            prepStatement = conn.prepareStatement(queryString);
            resultSet = prepStatement.executeQuery();
            if(resultSet.next())
                count = resultSet.getInt(1);
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
        return count;
    }
    
    private boolean showMessage(String message, TypeNotification type ) {
        Swal_Notification obj = new Swal_Notification(Main.getFrames()[0], true);
        obj.showMessage(message, type);
        return obj.isOk();
    }
    
}
