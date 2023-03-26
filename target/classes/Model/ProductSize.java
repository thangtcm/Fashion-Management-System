/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


/**
 *
 * @author couni
 */
public class ProductSize {

    /**
     * @return the Size
     */
    public String getSize() {
        return Size;
    }

    /**
     * @param Size the Size to set
     */
    public void setSize(String Size) {
        this.Size = Size;
    }

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
     * @return the ProductID
     */
    public Products getProductID() {
        return ProductID;
    }

    /**
     * @param ProductID the ProductID to set
     */
    public void setProductID(Products ProductID) {
        this.ProductID = ProductID;
    }

    /**
     * @return the PriceSize
     */
    public int getPriceSize() {
        return PriceSize;
    }

    /**
     * @param PriceSize the PriceSize to set
     */
    public void setPriceSize(int PriceSize) {
        this.PriceSize = PriceSize;
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
    private int ID;
    private Products ProductID;
//    private enum Size{
//        SMALL, MEDIUM, LARGE, EXTRALARGE
//    }// Size.SMALL.ToSring()
    private String Size;
    private int PriceSize;
    private int Quantity;
}
