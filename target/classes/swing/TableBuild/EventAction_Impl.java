/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing.TableBuild;

import DatabaseDao.Product_Dao;
import DatabaseDao.User_Dao;
import DatabaseDaoImpl.Product_DaoImpl;
import DatabaseDaoImpl.User_DaoImpl;
import Enum.TypeFunction;
import Enum.TypeNotification;
import Enum.TypeRoleName;
import Form.Product.ManagementProduct;
import Form.Product.ProductList;
import Form.User.ProfileEdit;
import Form.User.UserList;
import Model.Products;
import Model.User;
import static Services.Notification.showConfirm;
import static Services.Notification.showMessage;
import java.awt.Component;
import javax.swing.JLayeredPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author couni
 */
public abstract class EventAction_Impl implements EventAction{

    private final JTable table;
    private final User userCurrent;
    private final JLayeredPane main;
    
    public EventAction_Impl(JLayeredPane main, JTable table, User userCurrent)
    {
        this.main = main;
        this.table = table;
        this.userCurrent = userCurrent;
    }

    @Override
    public <T> void delete(T obj, int row) {
        if(obj instanceof User)
        {
            User object = (User)obj;
            if (row != -1) {
                if(this.userCurrent.getRoleName().trim().equals(TypeRoleName.Admin.toString()))
                {
                    ModelProfile selectedObject = (ModelProfile) this.table.getValueAt(row, 0);
                    if (showConfirm("Delete User : " + object.getFullName() + "(ID: " + selectedObject.getId() + ")", TypeNotification.Default)) {
                        // Xóa sinh viên tại vị trí rowIndex trong dữ liệu của JTable
                        int UserId = selectedObject.getId();
                        //Hàm Xóa
                        User_Dao user_Dao = new User_DaoImpl();
                        user_Dao.Delete_User(UserId);
                        
                        //Fix Lenght 
                        if (this.table.isEditing()) {
                            table.getCellEditor().stopCellEditing();
                        }
                        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
                        model.removeRow(row);  
                    }
                }
                else
                {
                    showMessage("Bạn không có quyền sử dụng chức năng này", TypeNotification.Warning);
                }
            }
        }
        if(obj instanceof Products)
        {
            Products object = (Products)obj;
            if (row != -1) {
                if(this.userCurrent.getRoleName().trim().equals(TypeRoleName.Admin.toString()))
                {
                    ModelProfile selectedObject = (ModelProfile) this.table.getValueAt(row, 0);
                    if (showConfirm("Delete User : " + object.getProductName()+ "(ID: " + selectedObject.getId() + ")", TypeNotification.Default)) {
                        // Xóa sinh viên tại vị trí rowIndex trong dữ liệu của JTable
                        int UserId = selectedObject.getId();
                        //Hàm Xóa
                        Product_Dao product_Dao = new Product_DaoImpl();
                        product_Dao.Delete_Product(UserId);
                        
                        //Fix Lenght 
                        if (this.table.isEditing()) {
                            table.getCellEditor().stopCellEditing();
                        }
                        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
                        model.removeRow(row);  
                    }
                }
                else
                {
                    showMessage("Bạn không có quyền sử dụng chức năng này", TypeNotification.Warning);
                }
            }
        }
        
    }

    @Override
    public <T> void update(T obj, int row) {
        if(obj instanceof User)
        {
            User object = (User)obj;
            if (row != -1) {
                if(this.userCurrent.getRoleName().trim().equals(TypeRoleName.Admin.toString()))
                {
                    ModelProfile selectedObject = (ModelProfile) this.table.getValueAt(row, 0);
                    if (showConfirm("<html>You are going to interface edit User <br>" + object.getFullName() + "<span style='text-align: center'>(ID: " + selectedObject.getId() + ")</span></html>", TypeNotification.Default)) {
                        showForm(new ProfileEdit(this.main, userCurrent, object, new UserList(main, this.userCurrent, null)));
                    }
                }
                else
                {
                    showMessage("Bạn không có quyền sử dụng chức năng này", TypeNotification.Warning);
                }
            }
        }
        if(obj instanceof Products)
        {
            Products object = (Products)obj;
            if (row != -1) {
                if(this.userCurrent.getRoleName().trim().equals(TypeRoleName.Admin.toString()))
                {
                    if (showConfirm("<html>You are going to interface edit User <br>" + object.getProductName()+ "<span style='text-align: center'>(ID: " + object.getID()+ ")</span></html>", TypeNotification.Default)) {
                        showForm(new ManagementProduct(this.main, userCurrent, TypeFunction.Edit, new ProductList(main, userCurrent), object));
                    }
                }
                else
                {
                    showMessage("Bạn không có quyền sử dụng chức năng này", TypeNotification.Warning);
                }
            }
        }
    }

    @Override
    public <T> void view(T obj, int row) {
        if(obj instanceof User)
        {
            User object = (User)obj;
            if (row != -1) {
                if(this.userCurrent.getRoleName().trim().equals(TypeRoleName.Admin.toString()))
                {
                    ModelProfile selectedObject = (ModelProfile) this.table.getValueAt(row, 0);
                    if (showConfirm("<html>You are going to interface edit User <br>" + object.getFullName() + "<span style='text-align: center'>(ID: " + selectedObject.getId() + ")</span></html>", TypeNotification.Default)) {
                        showForm(new ProfileEdit(this.main, userCurrent, this.userCurrent, new UserList(main, this.userCurrent, null)));
                    }
                }
                else
                {
                    showMessage("Bạn không có quyền sử dụng chức năng này", TypeNotification.Warning);
                }
            }
        }
    }
    
    
    private void showForm(Component com) {
        this.main.removeAll();
        this.main.add(com);
        this.main.repaint();
        this.main.revalidate();
    }
}
