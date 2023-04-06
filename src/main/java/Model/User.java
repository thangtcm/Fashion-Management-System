/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.awt.Color;
import java.sql.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import swing.Table.EventAction;
import swing.Table.ModelAction;
import swing.Table.ModelBadge;
import swing.Table.ModelProfile;

/**
 *
 * @author couni
 */
public class User {

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
     * @return the FullName
     */
    public String getFullName() {
        return FullName;
    }

    /**
     * @param FullName the FullName to set
     */
    public void setFullName(String FullName) {
        this.FullName = FullName;
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
    public String getPhone() {
        return Phone;
    }

    /**
     * @param Phone the Phone to set
     */
    public void setPhone(String Phone) {
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
    private Integer ID;
    private String UserName;
    private String Password;
    private String FullName;
    private Date Birthday;
    private String Phone;
    private String gender;
    private String Address;
    private String RoleName;
    private String Email;
    private String AvartarUrl; 

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
        this.FullName = user.FullName;
        this.Birthday = user.Birthday;
        this.Phone = user.Phone;
        this.Address = user.Address;
        this.RoleName = user.RoleName;
        this.gender = user.gender;
        this.Email = user.Email;
        this.AvartarUrl = user.AvartarUrl;
        System.out.println(user.AvartarUrl);
        this.icon = new ImageIcon(getClass().getResource(user.AvartarUrl));
    }
    
    public User()
    {
        
    }
    
    public User(Icon icon, String FullName, String gender, String Role, int Id,String Phone)
    {
        this.ID = Id;
        this.FullName = FullName;
        this.gender = gender;
        this.Phone = Phone;
        this.RoleName = Role;
        this.icon = icon;
    }
    
    public Object[] toRowTable(EventAction event) {
        //DecimalFormat df = new DecimalFormat("$#,##0.00");
        return new Object[]{new ModelProfile(icon, this.ID), this.FullName, this.gender, this.Phone, new ModelBadge(this.RoleName, new Color (245,118,47), new Color(255,224,187)) ,new ModelAction(this, event)};
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

    /**
     * @return the AvartarUrl
     */
    public String getAvartarUrl() {
        return AvartarUrl;
    }

    /**
     * @param AvartarUrl the AvartarUrl to set
     */
    public void setAvartarUrl(String AvartarUrl) {
        this.AvartarUrl = AvartarUrl;
    }
}
