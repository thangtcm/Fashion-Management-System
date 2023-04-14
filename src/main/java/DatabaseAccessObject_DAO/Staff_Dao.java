/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseAccessObject_DAO;

import Model.Employee;
import java.util.ArrayList;

/**
 *
 * @author couni
 */
public interface Staff_Dao {
    
    /*Lấy tất cả nhân viên*/
    public ArrayList<Employee> getStaffList(Employee staff);
    
    public boolean AddStaff(Employee staff);
    
    public void Delete_Staff(int ID);
    
    public boolean Update_Staff(Employee staff);
    
    public Employee Login_Staff(Employee staff);
    
    public Employee getEmployee(int ID);
    
    public Employee getNameEmployee(int ID);
    
    public void ChangePassword(Employee staff, String Password);
    
    public int Count(String where);
}
