/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author couni
 */
public class Order {
    private int ID;
    private User user;
    private Date OrderDate;
    private Date ShippedDate;
    private String Address;
    private int NumberPhone;
    private String Status;

    /**
     * @param Id
     * @param user
     * @param OrderDate
     * @param ShipedDate
     * @param Address
     * @param NumberPhone
     * @param Status
     */
    
    public Order(int Id, User user, Date OrderDate, Date ShipedDate, String Address, int NumberPhone, String Status)
    {
        this.ID = Id;
        this.user = user;
        this.OrderDate = OrderDate;
        this.ShippedDate = ShippedDate;
        this.Address = Address;
        this.NumberPhone = NumberPhone;
        this.Status = Status;
    }
    
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
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the OrderDate
     */
    public Date getOrderDate() {
        return OrderDate;
    }

    /**
     * @param OrderDate the OrderDate to set
     */
    public void setOrderDate(Date OrderDate) {
        this.OrderDate = OrderDate;
    }

    /**
     * @return the ShippedDate
     */
    public Date getShippedDate() {
        return ShippedDate;
    }

    /**
     * @param ShippedDate the ShippedDate to set
     */
    public void setShippedDate(Date ShippedDate) {
        this.ShippedDate = ShippedDate;
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
     * @return the NumberPhone
     */
    public int getNumberPhone() {
        return NumberPhone;
    }

    /**
     * @param NumberPhone the NumberPhone to set
     */
    public void setNumberPhone(int NumberPhone) {
        this.NumberPhone = NumberPhone;
    }

    /**
     * @return the Status
     */
    public String getStatus() {
        return Status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    
}
