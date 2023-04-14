package Swing.Table;

import Model.Employee;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author couni
 */
public class ThreeAction_Abs extends EventAction_Impl{
    
    
    public ThreeAction_Abs(JTable table, Employee employee, JPanel main) {
        super(table, employee, main);
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
