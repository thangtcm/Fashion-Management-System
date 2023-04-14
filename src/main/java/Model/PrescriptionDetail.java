/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author couni
 */
public class PrescriptionDetail {
    private Integer ID;
    private Integer prescription;
    private Integer drug;
    private int quantity;
    private double Price;
    
    
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
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
     * @return the prescription
     */
    public Integer getPrescription() {
        return prescription;
    }

    /**
     * @param prescription the prescription to set
     */
    public void setPrescription(Integer prescription) {
        this.prescription = prescription;
    }

    /**
     * @return the drug
     */
    public Integer getDrug() {
        return drug;
    }

    /**
     * @param drug the drug to set
     */
    public void setDrug(Integer drug) {
        this.drug = drug;
    }
    
    
}
