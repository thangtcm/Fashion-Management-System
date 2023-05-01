/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseDao;

import Model.ProductImage;
import java.util.ArrayList;

/**
 *
 * @author couni
 */
public interface ProductImage_Dao {
    public ArrayList<ProductImage> getProductImageList(int ID); // ID Product Variant
    
    public boolean AddProductImage(ProductImage productImage);
    
    public void Delete_ProductImage(ProductImage productImage);
    
//    public ProductImage getProductVariant(int ID);
}
