/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Form.User;

import DatabaseDao.User_Dao;
import DatabaseDaoImpl.User_DaoImpl;
import Enum.TypeGender;
import Enum.TypeNotification;
import Enum.TypeRoleName;
import Model.User;
import static Services.Notification.showConfirm;
import static Services.Notification.showMessage;
import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLayeredPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author couni
 */
public class ProfileEdit extends javax.swing.JPanel {


    private User user;
    private JLayeredPane body;
    private User userTarget;
    private Component back;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private String NameFile = null;
    File selectedFile = null;
    public ProfileEdit(JLayeredPane body, User user, User userTarget, Component back) {
        initComponents();
        this.body = body;
        this.user = user;
        this.back = back;
        this.userTarget = userTarget;
        DataUser();
        InitData();
    }
    
    public ProfileEdit()
    {
        initComponents();
    }
    
    private void InitData()
    {
        DefaultComboBoxModel<TypeRoleName> modelRoleName = new DefaultComboBoxModel<>(TypeRoleName.values());
        JRoleName.setModel(modelRoleName);
        DefaultComboBoxModel<TypeGender> modelGender = new DefaultComboBoxModel<>(TypeGender.values());
        JGender.setModel(modelGender);
        if(userTarget != null)
        {
            imageAvatar.setImage(userTarget.getIcon());
            txtFullName.setText(userTarget.getFullName());
            txtEmail.setText(userTarget.getEmail());
            txtPhone.setText(userTarget.getPhone());
            txtShowName.setText(userTarget.getFullName());
            txtShowUserId.setText(userTarget.getID().toString());
            txtShowUserName.setText(userTarget.getUserName());
            JBirthday.setDate(userTarget.getBirthday());
            JRoleName.setSelectedItem(TypeRoleName.valueOf(userTarget.getRoleName()));
            JGender.setSelectedItem(TypeGender.valueOf(userTarget.getGender()));
        }
    }

