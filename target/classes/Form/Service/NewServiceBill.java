/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Form.Service;

import DatabaseAccessObject_DAO.BillService_Dao;
import DatabaseAccessObject_DAO.MedicalExamination_Dao;
import DatabaseAccessObject_DAO.PatientService_Dao;
import DatabaseAccessObject_DAO.Service_Dao;
import DatabaseAccessObject_Impl.BillService_DaoImpl;
import DatabaseAccessObject_Impl.MedicalExamination_DaoImpl;
import DatabaseAccessObject_Impl.PatientService_DaoImpl;
import DatabaseAccessObject_Impl.Service_DaoImpl;
import Dialog.Swal_Confirm;
import Dialog.Swal_Notification;
import Enum.TypeInterface;
import Enum.TypeNotification;
import Model.BillService;
import Model.Employee;
import Model.MedicalExamination;
import Model.PatientService;
import Model.Service;
import Swing.Table.EventAction;
import Swing.Table.ThreeAction_Abs;
import ViewForm.Main;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author YAN
 */
public class NewServiceBill extends javax.swing.JPanel {

    /**
     * Creates new form ServiceBill
     */
    private JPanel main;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Employee employee;
    private TypeInterface type;
    private BillService servicer_bill;
    private MedicalExamination medical;
    
    public NewServiceBill()
    {
        initComponents();
    }
    
    public NewServiceBill(JPanel main, TypeInterface type, Employee employee) {
        initComponents();
        this.main = main;
        this.employee = employee;
        this.type = type;
        init(type);
    }
    
    public NewServiceBill(JPanel main, TypeInterface type, Employee employee, MedicalExamination medical, BillService servicer_bill) {
        initComponents();
        this.main = main;
        this.employee = employee;
        this.type = type;
        if(servicer_bill == null)
            this.servicer_bill = new BillService();
        else
            this.servicer_bill = servicer_bill;
        this.medical = medical;
        init(type);
    }
    
    private void init(TypeInterface type)
    {
        txtDescription.setText("");
        txtNote.setText("");
        txtResult.setText("");
        MedicalExamination_Dao medical_Dao = new MedicalExamination_DaoImpl();
        ArrayList<MedicalExamination> medicalList = medical_Dao.getMedicalList(new MedicalExamination(null, null));
        DefaultComboBoxModel<MedicalExamination> model = new DefaultComboBoxModel<>();
        model.addAll(medicalList);
        JMedical.setModel(model);
        JMedical.setSelectedItem(this.medical);
        JMedical.setEditable(false);
        JMedical.setEnabled(false);
        // Tắt sự kiện nhấn phím và chuột trên JComboBox
        JMedical.setFocusable(false);
        JMedical.setRequestFocusEnabled(false);
        
        Service_Dao service_Dao = new Service_DaoImpl();
        ArrayList<Service> serviceLists = service_Dao.getServiceList(new Service(null, null));
        DefaultComboBoxModel<Service> modelService = new DefaultComboBoxModel<>();
        modelService.addAll(serviceLists);
        JService.setModel(modelService);
        
        table1.fixTable(jScrollPane1);
        DefaultTableModel modeltable = new DefaultTableModel();
        modeltable.addColumn("#");
        modeltable.addColumn("Tên Dịch Vụ");
        modeltable.addColumn("Bắt Đầu Ngày");
        modeltable.addColumn("Hạn Ngày");
        modeltable.addColumn("Kết Quả");
        modeltable.addColumn("Chức Năng");

        if(type == TypeInterface.Edit)
        {
            PatientService_Dao patientService_Dao = new PatientService_DaoImpl();
            EventAction eventAction = new EventAction() {
                @Override
                public <T> void delete(T object, int row) {
                    if (object instanceof PatientService) {
                        PatientService obj = (PatientService) object;
                        if(row != -1)
                        {
                            if (showMessage("Delete Services", TypeNotification.Default)) {
                                if (table1.isEditing()) {
                                    table1.getCellEditor().stopCellEditing();
                                }
                                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                                model.removeRow(row);
                                patientService_Dao.Delete_ServiceDetail(obj.getID());
                        } else {
                            System.out.println("User click Cancel");
                        }
                        }
                    }
                }

                @Override
                public <T> void update(T object, int row) {
                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }

                @Override
                public <T> void view(T object, int row) {
                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }
            };
            
            ArrayList<PatientService> patientServiceList = patientService_Dao.getServiceList(servicer_bill.getID());
            for(PatientService obj : patientServiceList)
            {
                obj.setSerivces(service_Dao.getService(obj.getService()));
                modeltable.addRow(obj.toRowTable(eventAction));
            }
        }
        table1.setModel(modeltable);
        table1.setDefaultEditor(Object.class, null);
    }
    
