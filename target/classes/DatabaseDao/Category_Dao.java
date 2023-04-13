/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseDao;

import Model.Categories;
import java.util.ArrayList;

/**
 *
 * @author couni
 */
public interface Category_Dao {
    public ArrayList<Categories> getCategoryList();
    
    public boolean AddCategory(Categories category);
    
    public void Delete_Category(Categories category);
    
    public Categories getCategory(int ID);
    
    public void Update_Category(Categories category);
}
