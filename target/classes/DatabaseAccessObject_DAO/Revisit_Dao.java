/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseAccessObject_DAO;

import Model.Revisit;
import java.util.ArrayList;

/**
 *
 * @author couni
 */
public interface Revisit_Dao {
    public ArrayList<Revisit> getRevisitList(Revisit revisit);
    
    public boolean AddRevisit(Revisit revisit);
    
    public void Delete_Revisit(int ID);
    
    public boolean Update_Revisit(Revisit revisit);
}
