/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Swing.Table.EventAction;
import Swing.Table.ModelThreeAction;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author couni
 */
public class Drug { //Kho thuốc
    private Integer ID;
    private String DrugName;
    private String DrugType;
    private int Quantity;
    private double Price;
    private String Description;
    
    
    //Setup Price
    private final Locale locale ;
    private final NumberFormat currencyFormater;
    public Drug()
    {
        this.locale = new Locale("en", "US");
        this.currencyFormater = NumberFormat.getCurrencyInstance(locale);
        this.currencyFormater.setMaximumFractionDigits(0);
    }
    
    public Drug(Drug drug)
    {
        this.ID = drug.ID;
        this.DrugName = drug.DrugName;
        this.DrugType = drug.DrugType;
        this.Quantity = drug.Quantity;
        this.Price = drug.Price;
        this.Description = drug.Description;
        
        
        this.locale = new Locale("en", "US");
        this.currencyFormater = NumberFormat.getCurrencyInstance(locale);
        this.currencyFormater.setMaximumFractionDigits(0);
        
    }
    
    public Object[] toRowTable(EventAction event) {
        return new Object[]{this.ID, this.DrugName, this.DrugType, this.Quantity, this.currencyFormater.format(this.Price), new ModelThreeAction(this, event)};
    }
    
    public Drug(Integer ID, String DrugName)
    {
        this.ID = ID;
        this.DrugName = DrugName;
        
        this.locale = new Locale("en", "US");
        this.currencyFormater = NumberFormat.getCurrencyInstance(locale);
        this.currencyFormater.setMaximumFractionDigits(0);
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
     * @return the DrugName
     */
    public String getDrugName() {
        return DrugName;
    }

    /**
     * @param DrugName the DrugName to set
     */
    public void setDrugName(String DrugName) {
        this.DrugName = DrugName;
    }

    /**
     * @return the DrugType
     */
    public String getDrugType() {
        return DrugType;
    }

    /**
     * @param DrugType the DrugType to set
     */
    public void setDrugType(String DrugType) {
        this.DrugType = DrugType;
    }

    /**
     * @return the Quantity
     */
    public int getQuantity() {
        return Quantity;
    }

    /**
     * @param Quantity the Quantity to set
     */
    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
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
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }
    
}
