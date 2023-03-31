/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author couni
 */
public class Suppliers {

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
     * @return the CompayName
     */
    public String getCompayName() {
        return CompayName;
    }

    /**
     * @param CompayName the CompayName to set
     */
    public void setCompayName(String CompayName) {
        this.CompayName = CompayName;
    }

    /**
     * @return the ContractName
     */
    public String getAddress() {
        return Address;
    }

    /**
     * @param Address the ContractName to set
     */
    public void setAddress(String Address) {
        this.Address = Address;
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
    private int ID;
    private String CompayName;
    private String Address;
    private String Phone;
}
