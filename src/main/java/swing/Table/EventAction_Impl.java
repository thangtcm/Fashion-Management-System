/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Swing.Table;

import DatabaseAccessObject_DAO.Drug_Dao;
import DatabaseAccessObject_DAO.Service_Dao;
import DatabaseAccessObject_DAO.Staff_Dao;
import DatabaseAccessObject_Impl.Drug_DaoImpl;
import DatabaseAccessObject_Impl.Service_DaoImpl;
import DatabaseAccessObject_Impl.Staff_DaoImpl;
import Dialog.Swal_Confirm;
import Dialog.Swal_Notification;
import Enum.RoleName;
import Enum.TypeInterface;
import Enum.TypeNotification;
import Form.Drug.NewDrug;
import Form.Employee.EmployeeProfile;
import Form.Employee.NewEmployee;
import Form.Medical.MedicalDetails;
import Form.Medical.NewMedical;
import Form.Patient.NewPatient;
import Form.Patient.PatientDetails;
import Form.Service.NewService;
import Model.BillService;
import Model.Drug;
import Model.Employee;
import Model.MedicalExamination;
import Model.Patient;
import Model.PatientService;
import Model.Service;
import ViewForm.Main;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author couni
 */
public abstract class EventAction_Impl implements EventAction{
    private final JTable table;
    private final Employee employeecurrent;
    private final JPanel main;

    public EventAction_Impl(JTable table, Employee employeecurrent, JPanel main) {
        this.table = table;
        this.employeecurrent = employeecurrent;
        this.main = main;
    }

    @Override
    public <T> void delete(T obj, int row) {
        if (obj instanceof Patient) {
            Patient patient = (Patient)obj;
            System.out.println(".delete()" + patient.getID());
            if (row != -1) {
                showMessageError("Can't Delete Patient", TypeNotification.Warning);
            } else {
                System.out.println("No row selected");
            }
        }
        if (obj instanceof Employee) {
            Employee employee = (Employee) obj;
            System.out.println(".delete()" + employee.getID());
            if (row != -1) {
                if(this.employeecurrent.getRoleName().equals(RoleName.Admin.toString()))
                {
                    if (showMessage("Delete Employee : " + employee.getFullName() + "(ID: " + employee.getID() + ")", TypeNotification.Default)) {
                        if (this.table.isEditing()) {
                            this.table.getCellEditor().stopCellEditing();
                        }
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.removeRow(row);

                        Staff_Dao employee_Dao = new Staff_DaoImpl();
                        employee_Dao.Delete_Staff(employee.getID());
                    }
                }
                else {
                    showMessageError("You don't have permission to use this function.", TypeNotification.Warning);
                }
            } else {
                System.out.println("No row selected");
            }
        }
        if (obj instanceof MedicalExamination) {
            MedicalExamination medical = (MedicalExamination) obj;
            System.out.println(".delete()" + medical.getID());
            if (row != -1) {
                showMessageError("Can't Delete MedicalExamination", TypeNotification.Warning);
            }
        }
        if (obj instanceof Drug) {
            Drug object = (Drug) obj;
            System.out.println(".delete()" + object.getID());
            if (row != -1) {
                if(this.employeecurrent.getRoleName().equals(RoleName.Admin.toString()) || this.employeecurrent.getRoleName().equals(RoleName.Pharmacist.toString()))
                {
                    if (showMessage("Delete Employee : " + object.getDrugName()+ "(ID: " + object.getID() + ")", TypeNotification.Default)) {
                        if (this.table.isEditing()) {
                            this.table.getCellEditor().stopCellEditing();
                        }
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.removeRow(row);

                        Drug_Dao object_Dao = new Drug_DaoImpl();
                        object_Dao.Delete_Drug(object.getID());
                    } else {
                        System.out.println("User click Cancel");
                    }
                }
                else {
                    showMessageError("You don't have permission to use this function.", TypeNotification.Warning);
                }
            } else {
                System.out.println("No row selected");
            } 
        }
        if (obj instanceof PatientService) {
            PatientService object = (PatientService) obj;
            System.out.println(".delete()" + object.getID());
            if (row != -1) {
                if(this.employeecurrent.getRoleName().equals(RoleName.Admin.toString()) || this.employeecurrent.getRoleName().equals(RoleName.Pharmacist.toString()))
                {
                    if (showMessage("Delete Services", TypeNotification.Default)) {
                        if (this.table.isEditing()) {
                            this.table.getCellEditor().stopCellEditing();
                        }
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.removeRow(row);

                    } else {
                        System.out.println("User click Cancel");
                    }
                }
                else {
                    showMessageError("You don't have permission to use this function.", TypeNotification.Warning);
                }
            } else {
                System.out.println("No row selected");
            } 
        }
        if (obj instanceof BillService) {
            BillService object = (BillService) obj;
            System.out.println(".delete()" + object.getID());
            if (row != -1) {
                if(this.employeecurrent.getRoleName().equals(RoleName.Admin.toString()) || this.employeecurrent.getRoleName().equals(RoleName.Pharmacist.toString()))
                {
                    if (showMessage("Delete Services", TypeNotification.Default)) {
                        if (this.table.isEditing()) {
                            this.table.getCellEditor().stopCellEditing();
                        }
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.removeRow(row);

                    } else {
                        System.out.println("User click Cancel");
                    }
                }
                else {
                    showMessageError("You don't have permission to use this function.", TypeNotification.Warning);
                }
            } else {
                System.out.println("No row selected");
            } 
        }
        if (obj instanceof Service) {
            Service object = (Service) obj;
            System.out.println(".delete()" + object.getID());
            if (row != -1) {
                if(this.employeecurrent.getRoleName().equals(RoleName.Admin.toString()))
                {
                    if (showMessage("Delete Service : " + object.getServiceName()+ "(ID: " + object.getID() + ")", TypeNotification.Default)) {
                        if (this.table.isEditing()) {
                            this.table.getCellEditor().stopCellEditing();
                        }
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.removeRow(row);

                        Service_Dao object_Dao = new Service_DaoImpl();
                        object_Dao.Delete_Service(object.getID());
                    } else {
                        System.out.println("User click Cancel");
                    }
                }
                else {
                    showMessageError("You don't have permission to use this function.", TypeNotification.Warning);
                }
            } else {
                System.out.println("No row selected");
            } 
        }
    }
    
