/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author couni
 */
public class Revisit {
    private Integer ID;
    private MedicalExamination medicalExamination;
    private Date RevisitDate;
    private String Note;

    /**
     * @return the ID
     */
    public Integer getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }

    /**
     * @return the medicalExamination
     */
    public MedicalExamination getMedicalExamination() {
        return medicalExamination;
    }

    /**
     * @param medicalExamination the medicalExamination to set
     */
    public void setMedicalExamination(MedicalExamination medicalExamination) {
        this.medicalExamination = medicalExamination;
    }

    /**
     * @return the RevisitDate
     */
    public Date getRevisitDate() {
        return RevisitDate;
    }

    /**
     * @param RevisitDate the RevisitDate to set
     */
    public void setRevisitDate(Date RevisitDate) {
        this.RevisitDate = RevisitDate;
    }

    /**
     * @return the Note
     */
    public String getNote() {
        return Note;
    }

    /**
     * @param Note the Note to set
     */
    public void setNote(String Note) {
        this.Note = Note;
    }
    
    
}
