/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Form.User;

import DatabaseDao.User_Dao;
import DatabaseDaoImpl.User_DaoImpl;
import Enum.TypeRoleName;
import Model.User;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.table.DefaultTableModel;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import swing.TableBuild.EventAction;
import swing.TableBuild.EventAction_Main;
import swing.TableBuild.ModelAction;
import swing.TableBuild.ModelBadge;
import swing.TableBuild.ModelProfile;

/**
 *
 * @author YAN
 */
public class UserList extends javax.swing.JPanel {
    
    private User user;
    private JLayeredPane body;
    private Locale locale;
    private NumberFormat currentFormat;
    private Component back;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public UserList() {
        initComponents();
    }
    
    public UserList(JLayeredPane main, User user, Component back)
    {
        initComponents();
        this.user = user;
        this.body = main;
        this.back = back;
        this.locale = new Locale("en", "US");
        this.currentFormat = NumberFormat.getCurrencyInstance(locale);
        this.currentFormat.setMaximumFractionDigits(0);
        table1.fixTable(jScrollPane1);
        DataUser();
        DefaultComboBoxModel<TypeRoleName> modelRoleName = new DefaultComboBoxModel<>(TypeRoleName.values());
        JTypeUser.setModel(modelRoleName);
        InitData(new User(null, TypeRoleName.None.toString()));
    }