    @Override
    public <T> void update(T obj, int row) {
        if(obj instanceof Patient)
        {
            int rowIndex = table.getSelectedRow();
            Patient patient = (Patient)obj;
            if (rowIndex >= 0) {
                
                if (showMessage("You are going to the patient edit interface : " + patient.getFullName()+ "(ID: " + patient.getID()+ ")", TypeNotification.Default)) {
                // Chuyển giao diện sang Edit
                showForm(new NewPatient(main, TypeInterface.Edit, patient, this.employeecurrent));
                } else {
                    System.out.println("User click Cancel");
                }
                
            } else {
                System.out.println("No row selected");
            }
        }
        if(obj instanceof Employee)
        {
            int rowIndex = table.getSelectedRow();
            Employee object = (Employee)obj;
            if (rowIndex >= 0) {
                if(this.employeecurrent.getRoleName().equals(RoleName.Admin.toString()))
                {
                    if (showMessage("You are going to the employee edit interface : " + object.getFullName()+ "(ID: " + object.getID()+ ")", TypeNotification.Default)) {
                        // Chuyển giao diện sang Edit
                        //Staff_Dao employee_Dao = new Staff_DaoImpl();
                        showForm(new NewEmployee(main, TypeInterface.Edit, object, this.employeecurrent));
                        // ...
                    } else {
                        System.out.println("User click Cancel");
                    }
                }
                else {
                    showMessageError("You don't have permission to use this function.", TypeNotification.Warning);
                }
            } else {
                System.out.println("No row selected");
            }
        }
        if(obj instanceof MedicalExamination)
        {
            int rowIndex = table.getSelectedRow();
            MedicalExamination object = (MedicalExamination)obj;
            if (rowIndex >= 0) {
                if (showMessage("You are going to the medical edit interface : " + object.getPatient().getFullName()+ "(ID: " + object.getID()+ ")", TypeNotification.Default)) {
                    // Chuyển giao diện sang Edit
                    //MedicalExamination_Dao medical = new MedicalExamination_DaoImpl();
                    showForm(new NewMedical(main, TypeInterface.Edit, this.employeecurrent, object));
                } else {
                    System.out.println("User click Cancel");
                }
            } else {
                System.out.println("No row selected");
            }
        }
        if(obj instanceof Drug)
        {
            int rowIndex = table.getSelectedRow();
            Drug object = (Drug)obj;
            if (rowIndex >= 0) {
                if(this.employeecurrent.getRoleName().equals(RoleName.Admin.toString()) || this.employeecurrent.getRoleName().equals(RoleName.Pharmacist.toString()))
                {
                    if (showMessage("You are going to the drug edit interface : " + object.getDrugName()+ "(ID: " + object.getID()+ ")", TypeNotification.Default)) {
                        // Chuyển giao diện sang Edit
                        //Drug_Dao object_Dao = new Drug_DaoImpl();
                        showForm(new NewDrug(main, TypeInterface.Edit, this.employeecurrent, object));
                        // ...
                    } else {
                        System.out.println("User click Cancel");
                    }
                }
                else {
                    showMessageError("You don't have permission to use this function.", TypeNotification.Warning);
                }
            } else {
                System.out.println("No row selected");
            }
        }
        if(obj instanceof Service)
        {
            int rowIndex = table.getSelectedRow();
            Service object = (Service)obj;
            if (rowIndex >= 0) {
                if(this.employeecurrent.getRoleName().equals(RoleName.Admin.toString()) || this.employeecurrent.getRoleName().equals(RoleName.Pharmacist.toString()))
                {
                    if (showMessage("You are going to the drug edit interface : " + object.getServiceName()+ "(ID: " + object.getID()+ ")", TypeNotification.Default)) {
                        // Chuyển giao diện sang Edit
                        //Drug_Dao object_Dao = new Drug_DaoImpl();
                        showForm(new NewService(main, TypeInterface.Edit, object, this.employeecurrent));
                        // ...
                    } else {
                        System.out.println("User click Cancel");
                    }
                }
                else {
                    showMessageError("You don't have permission to use this function.", TypeNotification.Warning);
                }
            } else {
                System.out.println("No row selected");
            }
        }
    }
    