    private boolean ImportData()
    {
        Date date = new Date();
        BillService_Dao billService_Dao = new BillService_DaoImpl();
        servicer_bill.setBillDate(java.sql.Date.valueOf(dateFormat.format(date)));
        servicer_bill.setEmployee(employee);
        servicer_bill.setMedicalExamination(medical);
        PatientService_Dao patientService_Dao = new PatientService_DaoImpl();
        int patientServiceID = billService_Dao.AddBillService(servicer_bill);
        if(patientServiceID != -1)
        {
            for(PatientService obj : servicer_bill.getListservice())
            {
                obj.setBillService(patientServiceID);
                if(!patientService_Dao.AddServiceDetail(obj))
                {
                    showMessage("Đã có lỗi xảy ra", TypeNotification.Error);
                    return false;
                }
            }
            return true;
        }
        
        return false;
    }

    private boolean showMessage(String message, TypeNotification type ) {
        Swal_Notification obj = new Swal_Notification(Main.getFrames()[0], true);
        obj.showMessage(message, type);
        return obj.isOk();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TitleTable = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        panel1 = new Swing.Panel.Panel();
        jLabel7 = new javax.swing.JLabel();
        btnAdd = new Swing.Button.Button2();
        JMedical = new Swing.Combobox.ComboBoxSuggestion();
        JService = new Swing.Combobox.ComboBoxSuggestion();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        JStartTime = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        JEndTime = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        textAreaScroll3 = new Swing.TextField.TextAreaScroll();
        txtDescription = new Swing.TextField.TextArea();
        textAreaScroll4 = new Swing.TextField.TextAreaScroll();
        txtNote = new Swing.TextField.TextArea();
        jLabel20 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        textAreaScroll5 = new Swing.TextField.TextAreaScroll();
        txtResult = new Swing.TextField.TextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new Swing.Table.Table();
        btnAdd1 = new Swing.Button.Button2();

        setOpaque(false);

        TitleTable.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        TitleTable.setText("Services Bill");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Icons/Group 31.png"))); // NOI18N

        panel1.setBackground(new java.awt.Color(255, 255, 255));
        panel1.setRoundBottomLeft(55);
        panel1.setRoundBottomRight(55);
        panel1.setRoundTopLeft(55);
        panel1.setRoundTopRight(55);

        jLabel7.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(124, 124, 124));
        jLabel7.setText("Medical:");