    private void DataUser()
    {
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        headerBody1.init(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.ARROW_BACK, 30, new Color(23,34,56)), 
                this.user, "Edit User", this.back, this.body);
    }
    
    private void ImportData()
    {
        userTarget.setFullName(txtFullName.getText());
        userTarget.setAddress(txtAddress.getText());
        
        userTarget.setAvartarUrl(ImportImage());
        userTarget.setEmail(txtEmail.getText());
        String birthday = dateFormat.format(JBirthday.getDate());
        userTarget.setBirthday(java.sql.Date.valueOf(birthday));
        userTarget.setGender(JGender.getSelectedItem().toString());
        User_Dao user_Dao = new User_DaoImpl();
        user_Dao.Update_User(userTarget);
    }
    
    //Xử lí thao tác upload image
    private void saveFile(byte[] data, String filename) throws IOException {
        File dir = new File("/src/main/java/Images/Upload/"); // tạo đối tượng File cho thư mục lưu trữ
        if (!dir.exists()) { // nếu thư mục không tồn tại, tạo mới
            dir.mkdirs();
        }
        try (FileOutputStream out = new FileOutputStream(filename)) {
            out.write(data);
        }
    }
    
    
    private String ImportImage()
    {
        if(NameFile == null)
            return userTarget.getAvartarUrl();
        String absolutePath = System.getProperty("user.dir");
        String extension = FilenameUtils.getExtension(NameFile); 
        String newName = FilenameUtils.getBaseName(NameFile) + "_" + System.currentTimeMillis() + "." + extension; // tạo tên file mới
        String PathFile = "/Images/Upload/"+ newName;
        String Path = absolutePath + "/src/main/java" + PathFile;
        
        System.out.println("\n\t FileName : " + NameFile);
        try {
            byte[] data;
            try (FileInputStream fis = new FileInputStream(selectedFile)) {
                data = new byte[(int) selectedFile.length()];
                fis.read(data);
            }
            saveFile(data, Path);
            String fullPath = new File(Path).getAbsolutePath();
            System.out.println("File saved to: " + fullPath);
            System.out.println("File saved to: " + PathFile);
            return PathFile;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return userTarget.getAvartarUrl();
    }
    
    private void UploadFile()
    {
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.dir")));

         //filter the files
         FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","png");
         file.addChoosableFileFilter(filter);
         int result = file.showSaveDialog(null);
          //if the user click on save in Jfilechooser
         if(result == JFileChooser.APPROVE_OPTION){
            selectedFile = file.getSelectedFile();
            //dataFile = Files.readAllBytes(selectedFile.toPath());
            NameFile = selectedFile.getName();
            String filename = selectedFile.getAbsolutePath();
             System.out.println("Path Image " + filename);
            imageAvatar.setImage(new ImageIcon(filename));
            imageAvatar.repaint();
            showMessage("File uploaded successfully", TypeNotification.Success);
         }
         else if(result == JFileChooser.CANCEL_OPTION){
             System.out.println("No File Select");
         }
    }
    
    //Done
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerBody1 = new Components.HeaderBody();
        panelShadow1 = new swing.Panel.PanelShadow();
        imageAvatar = new swing.Image.ImageAvatar();
        buttonEdit1 = new swing.Button.ButtonEdit();
        txtShowName = new javax.swing.JLabel();
        Badge = new swing.Badge.Badge();
        jLabel5 = new javax.swing.JLabel();
        txtShowUserId = new javax.swing.JLabel();
        txtShowUserName = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        panelShadow2 = new swing.Panel.PanelShadow();
        buttonEdit2 = new swing.Button.ButtonEdit();
        jLabel1 = new javax.swing.JLabel();
        textAreaScroll1 = new swing.Field.TextAreaScroll();
        txtAddress = new swing.Field.TextArea();
        JGender = new swing.Combobox.ComboBoxSuggestion();
        JBirthday = new com.toedter.calendar.JDateChooser();
        textAreaScroll2 = new swing.Field.TextAreaScroll();
        txtPhone = new swing.Field.TextArea();
        textAreaScroll3 = new swing.Field.TextAreaScroll();
        txtFullName = new swing.Field.TextArea();
        textAreaScroll4 = new swing.Field.TextAreaScroll();
        txtEmail = new swing.Field.TextArea();
        JRoleName = new swing.Combobox.ComboBoxSuggestion();

        setBackground(new java.awt.Color(242, 246, 253));

        panelShadow1.setShadowSize(6);

        imageAvatar.setBorderSize(0);
        imageAvatar.setImage(new javax.swing.ImageIcon(getClass().getResource("/Images/Avatar/avatarpatient.jpg"))); // NOI18N

        buttonEdit1.setBackground(new java.awt.Color(51, 51, 255));
        buttonEdit1.setText("Upload Avatar");
        buttonEdit1.setBackgroundColor(new java.awt.Color(51, 51, 255));
        buttonEdit1.setBorderColor(new java.awt.Color(51, 51, 255));
        buttonEdit1.setColorHover(new java.awt.Color(51, 51, 255));
        buttonEdit1.setFont(new java.awt.Font("Inter", 1, 12)); // NOI18N
        buttonEdit1.setRadius(15);
        buttonEdit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEdit1ActionPerformed(evt);
            }
        });

        txtShowName.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        txtShowName.setForeground(new java.awt.Color(23, 34, 56));
        txtShowName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtShowName.setText("Trần Cao Minh Thắng");

        Badge.setBackground(new java.awt.Color(240, 240, 247));
        Badge.setForeground(new java.awt.Color(127, 155, 255));
        Badge.setText("Custom");
        Badge.setBorderColor(new java.awt.Color(127, 155, 255));
        Badge.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        Badge.setRadius(25);

        jLabel5.setFont(new java.awt.Font("Inter", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(23, 34, 56));
        jLabel5.setText("ID:");

        txtShowUserId.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        txtShowUserId.setForeground(new java.awt.Color(102, 102, 102));
        txtShowUserId.setText("111");

        txtShowUserName.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        txtShowUserName.setForeground(new java.awt.Color(102, 102, 102));
        txtShowUserName.setText("Admin");

        jLabel7.setFont(new java.awt.Font("Inter", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(23, 34, 56));
        jLabel7.setText("UserName:");

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtShowName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap(119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(imageAvatar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonEdit1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(120, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                        .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtShowUserId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtShowUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                        .addComponent(Badge, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119))))
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonEdit1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtShowName, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Badge, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtShowUserId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtShowUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelShadow2.setShadowSize(6);

        buttonEdit2.setBackground(new java.awt.Color(51, 51, 255));
        buttonEdit2.setText("SAVE");
        buttonEdit2.setBackgroundColor(new java.awt.Color(51, 51, 255));
        buttonEdit2.setBorderColor(new java.awt.Color(51, 51, 255));
        buttonEdit2.setColorHover(new java.awt.Color(51, 51, 255));
        buttonEdit2.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        buttonEdit2.setRadius(15);
        buttonEdit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEdit2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        jLabel1.setText("Information");

        textAreaScroll1.setBackground(new java.awt.Color(242, 246, 253));
        textAreaScroll1.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        textAreaScroll1.setLabelText("Address");
        textAreaScroll1.setOpaque(false);

        txtAddress.setBackground(new java.awt.Color(242, 246, 253));
        txtAddress.setColumns(20);
        txtAddress.setRows(5);
        txtAddress.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        textAreaScroll1.setViewportView(txtAddress);

        JBirthday.setBackground(new java.awt.Color(242, 246, 253));
        JBirthday.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N

        textAreaScroll2.setBackground(new java.awt.Color(242, 246, 253));
        textAreaScroll2.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        textAreaScroll2.setLabelText("Phone");
        textAreaScroll2.setOpaque(false);

        txtPhone.setBackground(new java.awt.Color(242, 246, 253));
        txtPhone.setColumns(20);
        txtPhone.setRows(5);
        txtPhone.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        textAreaScroll2.setViewportView(txtPhone);

        textAreaScroll3.setBackground(new java.awt.Color(242, 246, 253));
        textAreaScroll3.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        textAreaScroll3.setLabelText("Full Name");
        textAreaScroll3.setOpaque(false);

        txtFullName.setBackground(new java.awt.Color(242, 246, 253));
        txtFullName.setColumns(20);
        txtFullName.setRows(5);
        txtFullName.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        textAreaScroll3.setViewportView(txtFullName);

        textAreaScroll4.setBackground(new java.awt.Color(242, 246, 253));
        textAreaScroll4.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        textAreaScroll4.setLabelText("Email");
        textAreaScroll4.setOpaque(false);

        txtEmail.setBackground(new java.awt.Color(242, 246, 253));
        txtEmail.setColumns(20);
        txtEmail.setRows(5);
        txtEmail.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        textAreaScroll4.setViewportView(txtEmail);

        javax.swing.GroupLayout panelShadow2Layout = new javax.swing.GroupLayout(panelShadow2);
        panelShadow2.setLayout(panelShadow2Layout);
        panelShadow2Layout.setHorizontalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow2Layout.createSequentialGroup()
                .addGap(0, 34, Short.MAX_VALUE)
                .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonEdit2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow2Layout.createSequentialGroup()
                        .addGroup(panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textAreaScroll1, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                            .addComponent(textAreaScroll3, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textAreaScroll4, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textAreaScroll2, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JGender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JRoleName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(11, 11, 11)))
                .addGap(15, 15, 15))
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        panelShadow2Layout.setVerticalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textAreaScroll3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(textAreaScroll4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(textAreaScroll2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(textAreaScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JGender, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JRoleName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(buttonEdit2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(headerBody1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headerBody1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelShadow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEdit1ActionPerformed
        UploadFile();
    }//GEN-LAST:event_buttonEdit1ActionPerformed

    private void buttonEdit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEdit2ActionPerformed
        if(showConfirm("Are you sure ?", TypeNotification.Default))
        {
            ImportData();
        }
    }//GEN-LAST:event_buttonEdit2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Badge.Badge Badge;
    private com.toedter.calendar.JDateChooser JBirthday;
    private swing.Combobox.ComboBoxSuggestion JGender;
    private swing.Combobox.ComboBoxSuggestion JRoleName;
    private swing.Button.ButtonEdit buttonEdit1;
    private swing.Button.ButtonEdit buttonEdit2;
    private Components.HeaderBody headerBody1;
    private swing.Image.ImageAvatar imageAvatar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private swing.Panel.PanelShadow panelShadow1;
    private swing.Panel.PanelShadow panelShadow2;
    private swing.Field.TextAreaScroll textAreaScroll1;
    private swing.Field.TextAreaScroll textAreaScroll2;
    private swing.Field.TextAreaScroll textAreaScroll3;
    private swing.Field.TextAreaScroll textAreaScroll4;
    private swing.Field.TextArea txtAddress;
    private swing.Field.TextArea txtEmail;
    private swing.Field.TextArea txtFullName;
    private swing.Field.TextArea txtPhone;
    private javax.swing.JLabel txtShowName;
    private javax.swing.JLabel txtShowUserId;
    private javax.swing.JLabel txtShowUserName;
    // End of variables declaration//GEN-END:variables
}
