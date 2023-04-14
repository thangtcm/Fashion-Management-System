/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Swing.Table.EventAction;
import Swing.Table.ModelThreeAction;
import java.util.Date;

/**
 *
 * @author couni
 */
public class MedicalExamination {
    private Integer ID;
    private Patient Patient;
    private Integer PatientID;
    private Integer EmployeeID;
    private Employee Employee;
    private Date MedicalDate;
    private String Symptom;
    private String Illnesses;
    private String Note;

    public MedicalExamination(){
        
    }
    
    public MedicalExamination(MedicalExamination medical)
    {
        this.ID = medical.ID;
        this.Patient = medical.Patient;
        this.PatientID = medical.PatientID;
        this.Employee = medical.Employee;
        this.EmployeeID = medical.EmployeeID;
        this.MedicalDate = medical.MedicalDate;
        this.Symptom = medical.Symptom;
        this.Illnesses = medical.Illnesses;
        this.Note = medical.Note;
    }
    
    public Object[] toRowTable(EventAction event) {
        return new Object[]{this.ID, this.Patient.getFullName(), this.Employee.getFullName() , this.MedicalDate, this.Symptom, this.Illnesses ,new ModelThreeAction(this, event)};
    }
    
    @Override
    public String toString()
    {
        return this.Patient.getFullName() + " (ID: " + this.ID + ")";
    }
    
    public MedicalExamination(Integer ID, Patient patient)
    {
        this.ID = ID;
        this.Patient = patient;
    }
    
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
     * @return the Patient
     */
    public Patient getPatient() {
        return Patient;
    }

    /**
     * @param Patient the Patient to set
     */
    public void setPatient(Patient Patient) {
        this.Patient = Patient;
    }

    /**
     * @return the Employee
     */
    public Employee getEmployee() {
        return Employee;
    }

    /**
     * @param Employee the Employee to set
     */
    public void setEmployee(Employee Employee) {
        this.Employee = Employee;
    }

    /**
     * @return the MedicalDate
     */
    public Date getMedicalDate() {
        return MedicalDate;
    }

    /**
     * @param MedicalDate the MedicalDate to set
     */
    public void setMedicalDate(Date MedicalDate) {
        this.MedicalDate = MedicalDate;
    }

    /**
     * @return the Symptom
     */
    public String getSymptom() {
        return Symptom;
    }

    /**
     * @param Symptom the Symptom to set
     */
    public void setSymptom(String Symptom) {
        this.Symptom = Symptom;
    }

    /**
     * @return the Illnesses
     */
    public String getIllnesses() {
        return Illnesses;
    }

    /**
     * @param Illnesses the Illnesses to set
     */
    public void setIllnesses(String Illnesses) {
        this.Illnesses = Illnesses;
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

    /**
     * @return the PatientID
     */
    public Integer getPatientID() {
        return PatientID;
    }

    /**
     * @param PatientID the PatientID to set
     */
    public void setPatientID(Integer PatientID) {
        this.PatientID = PatientID;
    }

    /**
     * @return the EmployeeID
     */
    public Integer getEmployeeID() {
        return EmployeeID;
    }

    /**
     * @param EmployeeID the EmployeeID to set
     */
    public void setEmployeeID(Integer EmployeeID) {
        this.EmployeeID = EmployeeID;
    }
    
    
}
