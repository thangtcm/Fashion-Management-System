/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseAccessObject_DAO;

import Model.MedicalExamination;
import java.util.ArrayList;

/**
 *
 * @author couni
 */
public interface MedicalExamination_Dao {
    public ArrayList<MedicalExamination> getMedicalList(MedicalExamination medicalExamination);
    
    public boolean AddMedicalExamination(MedicalExamination medicalExamination);
    
    public ArrayList<MedicalExamination> getMedicalPatientList(int ID);
    
    //public boolean Delete_Staff(int ID); -- 100 year can delete 1 timer
    public MedicalExamination getMedicalExamination(int ID);
    
    public int Count(String where);
    
    public boolean Update_MedicalExamination(MedicalExamination medicalExamination);
}
