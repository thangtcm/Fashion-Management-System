/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseAccessObject_Impl;

import DatabaseAccessObject_DAO.Patient_Dao;
import Model.Patient;
import Services.StringHandle;
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
public class Patient_DaoImpl implements Patient_Dao{
    
//    Connection connection = null;
    Connection conn = null;
    PreparedStatement prepStatement= null;
    ResultSet resultSet = null;
    
    public Patient_DaoImpl()
    {
        conn = new DBConnect().getConnection();
    }
    
    @Override
    public ArrayList<Patient> getPatientList(Patient patient){
        ArrayList<Patient> list = new ArrayList<>();
        
        StringBuilder sql = new StringBuilder("SELECT * FROM [Patient]");

        if(patient != null)
        {
            Integer id = patient.getID();
            if(id != null)
            {
                sql.append(" AND ID LIKE '%").append(id).append("%' ");  
            }

            if(patient.getFullName() != null)
            {
                sql.append(" AND CONCAT_WS(' ', FirstName, MiddleName, LastName) LIKE N'").append(StringHandle.addWildcards(patient.getFullName())).append("'");
            } 
        }
        try{
            prepStatement = conn.prepareStatement(sql.toString().replaceFirst("AND", "WHERE"));
            resultSet = prepStatement.executeQuery();
            while (resultSet.next())
            {
                Patient object = new Patient();
                object.setID(resultSet.getInt("ID"));
                object.setFirstName(resultSet.getString("FirstName").trim());
                object.setMiddleName(resultSet.getString("MiddleName").trim());
                object.setLastName(resultSet.getString("LastName").trim());
                object.setBirthDay(resultSet.getDate("Birthday"));
                object.setGender(resultSet.getString("Gender").trim());
                object.setAddress(resultSet.getString("Address").trim());
                object.setEmail(resultSet.getString("Email").trim());
                object.setNumberPhone(resultSet.getString("NumberPhone").trim());
                
//                MedicalExamination_Dao medical = new MedicalExamination_DaoImpl();
//                object.setMedicalList(medical.getMedicalPatientList(resultSet.getInt("ID")));
                
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
    public boolean AddPatient(Patient patient){
//        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO [Patient] (ID, FirstName, MiddleName, LastName, Birthday, Gender, Address, NumberPhone, Email) VALUES (?,?,?,?,?,?,?,?,?)";
        
        try{
            prepStatement = conn.prepareStatement(sql);
            int index = 1;
            prepStatement.setInt(index++, patient.getID());
            prepStatement.setString(index++, patient.getFirstName().trim());
            prepStatement.setString(index++, patient.getMiddleName().trim());
            prepStatement.setString(index++, patient.getLastName().trim());
            prepStatement.setDate(index++, Convert.convertDate(patient.getBirthDay()));
            prepStatement.setString(index++, patient.getGender().trim());
            prepStatement.setString(index++, patient.getAddress().trim());
            prepStatement.setString(index++, patient.getNumberPhone().trim());
            prepStatement.setString(index++, patient.getEmail().trim());
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
    public Patient getPatient(int ID)
    {
        try {
            String query = "SELECT * FROM [Patient] Where ID = ?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, ID);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next())
            {
                Patient object = new Patient();
                object.setID(resultSet.getInt("ID"));
                object.setFirstName(resultSet.getString("FirstName").trim());
                object.setMiddleName(resultSet.getString("MiddleName").trim());
                object.setLastName(resultSet.getString("LastName").trim());
                object.setBirthDay(resultSet.getDate("Birthday"));
                object.setGender(resultSet.getString("Gender").trim());
                object.setAddress(resultSet.getString("Address").trim());
                object.setEmail(resultSet.getString("Email").trim());
                object.setNumberPhone(resultSet.getString("NumberPhone").trim());
                
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
    public Patient getNamePatient(int ID){
        try {
            String query = "SELECT * FROM [Patient] Where ID = ?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, ID);
            resultSet = prepStatement.executeQuery();
            while(resultSet.next())
            {
                Patient object = new Patient();
                object.setID(resultSet.getInt("ID"));
                object.setFirstName(resultSet.getString("FirstName").trim());
                object.setMiddleName(resultSet.getString("MiddleName").trim());
                object.setLastName(resultSet.getString("LastName").trim());
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
    
    //public boolean Delete_Staff(int ID); -- 100 year can delete 1 timer
    
     @Override
    public boolean Update_Patient(Patient patient){

        String query = "UPDATE Patient SET FirstName = ?, MiddleName = ?, LastName = ?, Birthday = ?, Gender = ?, Address = ?, NumberPhone = ?, Email = ? WHERE ID = ?";
        try{
            prepStatement = conn.prepareStatement(query);
            int index = 1;
            prepStatement.setString(index++, patient.getFirstName().trim());
            prepStatement.setString(index++, patient.getMiddleName().trim());
            prepStatement.setString(index++, patient.getLastName().trim());
            prepStatement.setDate(index++, Convert.convertDate(patient.getBirthDay()));
            prepStatement.setString(index++, patient.getGender().trim());
            prepStatement.setString(index++, patient.getAddress().trim());
            prepStatement.setString(index++, patient.getNumberPhone().trim());
            prepStatement.setString(index++, patient.getEmail().trim());
            prepStatement.setInt(index++, patient.getID());
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
    public int Count(String where)
    {
        String queryString = "SELECT COUNT(*) FROM [Patient]";
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
