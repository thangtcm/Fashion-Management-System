/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Form.Employee;

import DatabaseAccessObject_DAO.Staff_Dao;
import DatabaseAccessObject_Impl.Staff_DaoImpl;
import Dialog.Swal_Notification;
import Enum.RoleName;
import Enum.TypeInterface;
import Enum.TypeList;
import Enum.TypeNotification;
import Form.List.ListOfObject;
import Model.Employee;
import Services.CheckHandle;
import ViewForm.Main;
import java.awt.Component;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class NewEmployee extends javax.swing.JPanel {

    private final JPanel main;
    private final TypeInterface type;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Employee employee_target;
    private final Employee employeecurrent;
    public NewEmployee(JPanel main, TypeInterface type, Employee employeecurrent) {
        initComponents();
        this.main = main;
        this.type = type;
        this.employeecurrent = employeecurrent;
        init(type);
    }
    
    public NewEmployee(JPanel main, TypeInterface type, Employee employee_target, Employee employeecurrent) {
        initComponents();
        this.main = main;
        this.type = type;
        this.employee_target = employee_target;
        this.employeecurrent = employeecurrent;
        init(type);
    }
    
    private void init(TypeInterface type)
    {
        if(type == TypeInterface.Create)
        {
            txtFirstName.setText("");
            txtMiddleName.setText("");
            txtLastName.setText("");
            txtEmail.setText("");
            txtAddress.setText("");
            txtPhone.setText("");
            btnSave.setText("Resigner");
            for(RoleName obj : RoleName.values())
            {
                JRole.addItem(obj.toString());
            }
            
        }
        if(type == TypeInterface.Edit)
        {
            txtFirstName.setText(this.employee_target.getFirstName());
            txtMiddleName.setText(this.employee_target.getMiddleName());
            txtLastName.setText(this.employee_target.getLastName());
            txtEmail.setText(this.employee_target.getEmail());
            txtAddress.setText(this.employee_target.getAddress());
            txtPhone.setText(this.employee_target.getNumberPhone());
            for(RoleName obj : RoleName.values())
            {
                JRole.addItem(obj.toString());
            }
            JRole.setSelectedItem(RoleName.valueOf(this.employee_target.getRoleName()));
            btnSave.setText("Save");
        }
    }
    
    private boolean initCreateData()
    {
        Employee employee = new Employee();
        employee.setFirstName(txtFirstName.getText());
        employee.setMiddleName(txtMiddleName.getText());
        employee.setLastName(txtLastName.getText());
        if(CheckHandle.isValidEmail(txtEmail.getText()))
        {
            employee.setEmail(txtEmail.getText().trim());
        }
        else{
            showMessage("Email bạn nhập không hợp lệ", TypeNotification.Warning);
            return false;
        }
        if(CheckHandle.isValidPhone(txtPhone.getText().trim()))
        {
            employee.setNumberPhone(txtPhone.getText().trim());
        }
        else{
            showMessage("Phone bạn nhập không hợp lệ", TypeNotification.Warning);
            return false;
        }
        employee.setAddress(txtAddress.getText());
        employee.setGender(JGender.getSelectedItem().toString());
        System.out.println("Form.Patient.NewPatient.initCreateData()" + JBirthday.getDate());
        String birthday = dateFormat.format(JBirthday.getDate());
        System.out.println("Form.Patient.NewPatient.initCreateData()" + birthday);
        employee.setBirthday(java.sql.Date.valueOf(birthday));

        Staff_Dao employee_Dao = new Staff_DaoImpl();
        if(this.type == TypeInterface.Create)
        {
            if(employee_Dao.AddStaff(employee))
            {
                showMessage("Bạn vừa tạo mới nhân viên thành công", TypeNotification.Success);
                return true;
            }
            
        }
        else if(this.type == TypeInterface.Edit)
        {
            
            employee.setID(this.employee_target.getID());
            employee.setUserName(this.employee_target.getUserName());
            employee.setPassword(this.employee_target.getPassword());
            if(employee_Dao.Update_Staff(employee))
            {
                showMessage("Bạn vừa chỉnh sửa nhân viên thành công", TypeNotification.Success);
                return true;
            }
            
        }
        return false;  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBack = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        JRole = new javax.swing.JComboBox<>();
        JGender = new javax.swing.JComboBox<>();
        txtAddress = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        TitleTable1 = new javax.swing.JLabel();
        btnSave = new Swing.Button.Button2();
        jLabel13 = new javax.swing.JLabel();
        txtMiddleName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        JBirthday = new com.toedter.calendar.JDateChooser();

        setForeground(new java.awt.Color(79, 98, 203));

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Icons/Group 31.png"))); // NOI18N
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Icons/Group 44.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Avatar/Rectangle 43341.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(79, 98, 203));
        jLabel5.setText("Phone Number:");

        jLabel7.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(79, 98, 203));
        jLabel7.setText("Bitrthday: (dd/mm/yyyy)");

        jLabel8.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(79, 98, 203));
        jLabel8.setText("Role");

        jLabel9.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(79, 98, 203));
        jLabel9.setText("Gender");

        txtPhone.setBackground(new java.awt.Color(238, 245, 255));
        txtPhone.setColumns(1);
        txtPhone.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPhone.setText("0922113123");
        txtPhone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(126, 175, 255), 2));

        JRole.setBackground(new java.awt.Color(238, 245, 255));
        JRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Member" }));
        JRole.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(126, 175, 255), 2));

        JGender.setBackground(new java.awt.Color(238, 245, 255));
        JGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Other" }));
        JGender.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(126, 175, 255), 2));

        txtAddress.setBackground(new java.awt.Color(238, 245, 255));
        txtAddress.setColumns(1);
        txtAddress.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtAddress.setText("141 Dien Bien Phu, Binh Thanh, Ho Chi Minh ");
        txtAddress.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(126, 175, 255), 2));

        jLabel10.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(79, 98, 203));
        jLabel10.setText("Address:");

        txtEmail.setBackground(new java.awt.Color(238, 245, 255));
        txtEmail.setColumns(1);
        txtEmail.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtEmail.setText("khanhhlg20@gmail.com");
        txtEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(126, 175, 255), 2));

        jLabel12.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(79, 98, 203));
        jLabel12.setText("Email:");

        TitleTable1.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        TitleTable1.setText("Edit Doctor");

        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Resigner");
        btnSave.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        btnSave.setRadius(15);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(79, 98, 203));
        jLabel13.setText("Middle Name:");

        txtMiddleName.setBackground(new java.awt.Color(238, 245, 255));
        txtMiddleName.setColumns(1);
        txtMiddleName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtMiddleName.setText("Customer Name");
        txtMiddleName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(126, 175, 255), 2));

        jLabel4.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(79, 98, 203));
        jLabel4.setText("First Name:");

        jLabel14.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(79, 98, 203));
        jLabel14.setText("Last Name");

        txtFirstName.setBackground(new java.awt.Color(238, 245, 255));
        txtFirstName.setColumns(1);
        txtFirstName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtFirstName.setText("Customer Name");
        txtFirstName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(126, 175, 255), 2));

        txtLastName.setBackground(new java.awt.Color(238, 245, 255));
        txtLastName.setColumns(1);
        txtLastName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtLastName.setText("Customer Name");
        txtLastName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(126, 175, 255), 2));

        JBirthday.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(126, 175, 255), 2));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnBack)
                        .addGap(16, 16, 16)
                        .addComponent(TitleTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(111, 111, 111)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(111, 111, 111))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(JBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(32, 32, 32)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JGender, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(txtMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel12)
                            .addComponent(jLabel10)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(JRole, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBack)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(TitleTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(jLabel4)
                                    .addGap(6, 6, 6)
                                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addGap(6, 6, 6)
                                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(6, 6, 6)
                                .addComponent(txtMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JGender, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                            .addComponent(JBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JRole, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(6, 6, 6)
                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        // TODO add your handling code here:
        showForm(new ListOfObject(main, TypeList.Employee, this.employeecurrent));
    }//GEN-LAST:event_btnBackMouseClicked

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if(initCreateData())
        {
            showForm(new ListOfObject(main, TypeList.Employee, this.employeecurrent));
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private boolean showMessage(String message, TypeNotification type ) {
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser JBirthday;
    private javax.swing.JComboBox<String> JGender;
    private javax.swing.JComboBox<String> JRole;
    private javax.swing.JLabel TitleTable1;
    private javax.swing.JLabel btnBack;
    private Swing.Button.Button2 btnSave;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtMiddleName;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}