        btnAdd.setText("Add");
        btnAdd.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        btnAdd.setRadius(25);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        JMedical.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        JMedical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMedicalActionPerformed(evt);
            }
        });

        JService.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "C", "D", "SSSS", "Thắng", "Duyên", "AAA" }));
        JService.setSelectedIndex(-1);

        jLabel8.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(124, 124, 124));
        jLabel8.setText("Service:");

        jLabel9.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(124, 124, 124));
        jLabel9.setText("StartTime:");

        jLabel10.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(124, 124, 124));
        jLabel10.setText("EndTime:");

        jLabel19.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(124, 124, 124));
        jLabel19.setText("Description:");

        textAreaScroll3.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        textAreaScroll3.setLabelText("Input Description");
        textAreaScroll3.setOpaque(false);

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        txtDescription.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        textAreaScroll3.setViewportView(txtDescription);

        textAreaScroll4.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        textAreaScroll4.setLabelText("Input Note");
        textAreaScroll4.setOpaque(false);

        txtNote.setColumns(20);
        txtNote.setRows(5);
        txtNote.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        textAreaScroll4.setViewportView(txtNote);

        jLabel20.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(124, 124, 124));
        jLabel20.setText("Note:");

        jLabel11.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(124, 124, 124));
        jLabel11.setText("Result:");

        textAreaScroll5.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        textAreaScroll5.setLabelText("Input Result");
        textAreaScroll5.setOpaque(false);

        txtResult.setColumns(20);
        txtResult.setRows(5);
        txtResult.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        textAreaScroll5.setViewportView(txtResult);

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textAreaScroll5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textAreaScroll4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textAreaScroll3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(JService, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(JMedical, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(133, 133, 133)
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(JStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(JEndTime, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JMedical, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JStartTime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JEndTime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textAreaScroll3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textAreaScroll4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(textAreaScroll5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)))
                .addGap(15, 15, 15))
        );

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Patient", "Services Name", "Start Day", "Price", "Result", "Note"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table1);

        btnAdd1.setText("Save");
        btnAdd1.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        btnAdd1.setRadius(25);
        btnAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TitleTable, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 942, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(TitleTable, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void JMedicalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMedicalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JMedicalActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        EventAction eventAction = new EventAction() {
            @Override
            public <T> void delete(T object, int row) {
                if (object instanceof PatientService) {
                    PatientService obj = (PatientService) object;
                    if(row != -1)
                    {
                        if (showMessage("Delete Services", TypeNotification.Default)) {
                            if (table1.isEditing()) {
                                table1.getCellEditor().stopCellEditing();
                            }
                            DefaultTableModel model = (DefaultTableModel) table1.getModel();
                            model.removeRow(row);
                            servicer_bill.getListservice().remove(obj);
                    } else {
                        System.out.println("User click Cancel");
                    }
                    }
                }
            }

            @Override
            public <T> void update(T object, int row) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public <T> void view(T object, int row) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };
        PatientService patientService = new PatientService();
        patientService.setDescription(txtDescription.getText().trim());
        patientService.setNote(txtNote.getText().trim());
        patientService.setResult(txtResult.getText().trim());
        patientService.setStartTime(java.sql.Date.valueOf(dateFormat.format(JStartTime.getDate())));
        patientService.setEndTime(java.sql.Date.valueOf(dateFormat.format(JEndTime.getDate())));
        Service value = (Service)JService.getSelectedItem();
        patientService.setService(value.getID());
        patientService.setPrice(value.getServicePrice());
        DefaultTableModel modelTable = (DefaultTableModel) table1.getModel();
        modelTable.addRow(patientService.toRowTable(eventAction));
        table1.setModel(modelTable);
        servicer_bill.getListservice().add(patientService);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd1ActionPerformed
        if(ImportData())
        {
            showMessage("Thêm hóa đơn thành công", TypeNotification.Success);
        }
    }//GEN-LAST:event_btnAdd1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser JEndTime;
    private Swing.Combobox.ComboBoxSuggestion JMedical;
    private Swing.Combobox.ComboBoxSuggestion JService;
    private com.toedter.calendar.JDateChooser JStartTime;
    private javax.swing.JLabel TitleTable;
    private Swing.Button.Button2 btnAdd;
    private Swing.Button.Button2 btnAdd1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private Swing.Panel.Panel panel1;
    private Swing.Table.Table table1;
    private Swing.TextField.TextAreaScroll textAreaScroll3;
    private Swing.TextField.TextAreaScroll textAreaScroll4;
    private Swing.TextField.TextAreaScroll textAreaScroll5;
    private Swing.TextField.TextArea txtDescription;
    private Swing.TextField.TextArea txtNote;
    private Swing.TextField.TextArea txtResult;
    // End of variables declaration//GEN-END:variables
}
