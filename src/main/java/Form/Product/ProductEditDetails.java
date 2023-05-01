/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Form.Product;

import Components.ImageField;
import DatabaseDao.Category_Dao;
import DatabaseDao.ProductImage_Dao;
import DatabaseDao.ProductVariant_Dao;
import DatabaseDao.Product_Dao;
import DatabaseDaoImpl.Category_DaoImpl;
import DatabaseDaoImpl.ProductImage_DaoImpl;
import DatabaseDaoImpl.ProductVariant_DaoImpl;
import DatabaseDaoImpl.Product_DaoImpl;
import Enum.TypeFunction;
import Enum.TypeNotification;
import Model.Categories;
import Model.ProductImage;
import Model.ProductVariants;
import Model.Products;
import Model.User;
import static Services.Notification.showMessage;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLayeredPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FilenameUtils;


public class ProductEditDetails extends javax.swing.JPanel {

    
    private User user;
    private TypeFunction type;
    private Products product_target;
    private JLayeredPane body;
    public ProductEditDetails() {
        this.PathFileList = new ArrayList<>();
        initComponents();
    }
    
    public ProductEditDetails(JLayeredPane body, User user, TypeFunction type, Products product_target)
    {
        this.PathFileList = new ArrayList<>();
        initComponents();
        this.user = user;
        this.type = type;
        this.product_target = product_target;
        this.body = body;
        Init(type);
    }
    
    private void Init(TypeFunction type)
    {
        Category_Dao category_Dao = new Category_DaoImpl();
        DefaultComboBoxModel<Categories> model = new DefaultComboBoxModel<>();
        model.addAll(category_Dao.getCategoryList());
        JTypeProduct.setModel(model);
        
        if(type == TypeFunction.Create)
        {
            JTypeProduct.setSelectedIndex(-1);
        }
        if(type == TypeFunction.Edit)
        {
            txtNameProduct.setText(product_target.getProductName());
            txtDescription.setText(product_target.getDescription());
            product_target.setCategory(category_Dao.getCategory(product_target.getCategoryID()));
            JTypeProduct.setSelectedItem(product_target.getCategory());
            txtSale.setValue(product_target.getSale());
            ProductVariant_Dao productVariant_Dao = new ProductVariant_DaoImpl();
            ArrayList<ProductVariants> productVariantList = productVariant_Dao.getProductVariantList(product_target.getID());
            for(ProductVariants obj : productVariantList)
            {
                if(obj.getSize().equals("S"))
                {
                    txtPrice.setValue(obj.getPrice());
                    CSizeS.setSelected(true);
                    ASizeS.setValue(obj.getStock());
                }
                if(obj.getSize().equals("M"))
                {
                    CSizeM.setSelected(true);
                    ASizeM.setValue(obj.getStock());
                }
                if(obj.getSize().equals("L"))
                {
                    CSizeL.setSelected(true);
                    ASizeL.setValue(obj.getStock());
                }
                if(obj.getSize().equals("XL"))
                {
                    CSizeXL.setSelected(true);
                    ASizeXL.setValue(obj.getStock());
                }
            }
            
            //Xử lí Ảnh
            ProductImage_Dao productImage_Dao = new ProductImage_DaoImpl();
            ArrayList<ProductImage> productImageList = productImage_Dao.getProductImageList(product_target.getID());
            for(ProductImage obj : productImageList)
            {
                ImageField label = new ImageField();
                label.setNameFile("Old");
                label.setSize(92, 115);
                label.setImage(obj.getIcon());
                LayoutImage.add(label, 0);
                LayoutImage.revalidate();
                LayoutImage.repaint();
                label.getButton().addActionListener((ActionEvent e) -> {
                    LayoutImage.remove(label);
                    LayoutImage.revalidate();
                    LayoutImage.repaint();
                    imagelist.remove(label);
                    showMessage("Xóa ảnh thành công", TypeNotification.Success);
                });
                imagelist.add(label);
           }
        }
    }
    
