
package Form.Product;

import Enum.TypeFunction;
import Model.Products;
import Model.User;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLayeredPane;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

/**
 *
 * @author couni
 */
public class ManagementProduct extends javax.swing.JPanel {

    
    private User user;
    private JLayeredPane body;
    private Component back;
    private TypeFunction type;
    private Products product_target;
    public ManagementProduct() {
        initComponents();
        ProductEditDetails FormProduct = new ProductEditDetails();
        materialTabbed1.addTab("Product", FormProduct);
    }

    public ManagementProduct(JLayeredPane body, User user, TypeFunction type, Component back, Products product_target) {
        initComponents();
        this.user = user;
        this.body = body;
        this.back = back;
        this.type = type;
        this.product_target = product_target;
        OverView FormCategory = new OverView(user);
        ProductEditDetails FormProduct = new ProductEditDetails(body, user, this.type, this.product_target);
        materialTabbed1.addTab("Category", FormCategory);
        materialTabbed1.addTab("Product", FormProduct);
        DataUser();
    }
    
    private void DataUser()
    {
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        headerBody1.init(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.ARROW_BACK, 30, new Color(23,34,56)), 
                this.user, "Management Product", this.back, this.body);
    }
   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        materialTabbed1 = new swing.Panel.MaterialTabbed();
        headerBody1 = new Components.HeaderBody();

        setOpaque(false);

        materialTabbed1.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(headerBody1, javax.swing.GroupLayout.DEFAULT_SIZE, 968, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(materialTabbed1, javax.swing.GroupLayout.PREFERRED_SIZE, 882, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headerBody1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(materialTabbed1, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Components.HeaderBody headerBody1;
    private swing.Panel.MaterialTabbed materialTabbed1;
    // End of variables declaration//GEN-END:variables
}
