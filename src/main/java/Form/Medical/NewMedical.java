/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Form.Medical;

import DatabaseAccessObject_DAO.MedicalExamination_Dao;
import DatabaseAccessObject_DAO.Patient_Dao;
import DatabaseAccessObject_Impl.MedicalExamination_DaoImpl;
import DatabaseAccessObject_Impl.Patient_DaoImpl;
import Dialog.Swal_Notification;
import Enum.TypeInterface;
import Enum.TypeList;
import Enum.TypeNotification;
import Form.List.ListOfObject;
import Model.Employee;
import Model.MedicalExamination;
import Model.Patient;
import ViewForm.Main;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JPanel;


/**
 *
 * @author YAN
 */
public class NewMedical extends javax.swing.JPanel {

    /**
     * Creates new form ServiceBill
     */
    private final JPanel main;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final Employee employee;
    private final TypeInterface type;
    private MedicalExamination medical_target;
    private Patient patient_target;
    public NewMedical(JPanel main, TypeInterface type, Employee employee, Patient patient_target) {
        initComponents();
        this.main = main;
        this.employee = employee;
        this.type = type;
        this.patient_target = patient_target;
        
        init(type);
    }
    
    public NewMedical(JPanel main, TypeInterface type, Employee employee, MedicalExamination medical_target) {
        initComponents();
        this.main = main;
        this.employee = employee;

        this.type = type;
        this.medical_target = medical_target;
        init(type);
    }
    
    private void init(TypeInterface type)
    {
        Date date = new Date();
        MedicalExamination_Dao medical_Dao = new MedicalExamination_DaoImpl();
        if(type == TypeInterface.Create)
        {
            txtCID.setText(this.patient_target.getID().toString());
            txtName.setText(this.patient_target.getFullName());
            txtMedicalDate.setText(dateFormat.format(date));
            txtNumber.setText(String.valueOf(medical_Dao.Count("PatientID = " + this.patient_target.getID()) ));
            txtIllnesses.setText("");
            txtNote.setText("");
            txtSympton.setText("");
        }
        else{
            txtCID.setText(this.medical_target.getPatient().getID().toString());
            txtName.setText(this.medical_target.getPatient().getFullName());
            txtMedicalDate.setText(dateFormat.format(this.medical_target.getMedicalDate()));
            txtNumber.setText(String.valueOf(medical_Dao.Count("PatientID = " + this.medical_target.getPatient().getID()) ));
            txtIllnesses.setText(this.medical_target.getIllnesses());
            txtNote.setText(this.medical_target.getNote());
            txtSympton.setText(this.medical_target.getSymptom());
        }
    }

    private void initCreate()
    {
        MedicalExamination medical = new MedicalExamination();
        medical.setEmployee(this.employee);
        medical.setPatient(this.medical_target.getPatient());
        medical.setMedicalDate(java.sql.Date.valueOf(txtMedicalDate.getText()));
        medical.setIllnesses(txtIllnesses.getText());
        medical.setSymptom(txtSympton.getText());
        medical.setNote(txtNote.getText());
        
        MedicalExamination_Dao medical_Dao = new MedicalExamination_DaoImpl();
        if(type == TypeInterface.Create)
        {
            if(medical_Dao.AddMedicalExamination(medical))
            {
                showMessage("<html><div style ='text-align:center'>Bạn vừa tạo thành công phiếu khám bệnh của bệnh nhân <br>" + medical.getPatient().getFullName() + " !!</div></html>", TypeNotification.Success);
            }
            else
            {
                showMessage("<html><div style ='text-align:center'>Đã có lỗi xảy ra<br>Có vẻ bạn nhập thông tin phiếu của bệnh nhân " + medical.getPatient().getFullName() + " bị sai dữ liệu!!</div></html>", TypeNotification.Error);
            }
        }
        else
        {
            medical.setID(this.medical_target.getID());
            if(medical_Dao.Update_MedicalExamination(medical))
            {
                showMessage("<html><div style ='text-align:center'>Bạn vừa cập nhật thành công phiếu khám bệnh của bệnh nhân <br>" + medical.getPatient().getFullName() + " !!</div></html>", TypeNotification.Success);
            }
            else
            {
                showMessage("<html><div style ='text-align:center'>Đã có lỗi xảy ra<br>Có vẻ bạn nhập thông tin phiếu của bệnh nhân " + medical.getPatient().getFullName() + " bị sai dữ liệu!!</div></html>", TypeNotification.Error);
            }
        }
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
        txtName = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        textAreaScroll1 = new Swing.TextField.TextAreaScroll();
        txtIllnesses = new Swing.TextField.TextArea();
        textAreaScroll2 = new Swing.TextField.TextAreaScroll();
        txtSympton = new Swing.TextField.TextArea();
        textAreaScroll3 = new Swing.TextField.TextAreaScroll();
        txtNote = new Swing.TextField.TextArea();
        jLabel7 = new javax.swing.JLabel();
        txtCID = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMedicalDate = new javax.swing.JLabel();
        button25 = new Swing.Button.Button2();
        jLabel9 = new javax.swing.JLabel();
        txtNumber = new javax.swing.JLabel();
        panelShadow1 = new Swing.Panel.PanelShadow();
        imgAvatar1 = new Swing.Img.ImgAvatar();

        setOpaque(false);

        TitleTable.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        TitleTable.setText("New Medical Examination");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Icons/Group 31.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        panel1.setBackground(new java.awt.Color(255, 255, 255));
        panel1.setRoundBottomLeft(55);
        panel1.setRoundBottomRight(55);
        panel1.setRoundTopLeft(55);
        panel1.setRoundTopRight(55);

        txtName.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        txtName.setText("Tran Cao Minh Thang");

        jLabel15.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(124, 124, 124));
        jLabel15.setText("Illnesses:");