    private boolean ImportData()
    {
        try{
            Products product = new Products();
            Product_Dao product_Dao = new Product_DaoImpl();
            product.setProductName(txtNameProduct.getText());
            product.setDescription(txtDescription.getText());
            product.setSale(Double.parseDouble(txtSale.getValue().toString()));
            product.setCategoryID(((Categories)JTypeProduct.getSelectedItem()).getID());
            product.setStatus(true);
            ProductVariants productVariants = new ProductVariants();
            int ProducID;
            if(type == TypeFunction.Create)
            {
                ProducID = product_Dao.AddProduct(product);
                product.setID(ProducID);
            }
            else
            {
                product.setID(product_target.getID());
                product_Dao.Update_Product(product);
                ProducID = product_target.getID();
            }
            System.out.println("Form.Product.ProductEditDetails+ " + ProducID);
            
            if(CSizeS.isSelected())
            {
                productVariants.setProduct(product);
                productVariants.setSize("S");
                productVariants.setPrice(Double.parseDouble(txtPrice.getValue().toString()));
                productVariants.setStock(Integer.parseInt(ASizeS.getValue().toString()));
                ProductVariant_Dao productVariant_Dao = new ProductVariant_DaoImpl();
                if(!productVariant_Dao.AddProductVariant(productVariants))
                    return false;
            }
            if(CSizeM.isSelected())
            {
                productVariants.setProduct(product);
                productVariants.setSize("M");
                productVariants.setPrice(Double.parseDouble(txtPrice.getValue().toString()));
                productVariants.setStock(Integer.parseInt(ASizeM.getValue().toString()));
                ProductVariant_Dao productVariant_Dao = new ProductVariant_DaoImpl();
                if(!productVariant_Dao.AddProductVariant(productVariants))
                    return false;
            }if(CSizeL.isSelected())
            {
                productVariants.setProduct(product);
                productVariants.setSize("L");
                productVariants.setPrice(Double.parseDouble(txtPrice.getValue().toString()));
                productVariants.setStock(Integer.parseInt(ASizeL.getValue().toString()));
                ProductVariant_Dao productVariant_Dao = new ProductVariant_DaoImpl();
                if(!productVariant_Dao.AddProductVariant(productVariants))
                    return false;
            }
            if(CSizeXL.isSelected())
            {
                productVariants.setProduct(product);
                productVariants.setSize("XL");
                productVariants.setPrice(Double.parseDouble(txtPrice.getValue().toString()));
                productVariants.setStock(Integer.parseInt(ASizeXL.getValue().toString()));
                ProductVariant_Dao productVariant_Dao = new ProductVariant_DaoImpl();
                if(!productVariant_Dao.AddProductVariant(productVariants))
                    return false;
            }
            ImportImage();

            for(String path : PathFileList)
            {
                ProductImage productImage = new ProductImage();
                productImage.setImageUrl(path);
                productImage.setProduct(product);

                ProductImage_Dao productImage_Dao = new ProductImage_DaoImpl();
                if(!productImage_Dao.AddProductImage(productImage))
                    return false;
            }
        }catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
        
        return true;
    }
    
