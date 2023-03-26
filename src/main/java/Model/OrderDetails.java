/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author couni
 */
public class OrderDetails {

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
     * @return the TotalPrice
     */
    public int getTotalPrice() {
        return TotalPrice;
    }

    /**
     * @param TotalPrice the TotalPrice to set
     */
    public void setTotalPrice(int TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    /**
     * @return the Discount
     */
    public int getDiscount() {
        return Discount;
    }

    /**
     * @param Discount the Discount to set
     */
    public void setDiscount(int Discount) {
        this.Discount = Discount;
    }
    private int ID;
    private Products ProductID;
    private int Quantity;
    private int TotalPrice;
    private int Discount;
}