        jLabel18.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(124, 124, 124));
        jLabel18.setText("Symptom:");

        jLabel19.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(124, 124, 124));
        jLabel19.setText("Note:");

        jLabel20.setForeground(new java.awt.Color(118, 136, 233));
        jLabel20.setText("View Patient's Infomation");

        textAreaScroll1.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        textAreaScroll1.setLabelText("Input Illnesses");
        textAreaScroll1.setOpaque(false);

        txtIllnesses.setBorder(null);
        txtIllnesses.setColumns(20);
        txtIllnesses.setRows(5);
        textAreaScroll1.setViewportView(txtIllnesses);

        textAreaScroll2.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        textAreaScroll2.setLabelText("Input Symptom");
        textAreaScroll2.setOpaque(false);

        txtSympton.setColumns(20);
        txtSympton.setRows(5);
        textAreaScroll2.setViewportView(txtSympton);

        textAreaScroll3.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        textAreaScroll3.setLabelText("Input Note");
        textAreaScroll3.setOpaque(false);

        txtNote.setColumns(20);
        txtNote.setRows(5);
        textAreaScroll3.setViewportView(txtNote);

        jLabel7.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(124, 124, 124));
        jLabel7.setText("Patient's CID:");

        txtCID.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        txtCID.setForeground(new java.awt.Color(124, 124, 124));
        txtCID.setText("7C7C7C");

        jLabel8.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(124, 124, 124));
        jLabel8.setText("Medical Date:");

        txtMedicalDate.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        txtMedicalDate.setForeground(new java.awt.Color(124, 124, 124));
        txtMedicalDate.setText("2023-04-04");

        button25.setForeground(new java.awt.Color(255, 255, 255));
        button25.setText("Save");
        button25.setBackgroundColor(new java.awt.Color(255, 255, 255));
        button25.setBackgroundHoverColor(new java.awt.Color(79, 98, 203));
        button25.setColorHover(new java.awt.Color(79, 98, 203));
        button25.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        button25.setRadius(15);
        button25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button25ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(124, 124, 124));
        jLabel9.setText("Number of medical visits:");

        txtNumber.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        txtNumber.setForeground(new java.awt.Color(124, 124, 124));
        txtNumber.setText("2");

        imgAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Avatar/avatarpatient.jpg"))); // NOI18N

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(imgAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imgAvatar1, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(textAreaScroll3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textAreaScroll2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textAreaScroll1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMedicalDate, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCID, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(100, 100, 100)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button25, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtName)
                        .addGap(32, 32, 32)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMedicalDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textAreaScroll2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 50, Short.MAX_VALUE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textAreaScroll1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 50, Short.MAX_VALUE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textAreaScroll3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(button25, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TitleTable, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(44, 44, 44))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button25ActionPerformed
        // TODO add your handling code here:
        initCreate();
    }//GEN-LAST:event_button25ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        showForm(new ListOfObject(main, TypeList.Patient, this.employee));
    }//GEN-LAST:event_jLabel1MouseClicked

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
    private javax.swing.JLabel TitleTable;
    private Swing.Button.Button2 button25;
    private Swing.Img.ImgAvatar imgAvatar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private Swing.Panel.Panel panel1;
    private Swing.Panel.PanelShadow panelShadow1;
    private Swing.TextField.TextAreaScroll textAreaScroll1;
    private Swing.TextField.TextAreaScroll textAreaScroll2;
    private Swing.TextField.TextAreaScroll textAreaScroll3;
    private javax.swing.JLabel txtCID;
    private Swing.TextField.TextArea txtIllnesses;
    private javax.swing.JLabel txtMedicalDate;
    private javax.swing.JLabel txtName;
    private Swing.TextField.TextArea txtNote;
    private javax.swing.JLabel txtNumber;
    private Swing.TextField.TextArea txtSympton;
    // End of variables declaration//GEN-END:variables
}
