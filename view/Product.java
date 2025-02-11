/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import JDBCUtil.JDBCConnect;
import bus.CategoryBUS;
//import bus.CategoryBUS;
import bus.ProductBUS;
import bus.VoucherBUS;
import dao.CategoryDAO;
import dao.ProductDAO;
import dto.CategoryDTO;
import dto.ProductDTO;
import dto.VoucherDTO;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lap4all
 */
public final class Product extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    ProductBUS productBUS = new ProductBUS();
    ArrayList<ProductDTO> listProduct = productBUS.getALL();
    CategoryBUS categoryBUS = new CategoryBUS();
    ArrayList<CategoryDTO> listCategory = categoryBUS.getALL();
    VoucherBUS voucherBUS = new VoucherBUS();
    ArrayList<VoucherDTO> listvoucher = voucherBUS.getALL();

    public Product() {
        initComponents();
        loadDataTable(listProduct);
        loadform(listCategory, listvoucher);
    }

    private void displaySelectedProduct() {
        int index = jTable1.getSelectedRow();
        jTextField11.setText(String.valueOf(listProduct.get(index).getIDSP()));
        jTextField10.setText(String.valueOf(listProduct.get(index).getTEN()));
        jTextField13.setText(String.valueOf(listProduct.get(index).getGIA()));
        jTextField12.setText(listProduct.get(index).getXUATXU());
        jTextField16.setText(listProduct.get(index).getTHUONGHIEU());
        jTextField14.setText(listProduct.get(index).getMOTA());
        jTextField17.setText(String.valueOf(listProduct.get(index).getDUNGTICH()));
        jTextField18.setText(String.valueOf(listProduct.get(index).getNONGDOCON()));
        jTextField3.setText(String.valueOf(listProduct.get(index).getSOLUONG()));
        jTextField19.setText(listProduct.get(index).getANH());
        int cateId = listProduct.get(index).getIDLOAISP();
        int voucherid = listProduct.get(index).getIDKHUYENMAI();
        int trangthai = listProduct.get(index).getTRANGTHAI();
        jComboBox1.setSelectedItem(listCategory.get(categoryBUS.getIndexById(cateId)).getTENLOAISANPHAM());
        jComboBox3.setSelectedItem(listvoucher.get(voucherBUS.getIndexById(voucherid)).getTEN());
        if (trangthai == 1) {
            jComboBox2.setSelectedItem("Kích hoạt");
        } else {
            jComboBox2.setSelectedItem("Khóa");
        }

    }

    public void loadform(ArrayList<CategoryDTO> listCategory, ArrayList<VoucherDTO> listvoucher) {
        jComboBox2.addItem("Kích hoạt");
        jComboBox2.addItem("Khóa");
        for (CategoryDTO category : listCategory) {
            jComboBox1.addItem(category.getTENLOAISANPHAM());
        }
        for (VoucherDTO voucher : listvoucher) {
            jComboBox3.addItem(voucher.getTEN());
        }
    }

    public void loadDataTable(ArrayList<ProductDTO> result) {
        DefaultTableModel productModel = (DefaultTableModel) jTable1.getModel();

        productModel.setRowCount(0);
        for (ProductDTO p : result) {
            String status;
            if (p.getTRANGTHAI() == 0) {
                status = "Khóa ";
            } else {
                status = "Kích hoạt";
            }
            productModel.addRow(new Object[]{
                p.getIDSP(), p.getTEN(), p.getGIA(), categoryBUS.getCategoryName(p.getIDLOAISP()), p.getSOLUONG(), status
            });
        }
    }

    public int getRowSelected() {
        int index = jTable1.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm!");
        }
        return index;
    }

    public boolean validateProductInfo() {
//        // Kiểm tra trường IDPR
        if (jTextField3.getText().isEmpty() || jTextField10.getText().isEmpty() || jTextField13.getText().isEmpty() || jTextField12.getText().isEmpty() || jTextField13.getText().isEmpty() || jTextField12.getText().isEmpty() || jTextField16.getText().isEmpty() || jTextField17.getText().isEmpty() || jTextField18.getText().isEmpty() || jTextField14.getText().isEmpty() || jTextField19.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống dữ liêu");
            return false;
        }
        double cost;
        try {
            cost = Double.parseDouble(jTextField13.getText());
            String ten = jTextField10.getText();
            int soluong = Integer.parseInt(jTextField3.getText());
            double dungtich = Double.parseDouble(jTextField17.getText());
            double nongdo = Double.parseDouble(jTextField18.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá trị không hợp lệ");
            return false;
        }

        // Kiểm tra các trường số liệu khác
        // Tương tự như trường giá, kiểm tra và xử lý lỗi cho các trường còn lại
        // Nếu dữ liệu hợp lệ, trả về true
        return true;
    }

    public ProductDTO getProductInfo() {
        ProductDTO product = new ProductDTO();

        if (validateProductInfo()) {
            int IDSP;
            if (!jTextField11.getText().isEmpty()) {
                IDSP = Integer.parseInt(jTextField11.getText());
            } else {
                IDSP = ProductDAO.getInstance().getAutoIncrement();
            }
            String TENSP = jTextField10.getText();
            double cost = Double.parseDouble(jTextField13.getText());
            String XUATXU = jTextField12.getText();
            String THUONGHIEU = jTextField16.getText();
            int KHUYENMAI = listvoucher.get(jComboBox3.getSelectedIndex()).getIDKHUYENMAI();
            String tr = (String) jComboBox2.getSelectedItem();
            int TRANGTHAI;
            if (tr.contains("Kích hoạt")) {
                TRANGTHAI = 1;
            } else {
                TRANGTHAI = 0;
            }
            String MOTA = jTextField14.getText();
            double dungtich = Double.parseDouble(jTextField17.getText());
            double nongdocon = Double.parseDouble(jTextField18.getText());
            //quanity default equal zero
            String Img = jTextField19.getText();
            int soluong = Integer.parseInt(jTextField3.getText());
            int IDLOAISP = listCategory.get(jComboBox1.getSelectedIndex()).getIDLOAISP();
            product = new ProductDTO(IDSP, IDLOAISP, KHUYENMAI, TENSP, soluong, cost, dungtich, nongdocon, TRANGTHAI, XUATXU, THUONGHIEU, MOTA, Img);
        }
        return product;
    }

    private void addProduct() {
        ProductDTO product = getProductInfo();
        if (productBUS.checkDup(product.getTEN(), product.getIDSP())) {
            Boolean a = productBUS.add(product);
            if (a == false) {
                JOptionPane.showMessageDialog(this, "Thêm Sản phẩm thất bại !");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm Sản phẩm thành công !");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Sản phẩm đã tồn tại !");
        }
        resetForm();
    }

    private void deleteProduct() {
        int index = getRowSelected();
        if (index != -1) {
            Boolean a = productBUS.delete(listProduct.get(getRowSelected()));
            resetForm();
            if (a == true) {
                JOptionPane.showMessageDialog(this, "Xóa Sản phẩm thành công !");
            } else {
                JOptionPane.showMessageDialog(this, "Xóa Sản phẩm thất bại !");
            }

        }
    }

    private void khoiphuc() {
        int index = getRowSelected();
        if (index != -1) {
            Boolean a = productBUS.restore(listProduct.get(getRowSelected()));
            resetForm();
            if (a == true) {
                JOptionPane.showMessageDialog(this, "Khôi phục Sản phẩm thành công !");
            } else {
                JOptionPane.showMessageDialog(this, "Khôi phục Sản phẩm thất bại !");
            }

        }
    }

    public void resetForm() {
        jTextField11.setText("");
        jTextField3.setText("");
        jTextField10.setText("");
        jTextField11.setText("");
        jTextField12.setText("");
        jTextField13.setText("");
        jTextField14.setText("");
        jTextField16.setText("");
        jTextField17.setText("");
        jTextField18.setText("");
        jTextField19.setText("");
        listProduct.clear();
        listProduct = productBUS.getALL();
        loadDataTable(listProduct);
        CategoryBUS categoryBUS = new CategoryBUS();
        listCategory = categoryBUS.getALL();
        jComboBox1.removeAllItems();
        for (CategoryDTO category : listCategory) {
            jComboBox1.addItem(category.getTENLOAISANPHAM());
        }
        jLabel21.setIcon(null);
    }

    private void editProduct() {
        int index = getRowSelected();
        boolean a;
        if (index != -1) {
            if (validateProductInfo()) {
                ProductDTO product = getProductInfo();
                if (listProduct.get(index).getIDSP() == Integer.parseInt(jTextField11.getText()) || listProduct.get(index).getTEN().contains(jTextField10.getText())) {
                    a = productBUS.update(product);
                    if (a == false) {
                        JOptionPane.showMessageDialog(this, "Sửa Sản phẩm thất bại !");
                    } else {
                        JOptionPane.showMessageDialog(this, "Sửa Sản phẩm thành công !");
                    }
                } else if (productBUS.checkDup(product.getTEN(), product.getIDSP())) {

                    a = productBUS.update(product);
                    if (a == false) {
                        JOptionPane.showMessageDialog(this, "Sửa Sản phẩm thất bại !");
                    } else {
                        JOptionPane.showMessageDialog(this, "Sửa Sản phẩm thành công !");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa sản phẩm đã tồn tại !");
                }
                resetForm();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
private void initComponents() {

    jPanel1 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jTextField1 = new javax.swing.JTextField();
    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    jTable1 = new javax.swing.JTable();
    jPanel3 = new javax.swing.JPanel();
    jButton3 = new javax.swing.JButton();
    jButton4 = new javax.swing.JButton();
    jButton5 = new javax.swing.JButton();
    jButton6 = new javax.swing.JButton();
    jButton7 = new javax.swing.JButton();
    jPanel4 = new javax.swing.JPanel();
    jLabel3 = new javax.swing.JLabel();
    jLabel6 = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    jLabel8 = new javax.swing.JLabel();
    jLabel9 = new javax.swing.JLabel();
    jLabel10 = new javax.swing.JLabel();
    jLabel11 = new javax.swing.JLabel();
    jLabel12 = new javax.swing.JLabel();
    jLabel13 = new javax.swing.JLabel();
    jLabel14 = new javax.swing.JLabel();
    jLabel15 = new javax.swing.JLabel();
    jLabel16 = new javax.swing.JLabel();
    jLabel17 = new javax.swing.JLabel();
    jLabel18 = new javax.swing.JLabel();
    jLabel19 = new javax.swing.JLabel();
    jTextField3 = new javax.swing.JTextField();
    jTextField10 = new javax.swing.JTextField();
    jTextField11 = new javax.swing.JTextField();
    jTextField12 = new javax.swing.JTextField();
    jTextField13 = new javax.swing.JTextField();
    jTextField14 = new javax.swing.JTextField();
    jTextField16 = new javax.swing.JTextField();
    jTextField17 = new javax.swing.JTextField();
    jTextField18 = new javax.swing.JTextField();
    jTextField19 = new javax.swing.JTextField();
    jComboBox1 = new javax.swing.JComboBox<>();
    jComboBox2 = new javax.swing.JComboBox<>();
    jComboBox3 = new javax.swing.JComboBox<>();
    jLabel21 = new javax.swing.JLabel();

    setBackground(new java.awt.Color(204, 204, 204));

    jPanel1.setBackground(new java.awt.Color(255, 255, 255));

    jLabel1.setText("Thông tin chung");

    jButton1.setText("Nhập Excel");
    jButton1.setToolTipText("");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    jButton2.setText("Xuất Excel");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
        }
    });

    jTable1.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null}
        },
        new String [] {
            "Mã ", "Tên", "Giá", "Loại ", "Số lượng", "Trạng thái"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, false, false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    jTable1.setMinimumSize(new java.awt.Dimension(1024, 720));
    jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTable1MouseClicked(evt);
        }
    });
    jScrollPane1.setViewportView(jTable1);

    jButton3.setText("Thêm");
    jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton3MouseClicked(evt);
        }
    });
    jButton3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton3ActionPerformed(evt);
        }
    });

    jButton4.setText("Xóa");
    jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton4MouseClicked(evt);
        }
    });
    jButton4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton4ActionPerformed(evt);
        }
    });

    jButton5.setText("Sửa");
    jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton5MouseClicked(evt);
        }
    });

    jButton6.setText("Làm mới");
    jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton6MouseClicked(evt);
        }
    });
    jButton6.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton6ActionPerformed(evt);
        }
    });

    jButton7.setText("Khôi phục");
    jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jButton7MouseClicked(evt);
        }
    });
    jButton7.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton7ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addGap(9, 9, 9)
            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(84, 84, 84)
            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(50, 50, 50)
            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(30, 30, 30)
            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(15, 15, 15))
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
    );

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(310, 310, 310)
            .addComponent(jLabel1)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                    .addComponent(jButton1)
                    .addGap(74, 74, 74)
                    .addComponent(jButton2)
                    .addGap(16, 16, 16))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
            .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton1)
                .addComponent(jButton2))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane1))
    );

    jLabel3.setText("Thông tin dòng sản phẩm");

    jLabel6.setText("Mã sản phẩm");

    jLabel7.setText("Tên sản phẩm");

    jLabel8.setText("Giá");

    jLabel9.setText("Xuất xứ");

    jLabel10.setText("Thương hiệu");

    jLabel11.setText("Khuyến mãi");

    jLabel12.setText("Trạng thái");

    jLabel14.setText("Mô tả");

    jLabel15.setText("Dung tích");

    jLabel16.setText("Nồng độ cồn");

    jLabel17.setText("Số lượng");

    jLabel18.setText("Ảnh ");

    jLabel19.setText("Loại");

    jTextField11.setEditable(false);

    jTextField14.setToolTipText("");

    jTextField16.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jTextField16ActionPerformed(evt);
        }
    });

    jComboBox3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox3ActionPerformed(evt);
        }
    });

    jLabel21.setText("Ảnh");

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(87, 87, 87))
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel21)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jTextField18)
                        .addComponent(jTextField17)
                        .addComponent(jTextField14)
                        .addComponent(jTextField16, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jTextField12)
                        .addComponent(jTextField13, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jTextField10)
                        .addComponent(jTextField11)
                        .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField19))))
            .addContainerGap())
    );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel3)
            .addGap(34, 34, 34)
            .addComponent(jLabel13)
            .addGap(40, 40, 40)
            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jTextField11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jTextField10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel8)
                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jTextField12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel10)
                .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel11)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel19)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(9, 9, 9)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel12)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel14)
                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jTextField17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jTextField18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel17)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jTextField19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(191, Short.MAX_VALUE))
    );
}

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        displaySelectedProduct();

        // TODO add your handling code here:
    }

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        addProduct();

    }

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        resetForm();
    }

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        deleteProduct();
    }

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        khoiphuc();
    }

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        editProduct();
    }

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField3;

    // End of variables declaration                   
    public static void main(String[] args) {
        JFrame fr = new JFrame();
        Product pr = new Product();
        fr.add(pr);
        fr.setSize(1000, 800);
        fr.setVisible(true);
    }
}
