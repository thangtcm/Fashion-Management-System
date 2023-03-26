/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;
import javax.swing.Icon;
import swing.Table.EventAction;
import swing.Table.ModelAction;
import swing.Table.ModelProfile;

/**
 *
 * @author couni
 */
public class User {

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
     * @return the UserName
     */
    public String getUserName() {
        return UserName;
    }

    /**
     * @param UserName the UserName to set
     */
    public void setUserName(String UserName) {
        this.UserName = UserName;
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
     * @return the FulName
     */
    public String getFulName() {
        return FulName;
    }

    /**
     * @param FulName the FulName to set
     */
    public void setFulName(String FulName) {
        this.FulName = FulName;
    }

    /**
     * @return the Birthday
     */
    public Date getBirthday() {
        return Birthday;
    }

    /**
     * @param Birthday
     *  the Birthday to set
     */
    public void setBirthday(Date Birthday) {
        this.Birthday = Birthday;
    }

    /**
     * @return the Phone
     */
    public int getPhone() {
        return Phone;
    }

    /**
     * @param Phone the Phone to set
     */
    public void setPhone(int Phone) {
        this.Phone = Phone;
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
     * @return the IDRole
     */
    public String getRoleName() {
        return RoleName;
    }

    /**
     * @param RoleName
     */
    public void setRoleName(String RoleName) {
        this.RoleName = RoleName;
    }
    
    
    
    private Icon icon;
    private int ID;
    private String UserName;
    private  String Password;
    private String FulName;
    private Date Birthday;
    private int Phone;
    private String gender;
    private String Address;
    private String RoleName;

    /**
     * @return the icon
     */
    public Icon getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(Icon icon) {
        this.icon = icon;
    }
    
    public User(User user)
    {
        this.ID = user.ID;
        this.UserName = user.UserName;
        this.Password = user.Password;
        this.FulName = user.FulName;
        this.Birthday = user.Birthday;
        this.Phone = user.Phone;
        this.Address = user.Address;
        this.RoleName = user.RoleName;
        this.icon = user.icon;
    }
    
    public User()
    {
        
    }
    
    public User(Icon icon, String FullName, String gender, String Role, int Id,int Phone)
    {
        this.ID = Id;
        this.FulName = FullName;
        this.gender = gender;
        this.Phone = Phone;
        this.RoleName = Role;
        this.icon = icon;
    }
    
    public Object[] toRowTable(EventAction event) {
        //DecimalFormat df = new DecimalFormat("$#,##0.00");
        return new Object[]{new ModelProfile(icon, this.ID), this.FulName, this.gender, this.Phone, this.RoleName ,new ModelAction(this, event)};
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
}
