package DatabaseAccessObject_DAO;
import Model.PatientService;
import java.util.ArrayList;

public interface PatientService_Dao {
    public ArrayList<PatientService> getServiceList(int ID);
    
    public boolean AddServiceDetail(PatientService service);
    
    public void Delete_ServiceDetail(int ID);

    //public PatientService getServiceDetail(int ID);

    public boolean Update_ServiceDetail(PatientService service);
}
