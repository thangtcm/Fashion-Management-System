/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Form;

import Form.SlideForm.*;
import Model.User;
import View_Form.MainAdmin;
import com.raven.chart.ModelChartLine;
import dialog.Message;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import swing.Table.EventAction;
import swing.Table.ModelProfile;

/**
 *
 * @author couni
 */
public class Dashboard extends javax.swing.JPanel {

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();
        slideshow1.initSlideshow(new Slide1(), new Slide2(), new Slide3(), new Slide4());
        table1.fixTable(jScrollPane1);
        initTableData();
        initDataLine();
    }
    
    private void initTableData() {
        EventAction eventAction = new EventAction() {
            @Override
            public void delete(User user, int row) {
                //int rowCount = 
                //if()
                if (row != -1) {
                    ModelProfile selectedStudent = (ModelProfile) table1.getValueAt(row, 0);
                    if (showMessage("Delete Student : " + user.getFulName() + "(ID: " + selectedStudent.getId() + ")")) {
                        // Xóa sinh viên tại vị trí rowIndex trong dữ liệu của JTable
                        int UserId = selectedStudent.getId();
                        //Hàm Xóa
                        System.out.println("UserId = " + UserId);
                        
                        if (table1.isEditing()) {
                            table1.getCellEditor().stopCellEditing();
                        }
                        DefaultTableModel model = (DefaultTableModel) table1.getModel();
                        model.removeRow(row);
                        
                        // ...
                        System.out.println("User click OK");
                    } else {
                        System.out.println("User click Cancel");
                    }
                } else {
                    System.out.println("No row selected");
                }
            }

            @Override
            public void update(User user, int row) {
                int rowIndex = table1.getSelectedRow();
                if (rowIndex >= 0) {
                    ModelProfile selectedStudent = (ModelProfile) table1.getValueAt(rowIndex, 0);
                    if (showMessage("Update Student : " + user.getFulName() + "(ID: " + selectedStudent.getId() + ")")) {
                        // Cập nhật thông tin sinh viên tại vị trí rowIndex trong dữ liệu của JTable
                        // ...
                        System.out.println("User click OK");
                    } else {
                        System.out.println("User click Cancel");
                    }
                } else {
                    System.out.println("No row selected");
                }
            }
        };
        int i = 1;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("       #");
        model.addColumn("Họ Và Tên");
        model.addColumn("Giới Tính");
        model.addColumn("Số Điện Thoại");
        model.addColumn("Chức Vụ");
        model.addColumn("Chức Năng");
        
        model.addRow(new User(new ImageIcon(getClass().getResource("/Images/Avatar/avatar.jpg")), "Trần Cao Minh Thắng", "Male", "Admin", i++, i).toRowTable(eventAction));
        model.addRow(new User(new ImageIcon(getClass().getResource("/Images/Avatar/avatar.jpg")), "Lê Thảo Duyên", "Female", "Admin", i++, i).toRowTable(eventAction));
        model.addRow(new User(new ImageIcon(getClass().getResource("/Images/Avatar/avatar.jpg")), "Lê Ngọc Đình Đình", "Female", "Admin", i++, i).toRowTable(eventAction));
        model.addRow(new User(new ImageIcon(getClass().getResource("/Images/Avatar/avatar.jpg")), "Nguyễn Tuấn Khoa", "Male", "Admin", i++, i).toRowTable(eventAction));
        model.addRow(new User(new ImageIcon(getClass().getResource("/Images/Avatar/avatar.jpg")), "Hồ Lâm Gia Khánh", "Male", "Admin", i++, i).toRowTable(eventAction));
        table1.setModel(model);
    }
    
    private boolean showMessage(String message) {
        Message obj = new Message(MainAdmin.getFrames()[0], true);
        obj.showMessage(message);
        return obj.isOk();
    }
    
    private void initDataLine() {

        //  Test data chart line
        List<ModelChartLine> list = new ArrayList<>();
        list.add(new ModelChartLine("Monday", 10));
        list.add(new ModelChartLine("Tuesday", 150));
        list.add(new ModelChartLine("Wednesday", 80));
        list.add(new ModelChartLine("Thursday", 100));
        list.add(new ModelChartLine("Friday", 125));
        list.add(new ModelChartLine("Saturday", 80));
        list.add(new ModelChartLine("Sunday", 200));
        chartLine1.setModel(list);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        panelBorder1 = new swing.Panel.PanelBorder();
        cardDashboard1 = new swing.Card.CardDashboard();
        cardDashboard2 = new swing.Card.CardDashboard();
        cardDashboard4 = new swing.Card.CardDashboard();
        chartLine1 = new com.raven.chart.ChartLine();
        slideshow1 = new swing.SlideShow.Slideshow();
        jPanel5 = new javax.swing.JPanel();
        imageAvatar4 = new swing.Image.ImageAvatar();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new swing.Table.Table();
        jLabel2 = new javax.swing.JLabel();

        setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Inter", 1, 26)); // NOI18N
        jLabel1.setText("Sales Dashboard");

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));
        panelBorder1.setForeground(new java.awt.Color(132, 132, 132));

        cardDashboard1.setColor1(new java.awt.Color(219, 226, 250));
        cardDashboard1.setColor2(new java.awt.Color(173, 189, 240));

        cardDashboard2.setColor1(new java.awt.Color(249, 229, 211));
        cardDashboard2.setColor2(new java.awt.Color(251, 183, 122));

        cardDashboard4.setColor1(new java.awt.Color(252, 228, 208));
        cardDashboard4.setColor2(new java.awt.Color(251, 183, 122));

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chartLine1, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cardDashboard4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cardDashboard1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cardDashboard2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardDashboard4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardDashboard2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardDashboard1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(chartLine1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        slideshow1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setOpaque(false);

        imageAvatar4.setBorderSize(0);
        imageAvatar4.setImage(new javax.swing.ImageIcon(getClass().getResource("/Images/Avatar/avatar.jpg"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Inter", 1, 16)); // NOI18N
        jLabel6.setText("Trần Cao Minh Thắng");

        jLabel7.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(181, 180, 180));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Admin");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(imageAvatar4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addComponent(imageAvatar4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Profile", "Họ Và Tên", "Giới Tính", "Số Điện Thoại", "Chức Vụ", "Chức Năng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table1);

        jLabel2.setFont(new java.awt.Font("Inter", 1, 26)); // NOI18N
        jLabel2.setText("Employee List");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelBorder1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(slideshow1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slideshow1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Card.CardDashboard cardDashboard1;
    private swing.Card.CardDashboard cardDashboard2;
    private swing.Card.CardDashboard cardDashboard4;
    private com.raven.chart.ChartLine chartLine1;
    private swing.Image.ImageAvatar imageAvatar4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private swing.Panel.PanelBorder panelBorder1;
    private swing.SlideShow.Slideshow slideshow1;
    private swing.Table.Table table1;
    // End of variables declaration//GEN-END:variables
}