    private void InitData(User userClicked)
    {
        EventAction eventAction = new EventAction_Main(this.body, this.table1, user);
        //int i = 1;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("       #");
        model.addColumn("Họ Và Tên");
        model.addColumn("Giới Tính");
        model.addColumn("Loại Tài Khoán");
        model.addColumn("Chức Năng");
        User_Dao users = new User_DaoImpl();
        ArrayList<User> userlist = users.getUserList(userClicked);

        for(User object : userlist)
        {
            ModelBadge modelBadge;
            if(object.getRoleName().equals(TypeRoleName.Admin.toString()))
            {
                modelBadge = new ModelBadge(  object.getRoleName(), new Color (255,110,110), new Color(255,216,216));
            }
            else if(object.getRoleName().equals(TypeRoleName.Custom.toString()))
            {
                modelBadge = new ModelBadge(  object.getRoleName(), new Color (13,202,43), new Color(222,255,228));
            }
            else
            {
                modelBadge = new ModelBadge(  object.getRoleName(), new Color (245,118,47), new Color(255,224,187));
            }
            Object[] obj = new Object[]{
                new ModelProfile(object.getIcon(), object.getID()),
                object.getFullName(),
                object.getGender(),
                modelBadge ,
                new ModelAction(object, eventAction)
            };
            model.addRow(obj);
        }
        table1.setModel(model);
        table1.setDefaultEditor(Object.class, null);
        System.out.println("Form.User.UserList.InitData() Lenght " + userlist.size());
        for(User obj : userlist)
        {
            System.out.println("Form.User.UserList.InitData() + " + obj.getFullName());
        }
        
        table1.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
                int row = table1.rowAtPoint(e.getPoint());
                System.out.println(".mouseClicked() test + " + userlist.size());
                if (row >= 0 && row < userlist.size()) {
                    SetDataUser(userlist.get(row));
                }
            }
        });
    }
    
    private void SetDataUser(User userclicked)
    {
        txtFullName.setText(userclicked.getFullName());
        txtAddress.setText(userclicked.getAddress());
        txtPoint.setText("10");
        txtID.setText(userclicked.getID().toString());
        txtPhone.setText(userclicked.getPhone());
        txtBirthday.setText(dateFormat.format(userclicked.getBirthday()));
        imgavatar.setImage(userclicked.getIcon());
    }
    
    private void DataUser()
    {
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        headerBody1.init(null, 
                this.user, "User Management", this.back, this.body);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new swing.Panel.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtFullName = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtID = new javax.swing.JLabel();
        txtPhone = new javax.swing.JLabel();
        txtAddress = new javax.swing.JLabel();
        txtBirthday = new javax.swing.JLabel();
        txtPoint = new javax.swing.JLabel();
        BgTypeUser = new swing.Badge.Badge();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtNumberOrder = new javax.swing.JLabel();
        btnEdit = new swing.Button.ButtonEdit();
        imgavatar = new swing.Image.ImageLinear();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new swing.TableBuild.Table();
        headerBody1 = new Components.HeaderBody();
        JTypeUser = new swing.Combobox.ComboBoxSuggestion();
        btnSearch = new swing.Button.ButtonEdit();
        txtSFullName = new javax.swing.JTextField();

        setOpaque(false);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Inter", 1, 20)); // NOI18N
        jLabel1.setText("Profile User");

        jPanel1.setOpaque(false);

        txtFullName.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        txtFullName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtFullName.setText("Trần Cao Minh Thắng");

        jLabel4.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(23, 34, 56));
        jLabel4.setText("INFORMATION");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(53, 53, 53));
        jLabel5.setText("ID: ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(53, 53, 53));
        jLabel6.setText("Number Phone:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(53, 53, 53));
        jLabel7.setText("Birthday: ");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(53, 53, 53));
        jLabel8.setText("Address:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(53, 53, 53));
        jLabel9.setText("Accumulate Points:");

        txtID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtID.setForeground(new java.awt.Color(53, 53, 53));
        txtID.setText("S001");

        txtPhone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPhone.setForeground(new java.awt.Color(53, 53, 53));
        txtPhone.setText("098 7240 202");

        txtAddress.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtAddress.setForeground(new java.awt.Color(53, 53, 53));
        txtAddress.setText("199 Dien Bien Phu, Binh Thanh, Ho Chi Minh");

        txtBirthday.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBirthday.setForeground(new java.awt.Color(53, 53, 53));
        txtBirthday.setText("20/02/2002");

        txtPoint.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPoint.setForeground(new java.awt.Color(53, 53, 53));
        txtPoint.setText("$7,500");

        BgTypeUser.setText("Custom");
        BgTypeUser.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        BgTypeUser.setRadius(15);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(23, 34, 56));
        jLabel15.setText("ORDER HISTORY");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(23, 34, 56));
        jLabel18.setText("Number of orders:");

        txtNumberOrder.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNumberOrder.setForeground(new java.awt.Color(53, 53, 53));
        txtNumberOrder.setText("20");

        btnEdit.setBackground(new java.awt.Color(23, 34, 56));
        btnEdit.setText("Edit Profile");
        btnEdit.setBackgroundColor(new java.awt.Color(23, 34, 56));
        btnEdit.setBorderColor(new java.awt.Color(0, 0, 0));
        btnEdit.setColorHover(new java.awt.Color(23, 34, 56));
        btnEdit.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        btnEdit.setRadius(10);

        imgavatar.setBorderSize(3);
        imgavatar.setBorderSpace(2);
        imgavatar.setGradientColor2(new java.awt.Color(51, 0, 204));
        imgavatar.setImage(new javax.swing.ImageIcon(getClass().getResource("/Images/Avatar/avatarpatient.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPhone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNumberOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(imgavatar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(BgTypeUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(imgavatar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BgTypeUser, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPhone))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAddress)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBirthday)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtPoint))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtNumberOrder))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 16, Short.MAX_VALUE)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Province/City", "Position", "Number Phone", "Edit"
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

        btnSearch.setBackground(new java.awt.Color(23, 34, 56));
        btnSearch.setText("Search");
        btnSearch.setBackgroundColor(new java.awt.Color(23, 34, 56));
        btnSearch.setBorderColor(new java.awt.Color(23, 34, 56));
        btnSearch.setColorHover(new java.awt.Color(23, 34, 56));
        btnSearch.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        btnSearch.setRadius(25);
        btnSearch.setSizeBorder(1);
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        txtSFullName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(headerBody1, javax.swing.GroupLayout.DEFAULT_SIZE, 958, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(JTypeUser, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1))
                        .addGap(18, 18, 18)
                        .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headerBody1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTypeUser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSFullName, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        User userClicked = new User();
        userClicked.setFullName(txtSFullName.getText().trim());
        userClicked.setRoleName(((TypeRoleName)JTypeUser.getSelectedItem()).toString());
        
        System.out.println("Form.User.UserList.btnSearchActionPerformed() + " + userClicked.getRoleName());
        System.out.println("Form.User.UserList.btnSearchActionPerformed() + " + userClicked.getFullName());
        InitData(userClicked);
    }//GEN-LAST:event_btnSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Badge.Badge BgTypeUser;
    private swing.Combobox.ComboBoxSuggestion JTypeUser;
    private swing.Button.ButtonEdit btnEdit;
    private swing.Button.ButtonEdit btnSearch;
    private Components.HeaderBody headerBody1;
    private swing.Image.ImageLinear imgavatar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private swing.Panel.PanelBorder panelBorder1;
    private swing.TableBuild.Table table1;
    private javax.swing.JLabel txtAddress;
    private javax.swing.JLabel txtBirthday;
    private javax.swing.JLabel txtFullName;
    private javax.swing.JLabel txtID;
    private javax.swing.JLabel txtNumberOrder;
    private javax.swing.JLabel txtPhone;
    private javax.swing.JLabel txtPoint;
    private javax.swing.JTextField txtSFullName;
    // End of variables declaration//GEN-END:variables
}
