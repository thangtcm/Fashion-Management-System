/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DatabaseDao.Category_Dao;
import DatabaseDaoImpl.Category_DaoImpl;
import java.util.ArrayList;
import java.util.List;
import swing.TableBuild.EventAction;
import swing.TableBuild.ModelAction;

/**
 *
 * @author couni
 */
public class Products {
    private Integer ID;
    private String ProductName;
    private Suppliers SupplerID;
    private Categories Category;
    private Integer CategoryID;
    private String Description;
    private boolean Status;
    private double Sale;
    private int Stock;
    
    
    //Toàn bộ Image về sản phẩm
    private List<ProductImage> productImages;
    
    private List<ProductColor> productColor;
    
    // Lấy toàn bộ thông tin sản phẩm
    private List<ProductVariants> productVariants = new ArrayList<>();

    public Products(Products product)
    {
        this.ID = product.ID;
        this.ProductName = product.ProductName;
        this.SupplerID = product.SupplerID;
        this.CategoryID = product.CategoryID;
        this.Description = product.Description;
        this.Status = product.Status;
        this.Sale = product.Sale;
        this.productImages = product.productImages;
        this.productColor = product.productColor;
        this.productVariants = product.productVariants;
    }
    
    public Products()
    {
        
    }
    
    public Products(String ProductsName, Integer CategoryID)
    {
        this.ProductName = ProductsName;
        this.CategoryID = CategoryID;
    }
    
    public Object[] toRowTable(EventAction event) {
        String NameStatus;
        if(this.Status)
            NameStatus = "Activity";
        else
            NameStatus = "Inactive";
        return new Object[]{this.ID, this.ProductName, NameStatus, this.Sale, this.Stock ,new ModelAction(this, event)};
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
     * @return the ProductName
     */
    public String getProductName() {
        if(this.ProductName == null || this.ProductName.trim().isEmpty())
            return null;
        return ProductName;
    }

    /**
     * @param ProductName the ProductName to set
     */
    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    /**
     * @return the SupplerID
     */
    public Suppliers getSupplerID() {
        return SupplerID;
    }

    /**
     * @param SupplerID the SupplerID to set
     */
    public void setSupplerID(Suppliers SupplerID) {
        this.SupplerID = SupplerID;
    }

    public Categories getCategory() {
        return this.Category;
    }

    public void setCategory(Categories Category) {
        this.Category = Category;
    }

    /**
     * @return the Status
     */
    public boolean getStatus() {
        return Status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    /**
     * @return the Sale
     */
    public double getSale() {
        return Sale;
    }

    /**
     * @param Sale the Sale to set
     */
    public void setSale(double Sale) {
        this.Sale = Sale;
    }

    /**
     * @return the decryption
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

    /**
     * @return the productVariants
     */
    public List<ProductVariants> getProductVariants() {
        return productVariants;
    }

    /**
     * @param productVariants the productVariants to set
     */
    public void setProductVariants(List<ProductVariants> productVariants) {
        this.productVariants = productVariants;
    }

    /**
     * @return the productImages
     */
    public List<ProductImage> getProductImages() {
        return productImages;
    }

    /**
     * @param productImages the productImages to set
     */
    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    /**
     * @return the productColor
     */
    public List<ProductColor> getProductColor() {
        return productColor;
    }

    /**
     * @param productColor the productColor to set
     */
    public void setProductColor(List<ProductColor> productColor) {
        this.productColor = productColor;
    }

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
     * @return the CategoryID
     */
    public Integer getCategoryID() {
        return CategoryID;
    }

    /**
     * @param CategoryID the CategoryID to set
     */
    public void setCategoryID(Integer CategoryID) {
        this.CategoryID = CategoryID;
    }
    
}
