/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Components;

import Event.EventMenu;
import Event.EventMenuCallBack;
import Event.EventMenuSelected;
import Model.Model_Menu;
import Model.User;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import shadow.ShadowBorder;

/**
 *
 * @author couni
 */
public class MenuAdmin extends javax.swing.JPanel {

    /**
     * Creates new form MenuAdmin
     */
    private int selectedIndex = -1;
    private double menuTarget;
    private double menuLastTarget;
    private double currentLocation;
    private BufferedImage selectedImage;
    private Animator animator;
    private EventMenuCallBack callBack;
    private EventMenu event;
    private final int HeightItemMenu = 50;
    public MenuAdmin() {
        initComponents();
        init();
//        
    }
    
    public void InitDataAccount(String Name, Icon icon)
    {
        account1.InitDataAccount(Name, icon);
    }
    
    
    private void init(){
        initData();
        listMenuAdmin.addEventSelectedMenu((int index, EventMenuCallBack callBack1) -> {
            if (!animator.isRunning()) {
                if (index != selectedIndex) {
                    MenuAdmin.this.callBack = callBack1;
                    selectedIndex = index;
                    menuTarget = selectedIndex * HeightItemMenu + listMenuAdmin.getY();
                    animator.start();
                }
            }
        });
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                currentLocation = (menuTarget - menuLastTarget) * fraction;
                currentLocation += menuLastTarget;
                repaint();
            }

            @Override
            public void end() {
                menuLastTarget = menuTarget;
                callBack.call(selectedIndex);
                if (event != null) {
                    event.menuIndexChange(selectedIndex);
                }
            }
        };
        animator = new Animator(300, target);
        animator.setResolution(1);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);  
        
    }
    
    
    public void setSelectedIndex(int index) {
        selectedIndex = index;
        menuTarget = selectedIndex * HeightItemMenu + listMenuAdmin.getY();
        menuLastTarget = menuTarget;
        currentLocation = menuLastTarget;
        listMenuAdmin.selectedIndex(index);
        repaint();
    }
    
    private void initData() {
        listMenuAdmin.addItem(new Model_Menu("Home", "Dashboard", Model_Menu.MenuType.MENU));
        listMenuAdmin.addItem(new Model_Menu("Order", "Order", Model_Menu.MenuType.MENU));
        listMenuAdmin.addItem(new Model_Menu("User", "Users", Model_Menu.MenuType.MENU));
        listMenuAdmin.addItem(new Model_Menu("Product", "Products", Model_Menu.MenuType.MENU));
        listMenuAdmin.addItem(new Model_Menu("Setting", "Setting", Model_Menu.MenuType.MENU));
        listMenuAdmin.addItem(new Model_Menu("", "", Model_Menu.MenuType.EMPTY));
    }
    
    private void createImage() {
        int width = getWidth() - 30;
        selectedImage = ShadowBorder.getInstance().createShadowOut(width, HeightItemMenu, 2, 2, new Color(230,230,230));
    }

    @Override
    public void setBounds(int i, int i1, int i2, int i3) {
        super.setBounds(i, i1, i2, i3);
        createImage();
    }


    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        if (selectedIndex >= 0) {
            grphcs.drawImage(selectedImage, 15, (int) currentLocation, null);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        listMenuAdmin = new swing.Menu.ListMenuAdmin<>();
        account1 = new Components.Account();

        setBackground(new java.awt.Color(23, 34, 56));
        setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Giaza", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("GROUP 7");

        listMenuAdmin.setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
            .addGroup(layout.createSequentialGroup()
                .addComponent(listMenuAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(account1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listMenuAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(account1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void addEvent(EventMenu event) {
        this.event = event;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Components.Account account1;
    private javax.swing.JLabel jLabel2;
    private swing.Menu.ListMenuAdmin<String> listMenuAdmin;
    // End of variables declaration//GEN-END:variables
}
