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
public class Employee {

    
    private Integer ID;
    private String UserName;
    private String Password;
    private String FirstName;
    private String MiddleName;
    private String LastName;
    private Date Birthday;
    private String Gender;
    private String Address;
    private String NumberPhone;
    private String email;
    private String RoleName;
    
    
    public Employee()
    {
        
    }
    
    public Employee(Employee staff)
    {
        this.ID = staff.ID;
        this.UserName = staff.UserName;
        this.Password = staff.Password;
        this.FirstName = staff.FirstName;
        this.MiddleName = staff.MiddleName;
        this.LastName = staff.LastName;
        this.Birthday = staff.Birthday;
        this.RoleName = staff.RoleName;
        this.Gender = staff.Gender;
        this.Address = staff.Address;
        this.NumberPhone = staff.NumberPhone;
        this.email = staff.email;
    }
    
    public Object[] toRowTable(EventAction event) {
        return new Object[]{this.ID, getFullName(), this.Birthday, this.Gender, this.Address, this.NumberPhone, this.RoleName ,new ModelThreeAction(this, event)};
    }
    
    public Employee(Integer ID, String FirstName, String MiddleName, String LastName)
    {
        this.ID = ID;
        this.FirstName = FirstName;
        this.MiddleName = MiddleName;
        this.LastName = LastName;
    }
    
    /**
     * @return the RoleID
     */
    public String getRoleName() {
        if(this.RoleName == null)
            return "";
        return RoleName;
    }

    /**
     * @param RoleName the RoleID to set
     */
    public void setRoleName(String RoleName) {
        this.RoleName = RoleName;
    }
    
    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date Birthday) {
        this.Birthday = Birthday;
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
     * @return the NumberPhonne
     */
    public String getNumberPhone() {
        return NumberPhone;
    }

    /**
     * @param NumberPhonne the NumberPhonne to set
     */
    public void setNumberPhone(String NumberPhonne) {
        this.NumberPhone = NumberPhonne;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    
    /**
     * @return 
     */
    
    public String getUserName() {
        return UserName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.UserName = userName;
    }

    /**
     * @return the Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * @param Password the Password to set
     */
    public void setPassword(String Password) {
        this.Password = Password;
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
    
    @Override
    public String toString()
    {
        return getFullName() + " (ID : " + this.ID + " )";
    }
}
