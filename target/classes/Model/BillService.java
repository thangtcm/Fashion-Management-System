/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Swing.Table.EventAction;
import Swing.Table.ModelThreeAction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author couni
 */
public class BillService {
    private Integer ID;
    private MedicalExamination medicalExamination;
    private Employee employee;
    private Date BillDate;
    private double Price;
    private boolean Paid;
    
    
    //Get all list service
    private ArrayList<PatientService> Listservice = new ArrayList<>();

    public BillService()
    {
        
    }
    
    public BillService(BillService billService)
    {
        this.ID = billService.ID;
        this.medicalExamination = billService.getMedicalExamination();
        this.employee = billService.employee;
        this.BillDate = billService.BillDate;
        this.Price = billService.Price;
        this.Paid = billService.Paid;
    }
    
    public Object[] toRowTable(EventAction event) {
        return new Object[]{this.ID, this.medicalExamination.getPatient().getFullName(), this.BillDate, this.Price, new ModelThreeAction(this, event)};
    }
    
    public Integer getID() {
        return ID;
    }

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
     * @return the BillDate
     */
    public Date getBillDate() {
        return BillDate;
    }

    /**
     * @param BillDate the BillDate to set
     */
    public void setBillDate(Date BillDate) {
        this.BillDate = BillDate;
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
     * @return the Listservice
     */
    public List<PatientService> getListservice() {
        return Listservice;
    }
    
}
