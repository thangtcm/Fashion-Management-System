/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseAccessObject_DAO;

import Model.Prescription;
import java.util.ArrayList;
/**
 *
 * @author couni
 */
public interface BillPrescription_Dao {
    public ArrayList<Prescription> getBillPrescriptionList(int ID);
    
    public boolean AddBillPrescription(Prescription prescription);
    
    public void Delete_BillPrescription(int ID);
    
    public boolean Update_BillPrescription(Prescription prescription);

    public Prescription getBillPrescription(int ID);
}
