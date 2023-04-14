/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Form.List;

import Form.Drug.NewDrug;
import DatabaseAccessObject_DAO.Drug_Dao;
import DatabaseAccessObject_DAO.Patient_Dao;
import DatabaseAccessObject_DAO.Service_Dao;
import DatabaseAccessObject_DAO.Staff_Dao;
import DatabaseAccessObject_Impl.Drug_DaoImpl;
import DatabaseAccessObject_Impl.Patient_DaoImpl;
import DatabaseAccessObject_Impl.Service_DaoImpl;
import DatabaseAccessObject_Impl.Staff_DaoImpl;
import Enum.TypeInterface;
import Enum.TypeList;
import static Enum.TypeList.Drug;
import static Enum.TypeList.Employee;
import static Enum.TypeList.Patient;
import static Enum.TypeList.Revisit;
import static Enum.TypeList.Services;
import Form.Employee.NewEmployee;
import Form.Patient.NewPatient;
import Form.Service.NewService;
import Model.Drug;
import Model.Employee;
import Model.Patient;
import Model.Service;
import Swing.Table.EventAction;
import Swing.Table.ThreeAction_Abs;
import java.awt.Component;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author YAN
 */
public class ListOfObject extends javax.swing.JPanel {

    /**
     * Creates new form PatientList
     */
    private final JPanel main;
    private final TypeList list;
    private final Locale locale ;
    private final NumberFormat currencyFormater;
    private final Employee employeecurrent;
    public ListOfObject(JPanel main, TypeList list, Employee employeecurrent) {
        initComponents();
        this.locale = new Locale("en", "US");
        this.currencyFormater = NumberFormat.getCurrencyInstance(locale);
        this.currencyFormater.setMaximumFractionDigits(0);
        this.main = main;
        this.list = list;
        this.employeecurrent = employeecurrent;
        table.fixTable(jScrollPane1);
        InitDate();
    }
    
    private void InitDate()
    {
        EventAction eventAction = new ThreeAction_Abs(table, this.employeecurrent, main);
        DefaultTableModel model = new DefaultTableModel();
        if(null != list)
        switch (list) {
            case Drug -> {
                TitleTable.setText("List Drug");
                textSeachID.setLabelText("Search ID");
                textSeachName.setLabelText("Search Drug's Name");
                btnNew.setText("New Drug");
                model.addColumn("#");
                model.addColumn("Tên Thuốc");
                model.addColumn("Loại Thuốc");
                model.addColumn("Giá");
                model.addColumn("Số Lượng");
                model.addColumn("Chức Năng");
                Drug_Dao drugs = new Drug_DaoImpl();
                ArrayList<Drug> druglist = drugs.getDrugList(new Drug(null,null));
                for (Drug object : druglist) {
                    model.addRow(new Drug(object).toRowTable(eventAction));
                }
                //đưa dữ liệu từ model vào bảng
                table.fixTable(jScrollPane1);
                table.setModel(model);
                table.setDefaultEditor(Object.class, null);
            }
            case Employee -> {
                TitleTable.setText("List Employee");
                textSeachID.setLabelText("Search ID");
                textSeachName.setLabelText("Search Employee's Name");
                btnNew.setText("New Employee");
                model.addColumn("#");
                model.addColumn("Họ Và Tên");
                model.addColumn("Ngày Sinh");
                model.addColumn("Giới Tính");
                model.addColumn("Địa Chỉ");
                model.addColumn("Số Điện Thoại");
                model.addColumn("Chức Vụ");
                model.addColumn("Chức Năng");
                Staff_Dao staffs = new Staff_DaoImpl();
                ArrayList<Employee> stafflist = staffs.getStaffList(new Employee(null,null,null,null));
                for (Employee object : stafflist) {
                    //tạo hàng
                    model.addRow(new Employee(object).toRowTable(eventAction));
                }
                //đưa dữ liệu từ model vào bảng
                table.fixTable(jScrollPane1);
                table.setModel(model);
                table.setDefaultEditor(Object.class, null);
            }

            case Patient -> {
                TitleTable.setText("List Patient");
                textSeachID.setLabelText("Search CID");
                textSeachName.setLabelText("Search Patient's Name");
                btnNew.setText("New Patient");
                model.addColumn("#");
                model.addColumn("Họ Và Tên");
                model.addColumn("Ngày Sinh");
                model.addColumn("Giới Tính");
                model.addColumn("Địa Chỉ");
                model.addColumn("Số Điện Thoại");
                model.addColumn("Chức Năng");
                Patient_Dao patients = new Patient_DaoImpl();
                ArrayList<Patient> patientList = patients.getPatientList(new Patient(null,null,null,null));
                for (Patient object : patientList) {
                    model.addRow(new Patient(object).toRowTable(eventAction));
                }
                //đưa dữ liệu từ model vào bảng
                table.fixTable(jScrollPane1);
                table.setModel(model);
                table.setDefaultEditor(Object.class, null);
            }
            case Revisit -> {
            }
            case Services -> {
                TitleTable.setText("List Service");
                textSeachID.setLabelText("Search ID");
                textSeachName.setLabelText("Search Service's Name");
                btnNew.setText("New Service");
                model.addColumn("#");
                model.addColumn("Tên Dịch Vụ");
                model.addColumn("Giá");
                model.addColumn("Mô tả");
                model.addColumn("Chức Năng");
                Service_Dao service = new Service_DaoImpl();
                ArrayList<Service> serverList = service.getServiceList(new Service(null,null));
                for (Service object : serverList) {
                    model.addRow(new Service(object).toRowTable(eventAction));
                }
                //đưa dữ liệu từ model vào bảng
                table.fixTable(jScrollPane1);
                table.setModel(model);
                table.setDefaultEditor(Object.class, null);
            }
            default -> {
            }
        }
    }
    
