/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseDao;

import Model.User;
import java.util.ArrayList;

/**
 *
 * @author couni
 */
public interface User_Dao {
    public ArrayList<User> getUserList(User user);
    
    public boolean AddUser(User user);
    
    public void Delete_User(int Id);
    
    public void Update_User(User user);
    
    public User LoginUser(User user);
    
    public User getUser(int Id);
    
    public void ChangePassword(User user, String Password);
}
