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
    private int ID;
    private ProductVariants ProductVariantID;
    private String SizeName;

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
     * @return the ProductVariantID
     */
    public ProductVariants getProductVariantID() {
        return ProductVariantID;
    }

    /**
     * @param ProductVariantID the ProductVariantID to set
     */
    public void setProductVariantID(ProductVariants ProductVariantID) {
        this.ProductVariantID = ProductVariantID;
    }

    /**
     * @return the SizeName
     */
    public String getSizeName() {
        return SizeName;
    }

    /**
     * @param SizeName the SizeName to set
     */
    public void setSizeName(String SizeName) {
        this.SizeName = SizeName;
    }
    
    
}
