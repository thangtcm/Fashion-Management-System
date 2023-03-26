/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseDao;

import Model.User;
import java.util.List;

/**
 *
 * @author couni
 */
public interface User_Dao {
    public List<User> getStaffList(User user);
    
    public boolean AddStaff(User user);
    
    public boolean Delete_Staff(int ID);
    
    public boolean Update_Staff(User user);
    
    public User LoginUser(User user);
    
    String ChangePassword(User user, String Password);
}
