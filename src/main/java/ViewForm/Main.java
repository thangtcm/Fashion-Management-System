/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ViewForm;


import Enum.TypeList;
import Form.Home;
import Form.List.ListOfObject;
import Model.Employee;
import Model.ModelMenu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author couni
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    private final JPanel main = new JPanel();
    private MigLayout layout;
    private Animator animator;
    private boolean menuShow;
    private Employee employee;
    
    public Main() {
        initComponents();
        init();
    }

    public Main(Employee employee)
    {
        initComponents();
        this.employee = employee;
        init();  
    }
    
    private void init() {
        layout = new MigLayout("fill", "0[]10[]0", "0[fill]0");
        body.setLayout(layout);
        main.setOpaque(false);
        main.setLayout(new BorderLayout());

        ActionListener menuEvent = (ActionEvent ae) -> {
            if (!animator.isRunning()) {
                animator.start();
            }
        };
        menu.addEventLogo((ActionEvent ae) -> {
            menuEvent.actionPerformed(ae);
        });
        menu.addEventMenu(menuEvent);
        
        menu.setEvent((int index) -> {
            switch (index) {
                case 0 -> showForm(new Home(this.employee, main));
                case 1 -> showForm(new ListOfObject(main, TypeList.Patient, this.employee));
                //case 2 -> showForm(new ListOfObject(main, TypeList.Medical));
                case 2 -> showForm(new ListOfObject(main, TypeList.Drug, this.employee));
                case 3 -> showForm(new ListOfObject(main, TypeList.Employee, this.employee));
                case 4 -> showForm(new ListOfObject(main, TypeList.Services, this.employee));
                default -> {
                }
            }
        });
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        menu.addMenu(new ModelMenu("Profile", new ImageIcon(getClass().getResource("/Images/Icons/home.png"))));
        //menu.addMenu(new ModelMenu("Message", new ImageIcon(getClass().getResource("/Images/Icons/procedures.png"))));
        menu.addMenu(new ModelMenu("Patient", IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SUPERVISOR_ACCOUNT, 28, Color.WHITE)));
        //menu.addMenu(new ModelMenu("Medical", IconFontSwing.buildIcon(GoogleMaterialDesignIcons.LOCAL_HOSPITAL, 28, Color.WHITE)));// Phieu Kham Benh
        //menu.addMenu(new ModelMenu("Prescription", new ImageIcon(getClass().getResource("/Images/Icons/CardMedicine.png"))));// Phieu Linh Thuoc
        menu.addMenu(new ModelMenu("Drug", new ImageIcon(getClass().getResource("/Images/Icons/Drug.png"))));// Quan ly Thuoc
        menu.addMenu(new ModelMenu("Employee", new ImageIcon(getClass().getResource("/Images/Icons/users.png"))));// Nhan Vien
        //menu.addMenu(new ModelMenu("Report", new ImageIcon(getClass().getResource("/Images/Icons/layers.png")))); // Bao cao     
        menu.addMenu(new ModelMenu("Services", new ImageIcon(getClass().getResource("/Images/Icons/Service.png"))));
        body.add(menu, "w 200!");
        body.add(rightMain, BorderLayout.EAST);
        body.add(main, "w 100%");
        
        rightMain.lbNameEmployee(employee.getFullName());
        TimingTarget target;
        target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menuShow) {
                    width = 60 + (150 * fraction);
                    menu.setAlpha(fraction);
                    menu.HideShowBar(true);
                } else {
                    width = 60 + (150 * (1f - fraction));
                    menu.setAlpha(1f - fraction);
                    menu.HideShowBar(false);
                }
                layout.setComponentConstraints(menu, "w " + width + "!");
                body.revalidate();
            }

            @Override
            public void end() {
                menuShow = !menuShow;
            }
        };
        animator = new Animator(400, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        showForm(new Home(this.employee, main));
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

        body = new Swing.Panel.Panel();
        menu = new Components.Menu();
        rightMain = new Components.RightMain();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(245, 245, 245));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);

        body.setBackground(new java.awt.Color(238, 245, 255));
        body.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyLayout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1012, Short.MAX_VALUE)
                .addComponent(rightMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
            .addComponent(rightMain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(() -> {
//            new Main().setVisible(true);
//        });
//    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Swing.Panel.Panel body;
    private Components.Menu menu;
    private Components.RightMain rightMain;
    // End of variables declaration//GEN-END:variables
}
