/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DatabaseDao;

import Model.Products;
import java.util.ArrayList;

/**
 *
 * @author couni
 */
public interface Product_Dao {
    public ArrayList<Products> getProductList(Products products);
    
    public int AddProduct(Products product);
    
    public void Delete_Product(int ID);
    
    public Products getProducts(int ID);
    
    public void Update_Product(Products product);
}
