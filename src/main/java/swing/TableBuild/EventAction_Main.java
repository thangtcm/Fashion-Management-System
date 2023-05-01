/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing.TableBuild;

import Model.User;
import javax.swing.JLayeredPane;
import javax.swing.JTable;



/**
 *
 * @author couni
 */
public class EventAction_Main extends EventAction_Impl{

    public EventAction_Main(JLayeredPane main, JTable table, User userCurrent) {
        super(main, table, userCurrent);
    }
    
    
    @Override
    public <T> void view(T obj, int row) {
        super.view(obj, row);
    }
    
    @Override
    public <T> void update(T obj, int row) {
        super.update(obj, row);
    }
   
    @Override
    public <T> void delete(T obj, int row) {
        super.delete(obj, row);
    }
}
