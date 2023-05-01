/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;

/**
 *
 * @author couni
 */
public class ProductVariants {
    private int ID;
    private Products productID;
    private String Size;
    private double Price;
    private int Stock;
    
    
    //
    private List<Icon> icon = new ArrayList<>();
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

    public ProductVariants()
    {
        
    }
    
    public ProductVariants(ProductVariants productVariants)
    {
        this.ID = productVariants.ID;
        this.productID = productVariants.productID;
        this.Size = productVariants.Size;
        this.Price = productVariants.Price;
        this.Stock = productVariants.Stock;
    }
    
    /**
     * @return the product
     */
    public Products getProduct() {
        return productID;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(Products product) {
        this.productID = product;
    }

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
     * @return the ImageUrl
     */

    /**
     * @param ImageUrl the ImageUrl to set
     */
    /**
     * @return the Stock
     */
    public int getStock() {
        return Stock;
    }

    /**
     * @param Stock the Stock to set
     */
    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    /**
     * @return the icon
     */
    public List<Icon> getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(List<Icon> icon) {
        this.icon = icon;
    }
}
