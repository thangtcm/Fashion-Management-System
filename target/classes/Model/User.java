/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.awt.Color;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import swing.TableBuild.EventAction;
import swing.TableBuild.ModelAction;
import swing.TableBuild.ModelBadge;
import swing.TableBuild.ModelProfile;

/**
 *
 * @author couni
 */
public class User {

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getFullName() {
        if(this.FullName == null || FullName.trim().isEmpty())
            return null;
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date Birthday) {
        this.Birthday = Birthday;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String RoleName) {
        this.RoleName = RoleName;
    }
    
    private Icon icon;
    private Integer ID;
    private String UserName;
    private  String Password;
    private String FullName;
    private Date Birthday;
    private String Phone;
    private String gender;
    private String Address;
    private String RoleName;
    private String Email;
    private String AvartarUrl; 

    public Icon getIcon() {
        this.icon = new ImageIcon(getClass().getResource(this.AvartarUrl));
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public User(String FullName, String RoleName)
    {
        this.FullName = FullName;
        this.RoleName = RoleName;
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
    
    public User(Icon icon, String FullName, String gender, String Role, Integer Id,String Phone)
    {
        this.ID = Id;
        this.FullName = FullName;
        this.gender = gender;
        this.Phone = Phone;
        this.RoleName = Role;
        this.icon = icon;
    }
    
    public Object[] toRowTable(EventAction event) {
        return new Object[]{new ModelProfile(icon, this.ID), this.FullName, this.gender, this.Phone, new ModelBadge(this.RoleName, new Color (245,118,47), new Color(255,224,187)) ,new ModelAction(this, event)};
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAvartarUrl() {
        return AvartarUrl;
    }

    public void setAvartarUrl(String AvartarUrl) {
        this.AvartarUrl = AvartarUrl;
    }
}
