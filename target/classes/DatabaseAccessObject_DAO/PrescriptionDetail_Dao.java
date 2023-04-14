package DatabaseAccessObject_DAO;
import java.util.ArrayList;
import Model.PrescriptionDetail;

public interface PrescriptionDetail_Dao {
    public ArrayList<PrescriptionDetail> getPrescriptionList(int ID);
    
    public boolean AddPrescriptionDetail(PrescriptionDetail prescription);
    
    public void Delete_PrescriptionDetail(int ID);
   // public Patient getPrescriptionDetail(int ID);
    
    public boolean Update_PrescriptionDetail(PrescriptionDetail prescription);
}
