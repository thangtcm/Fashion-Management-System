/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseDao;

import Model.ProductVariants;
import java.util.ArrayList;

/**
 *
 * @author couni
 */
public interface ProductVariant_Dao {
    // Get toàn bộ thông tin sản phẩm thuộc về Sản Phẩm (ID)
    public ArrayList<ProductVariants> getProductVariantList(int ID);
    
    public boolean AddProductVariant(ProductVariants productVariant);
    
    public void Delete_ProductVariant(ProductVariants productVariant);
    
    public ProductVariants getProductVariant(int ID);
    
    public void Update_Category(ProductVariants productVariant);
}