    //Xử lí ảnh sản phẩm
    File selectedFile = null;
    private final ArrayList<ImageField> imagelist = new ArrayList<>();
    private final ArrayList<String> PathFileList;
    private void HandleUploadImage()
    {
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.dir")));

        //filter the files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","png");
        file.addChoosableFileFilter(filter);
        file.setAcceptAllFileFilterUsed(false);
        int result = file.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
           selectedFile = file.getSelectedFile();
           
           ImageField label = new ImageField();
           label.setNameFile(selectedFile.getName());
           String filename = selectedFile.getAbsolutePath();
           
           label.setSelectedFile(selectedFile);
           label.setSize(92, 115);
           label.setImage(new ImageIcon(filename));
           LayoutImage.add(label, 0);
           LayoutImage.revalidate();
           LayoutImage.repaint();
           showMessage("Bạn vừa tải ảnh " + label.getNameFile() + " lên thành công." , TypeNotification.Success);
           label.getButton().addActionListener((ActionEvent e) -> {
               LayoutImage.remove(label);
               LayoutImage.revalidate();
               LayoutImage.repaint();
               imagelist.remove(label);
               showMessage("Xóa ảnh thành công", TypeNotification.Success);
           });
           imagelist.add(label);
        }
        else if(result == JFileChooser.CANCEL_OPTION){
            System.out.println("No File Select");
        }
    }
    
    public void ImportImage()
    {
        String absolutePath = System.getProperty("user.dir");
        String extension, newName, PathFile, Path;
        for(ImageField obj : imagelist)
        {
            if(!obj.getNameFile().equals("Old"))
            {
                extension = FilenameUtils.getExtension(obj.getNameFile());
                newName = FilenameUtils.getBaseName(obj.getNameFile()) + "_" + System.currentTimeMillis() + "." + extension; // tạo tên file mới
                PathFile = "/Images/Products/"+ newName;
                Path = absolutePath + "/src/main/java" + PathFile;
                try {
                    byte[] data;
                    try (FileInputStream fis = new FileInputStream(obj.getSelectedFile())) {
                        data = new byte[(int) obj.getSelectedFile().length()];
                        fis.read(data);
                    }
                    saveFile(data, Path);
                    System.out.println("File uploaded successfully (FileName : " + newName + " )");
                    System.out.println("File saved to: " + PathFile);
                    PathFileList.add(PathFile);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }  
        }
    }
    
    public void saveFile(byte[] data, String filename) throws IOException {
        File dir = new File("/src/main/java/Images/Products/"); // tạo đối tượng File cho thư mục lưu trữ
        if (!dir.exists()) { // nếu thư mục không tồn tại, tạo mới
            dir.mkdirs();
        }
        try (FileOutputStream out = new FileOutputStream(filename)) {
            out.write(data);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        JTypeProduct = new swing.Combobox.ComboBoxSuggestion();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        CSizeS = new swing.Field.JCheckBoxCustom();
        ASizeS = new javax.swing.JSpinner();
        jPanel4 = new javax.swing.JPanel();
        CSizeM = new swing.Field.JCheckBoxCustom();
        ASizeM = new javax.swing.JSpinner();
        jPanel5 = new javax.swing.JPanel();
        CSizeL = new swing.Field.JCheckBoxCustom();
        ASizeL = new javax.swing.JSpinner();
        jPanel6 = new javax.swing.JPanel();
        CSizeXL = new swing.Field.JCheckBoxCustom();
        ASizeXL = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        LayoutImage = new javax.swing.JPanel();
        dashBorder1 = new swing.Panel.DashBorder();
        textAreaScroll1 = new swing.Field.TextAreaScroll();
        txtDescription = new swing.Field.TextArea();
        jLabel6 = new javax.swing.JLabel();
        txtNameProduct = new swing.Field.TextFieldRadius();
        jLabel12 = new javax.swing.JLabel();
        btnSave = new swing.Button.ButtonEdit();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JSpinner();
        txtSale = new javax.swing.JSpinner();

        setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLabel3.setText("Type Product ");

        JTypeProduct.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "b", "c", "d", "s", "s", "á", "sa", "f", "gh", "hg", "ghn", " " }));
        JTypeProduct.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jPanel2.setOpaque(false);

        CSizeS.setBackground(new java.awt.Color(51, 102, 255));
        CSizeS.setText("S");
        CSizeS.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N

        ASizeS.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        ASizeS.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000, 1));
        ASizeS.setBorder(null);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CSizeS, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ASizeS, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CSizeS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ASizeS, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jPanel3.add(jPanel2);

        jPanel4.setOpaque(false);

        CSizeM.setBackground(new java.awt.Color(51, 102, 255));
        CSizeM.setText("M");
        CSizeM.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N

        ASizeM.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        ASizeM.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000, 1));
        ASizeM.setBorder(null);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CSizeM, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ASizeM, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CSizeM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ASizeM, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jPanel3.add(jPanel4);

        jPanel5.setOpaque(false);

        CSizeL.setBackground(new java.awt.Color(51, 102, 255));
        CSizeL.setText("L");
        CSizeL.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N

        ASizeL.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        ASizeL.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000, 1));
        ASizeL.setBorder(null);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CSizeL, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ASizeL, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CSizeL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ASizeL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jPanel3.add(jPanel5);

        jPanel6.setOpaque(false);

        CSizeXL.setBackground(new java.awt.Color(51, 102, 255));
        CSizeXL.setText("XL");
        CSizeXL.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N

        ASizeXL.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        ASizeXL.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000, 1));
        ASizeXL.setBorder(null);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CSizeXL, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ASizeXL, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CSizeXL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ASizeXL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jPanel3.add(jPanel6);

        jLabel4.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLabel4.setText("Image");

        jLabel5.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLabel5.setText("Size");

        LayoutImage.setMinimumSize(new java.awt.Dimension(125, 125));
        LayoutImage.setOpaque(false);
        LayoutImage.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        dashBorder1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dashBorder1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icon/AddNew.png"))); // NOI18N
        dashBorder1.setPreferredSize(new java.awt.Dimension(100, 100));
        dashBorder1.setSizeBorder(2);
        dashBorder1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashBorder1MouseClicked(evt);
            }
        });
        LayoutImage.add(dashBorder1);

        textAreaScroll1.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        textAreaScroll1.setLabelText("Description");

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        txtDescription.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        textAreaScroll1.setViewportView(txtDescription);

        jLabel6.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLabel6.setText("Product's Name");

        txtNameProduct.setText("Input Name");
        txtNameProduct.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        txtNameProduct.setLabelText("Input Name");
        txtNameProduct.setRound(15);
        txtNameProduct.setShadowSize(new java.awt.Insets(1, 1, 1, -3));

        jLabel12.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLabel12.setText("Description");

        btnSave.setBackground(new java.awt.Color(23, 34, 56));
        btnSave.setText("Save");
        btnSave.setBackgroundColor(new java.awt.Color(23, 34, 56));
        btnSave.setBorderColor(new java.awt.Color(23, 34, 56));
        btnSave.setColorHover(new java.awt.Color(23, 34, 56));
        btnSave.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        btnSave.setRadius(15);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLabel10.setText("Price:");

        jLabel11.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        jLabel11.setText("Sale:");

        txtPrice.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        txtPrice.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000, 1));
        txtPrice.setBorder(null);

        txtSale.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        txtSale.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LayoutImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textAreaScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNameProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                    .addComponent(txtPrice))
                                .addGap(18, 115, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(JTypeProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSale))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNameProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JTypeProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtSale)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textAreaScroll1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LayoutImage, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dashBorder1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashBorder1MouseClicked
        HandleUploadImage();
    }//GEN-LAST:event_dashBorder1MouseClicked

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        if(ImportData())
        {
            if(this.type == TypeFunction.Create)
            {
                showMessage("Bạn đã tạo sản phẩm thành công", TypeNotification.Success);
                showForm(new ManagementProduct(body, this.user, TypeFunction.Create, null, null));
            }
            else
            {
                showMessage("Bạn đã tạo sản phẩm thành công", TypeNotification.Success);
                showForm(new ProductList(body, user));
            }
            
        }
        else
           showMessage("Đã có lỗi xảy ra", TypeNotification.Error); 

    }//GEN-LAST:event_btnSaveActionPerformed

    private void showForm(Component com) {
        body.removeAll();
        body.add(com);
        body.repaint();
        body.revalidate();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner ASizeL;
    private javax.swing.JSpinner ASizeM;
    private javax.swing.JSpinner ASizeS;
    private javax.swing.JSpinner ASizeXL;
    private swing.Field.JCheckBoxCustom CSizeL;
    private swing.Field.JCheckBoxCustom CSizeM;
    private swing.Field.JCheckBoxCustom CSizeS;
    private swing.Field.JCheckBoxCustom CSizeXL;
    private swing.Combobox.ComboBoxSuggestion JTypeProduct;
    private javax.swing.JPanel LayoutImage;
    private swing.Button.ButtonEdit btnSave;
    private swing.Panel.DashBorder dashBorder1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private swing.Field.TextAreaScroll textAreaScroll1;
    private swing.Field.TextArea txtDescription;
    private swing.Field.TextFieldRadius txtNameProduct;
    private javax.swing.JSpinner txtPrice;
    private javax.swing.JSpinner txtSale;
    // End of variables declaration//GEN-END:variables
}
