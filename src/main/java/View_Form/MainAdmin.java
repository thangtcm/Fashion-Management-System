/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View_Form;
import Enum.TypeFunction;
import Form.Dashboard;
import Form.Product.ManagementProduct;
import Form.Product.ProductList;
import Form.User.ProfileEdit;
import Form.User.UserList;
import Model.User;
import java.awt.Component;

/**
 *
 * @author couni
 */
public class MainAdmin extends javax.swing.JFrame {

    private User user;
    public MainAdmin() {
        initComponents();
        showForm(new Dashboard());
    }
    
    public MainAdmin(User user)
    {
        initComponents();
        this.user = user;
        showForm(new Dashboard(this.body, this.user));
        System.out.println(user.getFullName());
        menuAdmin1.InitDataAccount("<html>" + user.getFullName() + "</html>", this.user.getIcon());
    }
     
    private void showForm(Component com) {
        body.removeAll();
        body.add(com);
        body.repaint();
        body.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelBorder = new swing.Panel.PanelBorder();
        menuAdmin1 = new Components.MenuAdmin();
        body = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Management Fastion System");
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        PanelBorder.setBackground(new java.awt.Color(255, 255, 255));
        PanelBorder.setForeground(new java.awt.Color(255, 255, 255));
        PanelBorder.setOpaque(true);

        body.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout PanelBorderLayout = new javax.swing.GroupLayout(PanelBorder);
        PanelBorder.setLayout(PanelBorderLayout);
        PanelBorderLayout.setHorizontalGroup(
            PanelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBorderLayout.createSequentialGroup()
                .addComponent(menuAdmin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(body, javax.swing.GroupLayout.PREFERRED_SIZE, 984, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PanelBorderLayout.setVerticalGroup(
            PanelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuAdmin1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            .addComponent(body, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        menuAdmin1.setSelectedIndex(0);
        menuAdmin1.addEvent((int index) -> {
            switch (index) {
                case 0 -> showForm(new Dashboard(this.body, this.user));
                case 1 -> showForm(new ProfileEdit());
                case 2 -> showForm(new UserList(this.body, this.user, null));
                case 3 -> showForm(new ProductList(this.body, this.user));
                case 4 -> showForm(new ManagementProduct(body, user, TypeFunction.Create, body, null));
                default -> {
                }
            }
        });
    }//GEN-LAST:event_formWindowOpened

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Panel.PanelBorder PanelBorder;
    private javax.swing.JLayeredPane body;
    private Components.MenuAdmin menuAdmin1;
    // End of variables declaration//GEN-END:variables
}
