/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DatabaseDao;

import Model.Suppliers;
import java.util.List;

/**
 *
 * @author couni
 */
public interface Supply_Dao {
    public List<Suppliers> getSupplyList();
    
    public boolean AddSupply(Suppliers supply);
    
    public void Delete_Supply(Suppliers supply);
    
    public Suppliers getSupply(int ID);
    
    public void Update_Supply(Suppliers category);
}
