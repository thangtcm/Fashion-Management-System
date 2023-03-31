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
    public List<User> getUserList();
    
    public boolean AddUser(User user);
    
    public void Delete_User(User user);
    
    public void Update_User(User user);
    
    public User LoginUser(User user);
    
    public void ChangePassword(User user, String Password);
}
