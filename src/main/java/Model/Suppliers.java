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
    public String getContractName() {
        return ContractName;
    }

    /**
     * @param ContractName the ContractName to set
     */
    public void setContractName(String ContractName) {
        this.ContractName = ContractName;
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
    private int ID;
    private String CompayName;
    private String ContractName;
    private int Phone;
}
