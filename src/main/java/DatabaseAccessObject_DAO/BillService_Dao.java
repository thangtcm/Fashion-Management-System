/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseAccessObject_DAO;

import Model.BillService;
import java.util.ArrayList;

/**
 *
 * @author couni
 */
public interface BillService_Dao {
    public ArrayList<BillService> getBillServiceList(int ID);
    
    public int AddBillService(BillService billService);
    
    public void Delete_BillService(int ID);
    
    public boolean Update_BillService(BillService billService);
}