    @Override
    public <T> void view(T obj, int row) {
        if(obj instanceof Patient)
        {
            int rowIndex = table.getSelectedRow();
            Patient object = (Patient)obj;
            if (rowIndex >= 0) {
                if (showMessage("View Patient : " + object.getFullName()+ "(ID: " + object.getID()+ ")", TypeNotification.Default)) {
                    // Chuyển giao diện xem tại đây
                    showForm(new PatientDetails(main, this.employeecurrent, object));
                } else {
                    System.out.println("User click Cancel");
                }
            } else {
                System.out.println("No row selected");
            }
        }
        else if(obj instanceof Employee)
        {
            int rowIndex = table.getSelectedRow();
            Employee object = (Employee)obj;
            if (rowIndex >= 0) {
                if (showMessage("View Patient : " + object.getFullName()+ "(ID: " + object.getID()+ ")", TypeNotification.Default)) {
                    // Chuyển giao diện xem tại đây
                    showForm(new EmployeeProfile(main, this.employeecurrent, object));
                } else {
                    System.out.println("User click Cancel");
                }
            } else {
                System.out.println("No row selected");
            }
        }
        else if(obj instanceof MedicalExamination)
        {
            int rowIndex = table.getSelectedRow();
            MedicalExamination object = (MedicalExamination)obj;
            if (rowIndex >= 0) {
                if (showMessage("View Patient : " + object.getPatient().getFullName()+ "(ID: " + object.getID()+ ")", TypeNotification.Default)) {
                    // Chuyển giao diện xem tại đây
                    showForm(new MedicalDetails(main, this.employeecurrent, object));
                } else {
                    System.out.println("User click Cancel");
                }
            } else {
                System.out.println("No row selected");
            }
        }
        else if(obj instanceof Drug)
        {
            int rowIndex = table.getSelectedRow();
            if (rowIndex >= 0) {
                showMessageError("The fuction doesn't exits, it look like it's under construction.", TypeNotification.Warning);
            } else {
                System.out.println("No row selected");
            }
        }
        else if(obj instanceof Service)
        {
            int rowIndex = table.getSelectedRow();
            if (rowIndex >= 0) {
                showMessageError("The fuction doesn't exits, it look like it's under construction.", TypeNotification.Warning);
            } else {
                System.out.println("No row selected");
            }
        }

    }
    
    private boolean showMessage(String message, TypeNotification type ) {
        Swal_Confirm obj = new Swal_Confirm(Main.getFrames()[0], true);
        obj.showMessage(message, type);
        return obj.isOk();
    }
    
    private boolean showMessageError(String message, TypeNotification type ) {
        Swal_Notification obj = new Swal_Notification(Main.getFrames()[0], true);
        obj.showMessage(message, type);
        return obj.isOk();
    }
    
    private void showForm(Component com){
        main.removeAll();
        main.add(com);
        main.repaint();
        main.revalidate();
    }
}
