/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseAccessObject_DAO;

import Model.Drug;
import java.util.ArrayList;

/**
 *
 * @author couni
 */
public interface Drug_Dao {
    public ArrayList<Drug> getDrugList(Drug drug);
    
    public boolean AddDrug(Drug drug);
    
    public void Delete_Drug(int ID);
    
    public int Count(String where);
    
    public Drug getDrug(int ID);
    
    public boolean Update_Drug(Drug drug);
}
