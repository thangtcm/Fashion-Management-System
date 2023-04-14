/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Swing.Table.EventAction;
import Swing.Table.ModelThreeAction;
import java.sql.Date;

/**
 *
 * @author couni
 */
public class Prescription { // Phiếu lĩnh thuốc (BILL)
    private int ID;
    private Employee employee;
    private MedicalExamination medicalExamination;
    private Date CreateTime;
    private Date ReceivedTime;
    private double Price;
    private boolean Paid;
    private String Note;

    
    public Prescription(){
        
    }
    
    public Prescription(Prescription prescription)
    {
        this.ID = prescription.ID;
        this.employee = prescription.employee;
        this.medicalExamination = prescription.medicalExamination;
        this.CreateTime = prescription.CreateTime;
        this.ReceivedTime = prescription.ReceivedTime;
        this.Price = prescription.Price;
        this.Paid = prescription.Paid;
        this.Note = prescription.Note;
    }
    
    public Object[] toRowTable(EventAction event) {
        //DecimalFormat df = new DecimalFormat("$#,##0.00");
        return new Object[]{this.ID, this.medicalExamination.getPatient().getFullName(), this.CreateTime, this.ReceivedTime, this.Price, new ModelThreeAction(this, event)};
    }
    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
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
     * @return the CreateTime
     */
    public Date getCreateTime() {
        return CreateTime;
    }

    /**
     * @param CreateTime the CreateTime to set
     */
    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }

    /**
     * @return the ReceivedTime
     */
    public Date getReceivedTime() {
        return ReceivedTime;
    }

    /**
     * @param ReceivedTime the ReceivedTime to set
     */
    public void setReceivedTime(Date ReceivedTime) {
        this.ReceivedTime = ReceivedTime;
    }

    /**
     * @return the Price
     */
    public double getPrice() {
        return Price;
    }

    /**
     * @param Price the Price to set
     */
    public void setPrice(double Price) {
        this.Price = Price;
    }

    /**
     * @return the Paid
     */
    public boolean isPaid() {
        return Paid;
    }

    /**
     * @param Paid the Paid to set
     */
    public void setPaid(boolean Paid) {
        this.Paid = Paid;
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
