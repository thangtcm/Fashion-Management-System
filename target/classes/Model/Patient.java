/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Swing.Table.EventAction;
import Swing.Table.ModelThreeAction;
import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author couni
 */
public class Patient {
    private Integer ID;
    private String FirstName;
    private String MiddleName;
    private String LastName;
    private Date BirthDay;
    private String Address;
    private String Gender;
    private String NumberPhone;
    private String Email;
    
    
    private ArrayList<MedicalExamination> medicalList; 

    /**
     * @return the ID
     */
    public Integer getID() {
        return ID;
    }
    
    public void setID(Integer ID){
        this.ID = ID;
    }

    /**
     * @return the FirstName
     */
    public String getFirstName() {
        return FirstName;
    }

    /**
     * @param FirstName the FirstName to set
     */
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    /**
     * @return the MiddleName
     */
    public String getMiddleName() {
        return MiddleName;
    }

    /**
     * @param MiddleName the MiddleName to set
     */
    public void setMiddleName(String MiddleName) {
        this.MiddleName = MiddleName;
    }

    /**
     * @return the LastName
     */
    public String getLastName() {
        return LastName;
    }

    /**
     * @param LastName the LastName to set
     */
    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    /**
     * @return the BirthDay
     */
    public Date getBirthDay() {
        return BirthDay;
    }

    /**
     * @param BirthDay the BirthDay to set
     */
    public void setBirthDay(Date BirthDay) {
        this.BirthDay = BirthDay;
    }

    /**
     * @return the Address
     */
    public String getAddress() {
        return Address;
    }

    /**
     * @param Address the Address to set
     */
    public void setAddress(String Address) {
        this.Address = Address;
    }

    /**
     * @return the Sex
     */
    public String getGender() {
        return Gender;
    }

    /**
     * @param Gender the Sex to set
     */
    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    /**
     * @return the NumberPhone
     */
    public String getNumberPhone() {
        return NumberPhone;
    }

    /**
     * @param NumberPhone the NumberPhone to set
     */
    public void setNumberPhone(String NumberPhone) {
        this.NumberPhone = NumberPhone;
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }
     
    public String getFullName()
    {
        if (this.FirstName == null && this.MiddleName == null && this.LastName == null) {
            return null;
        }
        String fullName = (this.FirstName != null ? this.FirstName : "")
                + (this.MiddleName  != null ?  " " + this.MiddleName  : "") + " "
                + (this.LastName != null ? this.LastName : "");
        fullName = fullName.trim();
        if (fullName.isEmpty()) {
            return null;
        }
        return fullName;
    }
    
    public Patient(){
        
    }
    
    public Patient(Integer Id, String FirstName,String MiddleName,String LastName)
    {
        this.ID = Id;
        this.FirstName = FirstName;
        this.MiddleName = MiddleName;
        this.LastName = LastName;
    }

    /**
     * @return the medicalList
     */
    public ArrayList<MedicalExamination> getMedicalList() {
        return medicalList;
    }

    /**
     * @param medicalList the medicalList to set
     */
    public void setMedicalList(ArrayList<MedicalExamination> medicalList) {
        this.medicalList = medicalList;
    }
    
    public Patient(Patient patient)
    {
        this.ID = patient.ID;
        this.FirstName = patient.FirstName;
        this.MiddleName = patient.MiddleName;
        this.LastName = patient.LastName;
        this.Gender = patient.Gender;
        this.BirthDay = patient.getBirthDay();
        this.Address = patient.Address;
        this.NumberPhone = patient.NumberPhone;
        this.Email = patient.Email;
    }
    
    public Object[] toRowTable(EventAction event) {
        //DecimalFormat df = new DecimalFormat("$#,##0.00");
        return new Object[]{this.ID, getFullName(), this.BirthDay, this.Gender, this.Address, this.NumberPhone ,new ModelThreeAction(this, event)};
    }
}
