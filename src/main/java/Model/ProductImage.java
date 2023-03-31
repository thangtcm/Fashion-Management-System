/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author couni
 */
public class ProductImage {
    private int ID;
    private Products ProductID;
    private String ImageUrl;

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
     * @return the ProductVariantsID
     */
    public Products getProduct() {
        return ProductID;
    }

    /**
     * @param ProductID the ProductVariantsID to set
     */
    public void setProduct(Products ProductID) {
        this.ProductID = ProductID;
    }

    /**
     * @return the ImageUrl
     */
    public String getImageUrl() {
        return ImageUrl;
    }

    /**
     * @param ImageUrl the ImageUrl to set
     */
    public void setImageUrl(String ImageUrl) {
        this.ImageUrl = ImageUrl;
    }
    
    
}