    private void showForm(Component com){
        main.removeAll();
        main.add(com);
        main.repaint();
        main.revalidate();
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
        Card = new widget.SPatients();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new Swing.Table.Table();
        jPanel1 = new javax.swing.JPanel();
        textSeachName = new Swing.Textfield.TextFieldSuggestion();
        textSeachID = new Swing.Textfield.TextFieldSuggestion();
        btnSearch = new Swing.Button.Button2();
        btnNew = new Swing.Button.Button2();

        setOpaque(false);

        TitleTable.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        TitleTable.setText("List Drug Bill");

        Card.setShadowSize(5);

        table.setBackground(new java.awt.Color(238, 245, 255));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Drug", "Drug Name", "Drug Type", "Quantity", "Price", "Decription", "Delete"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel1.setOpaque(false);

        textSeachName.setForeground(new java.awt.Color(204, 204, 204));
        textSeachName.setText("");
        textSeachName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textSeachNameFocusGained(evt);
            }
        });

        textSeachID.setText("");
        textSeachID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textSeachIDActionPerformed(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        btnSearch.setRadius(15);
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(textSeachID, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(textSeachName, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textSeachName, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(textSeachID, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        btnNew.setText("New Drug");
        btnNew.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        btnNew.setRadius(15);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 940, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(TitleTable, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(Card, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(TitleTable, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Card, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        if(null != list)
        switch (list) {
            case Drug -> {
                showForm(new NewDrug(main, TypeInterface.Create, this.employeecurrent));
            }
            case Employee -> {
                showForm(new NewEmployee(main, TypeInterface.Create ,this.employeecurrent));
            }
            case Patient -> {
                showForm(new NewPatient(main, TypeInterface.Create , this.employeecurrent));
            }
            case Revisit -> {
                
            }
            case Services -> {
                showForm(new NewService(main, TypeInterface.Create, this.employeecurrent));
            }
            default -> {
            }
        }
        
    }//GEN-LAST:event_btnNewActionPerformed

    private void textSeachNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textSeachNameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_textSeachNameFocusGained

    private void textSeachIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textSeachIDActionPerformed

    }//GEN-LAST:event_textSeachIDActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        if(null != list)
        switch (list) {
            case Drug -> {
                Drug drug = new Drug();
                if(!textSeachID.getText().equals(textSeachID.getLabelText()))
                {
                    drug.setID(Integer.valueOf(textSeachID.getText().trim()));
                }
                else
                {
                    drug.setID(null);
                }
                if(!textSeachName.getText().equals(textSeachName.getLabelText()))
                {
                    drug.setDrugName(textSeachName.getText());
                }
                else
                {
                    drug.setDrugName(null);
                }
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("#");
                model.addColumn("Tên Thuốc");
                model.addColumn("Loại Thuốc");
                model.addColumn("Giá");
                model.addColumn("Số Lượng");
                model.addColumn("Chức Năng");
                String formattedAmount;
                Drug_Dao drugs = new Drug_DaoImpl();
                ArrayList<Drug> druglist = drugs.getDrugList(drug);
                for (Drug object : druglist) {
                    ArrayList<Object> item = new ArrayList<>();
                    item.add(object.getID());
                    item.add(object.getDrugName());
                    item.add(object.getDrugType());
                    formattedAmount = currencyFormater.format(object.getPrice());
                    item.add(formattedAmount);
                    item.add(object.getQuantity());
                    //tạo hàng
                    model.addRow(item.toArray());
                }
                table.fixTable(jScrollPane1);
                table.setModel(model);
                table.setDefaultEditor(Object.class, null);
            }
            case Employee -> {
                Employee employee = new Employee();
                if(!textSeachID.getText().equals(textSeachID.getLabelText()))
                {
                    employee.setID(Integer.valueOf(textSeachID.getText().trim()));
                }
                else
                {
                    employee.setID(null);
                }
                if(!textSeachName.getText().equals(textSeachName.getLabelText()))
                {
                    employee.setFirstName(null);
                    employee.setMiddleName(null);
                    employee.setLastName(textSeachName.getText().trim());
                }
                else
                {
                    employee.setFirstName(null);
                    employee.setMiddleName(null);
                    employee.setLastName(null);
                }
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("#");
                model.addColumn("Họ Và Tên");
                model.addColumn("Ngày Sinh");
                model.addColumn("Giới Tính");
                model.addColumn("Số Điện Thoại");
                model.addColumn("Chức Vụ");
                Staff_Dao staffs = new Staff_DaoImpl();
                ArrayList<Employee> stafflist = staffs.getStaffList(employee);
                for (Employee object : stafflist) {
                    ArrayList<Object> item = new ArrayList<>();
                    item.add(object.getID());
                    item.add(object.getFullName());
                    item.add(object.getBirthday());
                    item.add(object.getGender());
                    item.add(object.getNumberPhone());
                    item.add(object.getRoleName());
                    //tạo hàng
                    model.addRow(item.toArray());
                }
                //đưa dữ liệu từ model vào bảng
                table.fixTable(jScrollPane1);
                table.setModel(model);
                table.setDefaultEditor(Object.class, null);
            }
            case Patient -> {
                Patient patient = new Patient();
                if(!textSeachID.getText().equals(textSeachID.getLabelText()))
                {
                    patient.setID(Integer.valueOf(textSeachID.getText().trim()));
                }
                else
                {
                    patient.setID(null);
                }
                if(!textSeachName.getText().equals(textSeachName.getLabelText()))
                {
                    patient.setFirstName(null);
                    patient.setMiddleName(null);
                    patient.setLastName(textSeachName.getText().trim());
                }
                else
                {
                    patient.setFirstName(null);
                    patient.setMiddleName(null);
                    patient.setLastName(null);
                }
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("#");
                model.addColumn("Họ Và Tên");
                model.addColumn("Ngày Sinh");
                model.addColumn("Giới Tính");
                model.addColumn("Địa Chỉ");
                model.addColumn("Số Điện Thoại");
                Patient_Dao patients = new Patient_DaoImpl();
                List<Patient> patientList = patients.getPatientList(patient);
                for (Patient object : patientList) {
                    ArrayList<Object> item = new ArrayList<>();
                    item.add(object.getID());
                    item.add(object.getFullName());
                    item.add(object.getBirthDay());
                    item.add(object.getGender());
                    item.add(object.getAddress());
                    item.add(object.getNumberPhone());
                    //tạo hàng
                    model.addRow(item.toArray());
                }
                //đưa dữ liệu từ model vào bảng
                table.fixTable(jScrollPane1);
                table.setModel(model);
                table.setDefaultEditor(Object.class, null);
            }
            case Revisit -> {

            }
            case Services -> {
                Service service = new Service();
                if(!textSeachID.getText().equals(textSeachID.getLabelText()))
                {
                    service.setID(Integer.valueOf(textSeachID.getText().trim()));
                }
                else
                {
                    service.setID(null);
                }
                if(!textSeachName.getText().equals(textSeachName.getLabelText()))
                {
                    service.setServiceName(textSeachName.getText());
                }
                else
                {
                    service.setServiceName(null);
                }

                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("#");
                model.addColumn("Tên Dịch Vụ");
                model.addColumn("Giá");
                model.addColumn("Mô tả");
                String formattedAmount;
                Service_Dao service_Dao = new Service_DaoImpl();
                ArrayList<Service> serverList = service_Dao.getServiceList(service);
                for (Service object : serverList) {
                    ArrayList<Object> item = new ArrayList<>();
                    item.add(object.getID());
                    item.add(object.getServiceName());
                    formattedAmount = currencyFormater.format(object.getServicePrice());
                    item.add(formattedAmount);
                    item.add(object.getServiceDescription());
                    //tạo hàng
                    model.addRow(item.toArray());
                }
                //đưa dữ liệu từ model vào bảng
                table.fixTable(jScrollPane1);
                table.setModel(model);
                table.setDefaultEditor(Object.class, null);
            }
            default -> {
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.SPatients Card;
    private javax.swing.JLabel TitleTable;
    private Swing.Button.Button2 btnNew;
    private Swing.Button.Button2 btnSearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private Swing.Table.Table table;
    private Swing.Textfield.TextFieldSuggestion textSeachID;
    private Swing.Textfield.TextFieldSuggestion textSeachName;
    // End of variables declaration//GEN-END:variables
}
