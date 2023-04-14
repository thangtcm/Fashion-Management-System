/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseAccessObject_Impl;

import DatabaseAccessObject_DAO.MedicalExamination_Dao;
import DatabaseAccessObject_DAO.Revisit_Dao;
import Model.Revisit;
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
public class Revisit_DaoImpl implements Revisit_Dao{
    Connection conn = null;
    PreparedStatement prepStatement= null;
    ResultSet resultSet = null;
    
    public Revisit_DaoImpl()
    {
        conn = new DBConnect().getConnection();
    }

    @Override
    public ArrayList<Revisit> getRevisitList(Revisit revisit) {
        ArrayList<Revisit> list = new ArrayList<>();
        
        StringBuilder sql = new StringBuilder("SELECT r.* FROM Revisit"
                + "INNER JOIN MedicalExamination m ON m.ID = r.MedicalExaminationID "
                + " WHERE ");

        if(revisit != null)
        {
            Integer id = revisit.getID();
            if(id != null)
            {
                sql.append("ID = '").append(revisit.getID()).append("' ");  
            }else
                {
                    sql.append("r.MedicalExaminationID = '").append(revisit.getMedicalExamination().getID()).append("' ");
                }
            
        }
        try{
            prepStatement = conn.prepareStatement(sql.toString());
            resultSet = prepStatement.executeQuery();
            while (resultSet.next())
            {
                Revisit object = new Revisit();
                object.setID(resultSet.getInt("ID"));
                
                MedicalExamination_Dao medical = new MedicalExamination_DaoImpl();
                object.setMedicalExamination(medical.getMedicalExamination(resultSet.getInt("m.ID")));
                
                object.setRevisitDate(resultSet.getDate("RevisitDate"));
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
    public boolean AddRevisit(Revisit revisit) {
        String sql = "INSERT INTO [Revisit] (MedicalExaminationID, RevisitDate, Note) VALUES (?,?,?)";
        
        try{
            prepStatement = conn.prepareStatement(sql);
            int index = 1;
            prepStatement.setInt(index++, revisit.getMedicalExamination().getID());
            prepStatement.setDate(index++, Convert.convertDate(revisit.getRevisitDate()));
            prepStatement.setString(index++, revisit.getNote().trim());
            return prepStatement.executeUpdate() > 0;
        }
         catch (SQLException e) {
            System.out.println("Failed to add Revisit: " + e.getMessage());
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
    public void Delete_Revisit(int ID) {
        try {
            String query = "DELETE FROM [Revisit] WHERE ID=?";
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
    public boolean Update_Revisit(Revisit revisit) {
        String query = "UPDATE [Revisit] SET MedicalExamination = ?, RevisitDate = ?, Note = ? WHERE ID = ?";
        try{
            prepStatement = conn.prepareStatement(query);
            int index = 1;
            prepStatement.setInt(index++, revisit.getMedicalExamination().getID());
            prepStatement.setDate(index++, Convert.convertDate(revisit.getRevisitDate()));
            prepStatement.setString(index++, revisit.getNote().trim());
            prepStatement.setInt(index++, revisit.getID());
            return prepStatement.executeUpdate() > 0;
        } catch (SQLException e)
        {
            System.out.println("Failed to add Revisit: " + e.getMessage());
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
