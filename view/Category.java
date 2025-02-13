package view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

//import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;
//
import bus.CategoryBUS;
import bus.ProductBUS;
import dao.CategoryDAO;
import dto.CategoryDTO;
import dto.ProductDTO;
//import dao.CategoryDAO;
//import dto.CategoryDTO;
//import dto.ProductDTO;
//import service.ExcelExporter;
//import service.Validation;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.LineBorder;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class Category extends JPanel implements MouseListener, KeyListener {

    private static final long serialVersionUID = 1L;
    private JTextField cateFindTxt;
    private JTable table;
    private JTextField cateNameTxt;
    private JTextField textField_3;
    private JTable table_1;
    private DefaultTableModel categoryModel, productModel;
    private JButton btnAddCate;
    private JButton btnEditCate;
    private JButton btnDeleteCate;
    private JButton btnKhoiphucCate;

    private JButton btnRefreshCate;
    private JButton btnExport, btnImport;
    CategoryBUS categoryBUS = new CategoryBUS();
    ArrayList<CategoryDTO> listCategory = categoryBUS.getALL();
    ProductBUS productBUS = new ProductBUS();
    ArrayList<ProductDTO> listProduct = productBUS.getALL();
    private JTextField cateIdTxt;
    CategoryBUS CategoryBUS = new CategoryBUS();

    /**
     * Create the panel.
     */
    public static void main(String[] args) {
        Category ct = new Category();
        JFrame fr = new JFrame();
        fr.add(ct);
        fr.setVisible(true);

    }

    public Category() {
        initComponent();
        loadDataTable(listCategory);
        loadDataProductTable(listProduct);
    }

    public void initComponent() {
        Color myColor = new Color(34, 33, 75);
        Color backGroundColor = Color.white;
        Color borderColor = myColor;
        Color textColor = myColor;
        Color buttonColor = myColor;
        setBackground(backGroundColor);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{600, 400};
        gridBagLayout.rowHeights = new int[]{400, 600};
        gridBagLayout.columnWeights = new double[]{1.0, 1.0};
        gridBagLayout.rowWeights = new double[]{1.0, 1.0};
        setLayout(gridBagLayout);

        JPanel panelCategoryTable = new JPanel();
        panelCategoryTable.setBackground(Color.WHITE);
        panelCategoryTable.setBorder(new MatteBorder(0, 0, 0, 2, borderColor));
        GridBagConstraints gbc_panelCategoryTable = new GridBagConstraints();
        gbc_panelCategoryTable.weightx = 0.7;
        gbc_panelCategoryTable.insets = new Insets(20, 20, 5, 10);
        gbc_panelCategoryTable.fill = GridBagConstraints.BOTH;
        gbc_panelCategoryTable.gridx = 0;
        gbc_panelCategoryTable.gridy = 0;
        add(panelCategoryTable, gbc_panelCategoryTable);
        GridBagLayout gbl_panelCategoryTable = new GridBagLayout();
        gbl_panelCategoryTable.columnWidths = new int[]{0, 0, 0, 0};
        gbl_panelCategoryTable.rowHeights = new int[]{0, 0, 0, 0};
        gbl_panelCategoryTable.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panelCategoryTable.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
        panelCategoryTable.setLayout(gbl_panelCategoryTable);

        JLabel lblNewLabel = new JLabel("Thông Tin Loại SP");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNewLabel.setForeground(textColor);
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.weighty = 0.1;
        gbc_lblNewLabel.weightx = 1.0;
        gbc_lblNewLabel.gridwidth = 3;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 0;
        panelCategoryTable.add(lblNewLabel, gbc_lblNewLabel);

        cateFindTxt = new JTextField();
        cateFindTxt.setBorder(new LineBorder(borderColor, 2, true));
        cateFindTxt.setColumns(10);
        cateFindTxt.addKeyListener(this);
        GridBagConstraints gbc_cateFindTxt = new GridBagConstraints();
        gbc_cateFindTxt.ipady = 5;
        gbc_cateFindTxt.weighty = 0.1;
        gbc_cateFindTxt.weightx = 0.8;
        gbc_cateFindTxt.insets = new Insets(0, 5, 5, 5);
        gbc_cateFindTxt.fill = GridBagConstraints.HORIZONTAL;
        gbc_cateFindTxt.gridx = 0;
        gbc_cateFindTxt.gridy = 1;
        panelCategoryTable.add(cateFindTxt, gbc_cateFindTxt);

        btnExport = new JButton("Xuất Excel");
        btnExport.setBorder(new LineBorder(borderColor, 2, true));
        btnExport.setForeground(Color.white);
        btnExport.setBackground(buttonColor);
        btnExport.addMouseListener(this);

        GridBagConstraints gbc_btnExport = new GridBagConstraints();
        gbc_btnExport.weighty = 0.1;
        gbc_btnExport.weightx = 0.1;
        gbc_btnExport.insets = new Insets(0, 0, 5, 5);
        gbc_btnExport.gridx = 1;
        gbc_btnExport.gridy = 1;
        panelCategoryTable.add(btnExport, gbc_btnExport);

        btnImport = new JButton("Nhập Excel");
        btnImport.setBorder(new LineBorder(borderColor, 2, true));
        btnImport.setForeground(Color.white);
        btnImport.setBackground(buttonColor);
        btnImport.addMouseListener(this);

        GridBagConstraints gbc_btnImport = new GridBagConstraints();
        gbc_btnImport.weighty = 0.1;
        gbc_btnImport.weightx = 0.1;
        gbc_btnImport.insets = new Insets(0, 0, 5, 0);
        gbc_btnImport.gridx = 2;
        gbc_btnImport.gridy = 1;
        panelCategoryTable.add(btnImport, gbc_btnImport);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new LineBorder(borderColor, 2));
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.insets = new Insets(0, 5, 0, 5);
        gbc_scrollPane.weighty = 0.8;
        gbc_scrollPane.weightx = 1.0;
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridwidth = 3;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 2;
        panelCategoryTable.add(scrollPane, gbc_scrollPane);

        String[] columnNames = {"Mã Loại SP", "Loại SP", "Trạng thái"};
        Object[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        categoryModel = new DefaultTableModel();
        categoryModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        categoryModel.setColumnIdentifiers(columnNames);

        table = new JTable(categoryModel);
        table.addMouseListener(this);
        table.setRowHeight(20);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);
        columnModel.getColumn(2).setCellRenderer(centerRenderer);

        table.setBorder(new LineBorder(borderColor, 2, false));
        table.getTableHeader().setBorder(new LineBorder(borderColor, 2, false));
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setBackground(borderColor);
        table.getTableHeader().setForeground(Color.white);

//       scrollPane.setViewportBorder(BorderFactory.createLineBorder(borderColor, 5));
        scrollPane.setViewportView(table);

        JPanel panelCategoryInfo = new JPanel();
        panelCategoryInfo.setBackground(Color.WHITE);
        GridBagConstraints gbc_panelCategoryInfo = new GridBagConstraints();
        gbc_panelCategoryInfo.weightx = 0.3;
        gbc_panelCategoryInfo.insets = new Insets(20, 5, 5, 10);
        gbc_panelCategoryInfo.fill = GridBagConstraints.BOTH;
        gbc_panelCategoryInfo.gridx = 1;
        gbc_panelCategoryInfo.gridy = 0;
        add(panelCategoryInfo, gbc_panelCategoryInfo);
        GridBagLayout gbl_panelCategoryInfo = new GridBagLayout();
        gbl_panelCategoryInfo.columnWidths = new int[]{50, 200};
        gbl_panelCategoryInfo.rowHeights = new int[]{0, 0, 0, 0, 0};
        gbl_panelCategoryInfo.columnWeights = new double[]{1.0, 1.0};
        gbl_panelCategoryInfo.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        panelCategoryInfo.setLayout(gbl_panelCategoryInfo);

        JLabel lblNewLabel_1 = new JLabel("Thông Tin Loại SP");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNewLabel_1.setForeground(textColor);
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.weighty = 0.1;
        gbc_lblNewLabel_1.weightx = 1.0;
        gbc_lblNewLabel_1.gridwidth = 2;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel_1.gridx = 0;
        gbc_lblNewLabel_1.gridy = 0;
        panelCategoryInfo.add(lblNewLabel_1, gbc_lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Mã Loại SP:");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.weighty = 0.3;
        gbc_lblNewLabel_2.weightx = 0.1;
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 0;
        gbc_lblNewLabel_2.gridy = 1;
        panelCategoryInfo.add(lblNewLabel_2, gbc_lblNewLabel_2);

        cateIdTxt = new JTextField();
        cateIdTxt.setColumns(10);
        cateIdTxt.setFocusable(true);
        cateIdTxt.setEditable(false);
        GridBagConstraints gbc_cateIdTxt = new GridBagConstraints();
        gbc_cateIdTxt.insets = new Insets(0, 0, 5, 0);
        gbc_cateIdTxt.fill = GridBagConstraints.HORIZONTAL;
        gbc_cateIdTxt.gridx = 1;
        gbc_cateIdTxt.gridy = 1;
        panelCategoryInfo.add(cateIdTxt, gbc_cateIdTxt);

        JLabel lblNewLabel_3 = new JLabel("Tên SP:");
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 12));
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.weighty = 0.3;
        gbc_lblNewLabel_3.weightx = 0.1;
        gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_3.gridx = 0;
        gbc_lblNewLabel_3.gridy = 2;
        panelCategoryInfo.add(lblNewLabel_3, gbc_lblNewLabel_3);

        cateNameTxt = new JTextField();
        cateNameTxt.setBorder(new LineBorder(borderColor, 2, true));
        GridBagConstraints gbc_cateNameTxt = new GridBagConstraints();
        gbc_cateNameTxt.ipady = 5;
        gbc_cateNameTxt.weightx = 0.9;
        gbc_cateNameTxt.insets = new Insets(0, 0, 5, 0);
        gbc_cateNameTxt.fill = GridBagConstraints.HORIZONTAL;
        gbc_cateNameTxt.gridx = 1;
        gbc_cateNameTxt.gridy = 2;
        panelCategoryInfo.add(cateNameTxt, gbc_cateNameTxt);
        cateNameTxt.setColumns(10);

        JPanel panel_3 = new JPanel();
        panel_3.setBackground(Color.WHITE);
        GridBagConstraints gbc_panel_3 = new GridBagConstraints();
        gbc_panel_3.fill = GridBagConstraints.BOTH;
        gbc_panel_3.weightx = 1.0;
        gbc_panel_3.weighty = 0.3;
        gbc_panel_3.gridwidth = 2;
        gbc_panel_3.insets = new Insets(10, 5, 10, 0);
        gbc_panel_3.gridx = 0;
        gbc_panel_3.gridy = 3;
        panelCategoryInfo.add(panel_3, gbc_panel_3);
        panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        btnAddCate = new JButton("Thêm");
        btnAddCate.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnAddCate.setBorder(new LineBorder(borderColor, 2, true));
        btnAddCate.setPreferredSize(new Dimension(100, 40));
        btnAddCate.setBackground(buttonColor);
        btnAddCate.setForeground(Color.white);
        btnAddCate.setFocusPainted(false);
        btnAddCate.addMouseListener(this);
        panel_3.add(btnAddCate);
        btnEditCate = new JButton("Sửa");
        btnEditCate.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnEditCate.setBorder(new LineBorder(borderColor, 2, true));
        Image iconEdit = new ImageIcon("img/edit.png").getImage();
        iconEdit = iconEdit.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        btnEditCate.setPreferredSize(new Dimension(100, 40));
        btnEditCate.setBackground(buttonColor);
        btnEditCate.setForeground(Color.white);
        btnEditCate.setFocusPainted(false);
        btnEditCate.setIcon(new ImageIcon(iconEdit));
        btnEditCate.addMouseListener(this);
        panel_3.add(btnEditCate);

        btnDeleteCate = new JButton("Xóa");
        btnDeleteCate.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnDeleteCate.setBorder(new LineBorder(borderColor, 2, true));
        btnDeleteCate.setPreferredSize(new Dimension(100, 40));
        btnDeleteCate.setBackground(buttonColor);
        btnDeleteCate.setForeground(Color.white);
        btnDeleteCate.setFocusPainted(false);
        btnDeleteCate.addMouseListener(this);
        panel_3.add(btnDeleteCate);

        btnKhoiphucCate = new JButton("Khôi phục");
        btnKhoiphucCate.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnKhoiphucCate.setBorder(new LineBorder(borderColor, 2, true));
        btnKhoiphucCate.setPreferredSize(new Dimension(100, 40));
        btnKhoiphucCate.setBackground(buttonColor);
        btnKhoiphucCate.setForeground(Color.white);
        btnKhoiphucCate.setFocusPainted(false);
        btnKhoiphucCate.addMouseListener(this);
        panel_3.add(btnKhoiphucCate);

        btnRefreshCate = new JButton("Làm mới");
        btnRefreshCate.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnRefreshCate.setBorder(new LineBorder(borderColor, 2, true));
        btnRefreshCate.setPreferredSize(new Dimension(100, 40));
        btnRefreshCate.setBackground(buttonColor);
        btnRefreshCate.setForeground(Color.white);
        btnRefreshCate.setFocusPainted(false);
        btnRefreshCate.addMouseListener(this);
        panel_3.add(btnRefreshCate);

        JPanel panelProductTable = new JPanel();
        panelProductTable.setBackground(Color.WHITE);
        panelProductTable.setBorder(new MatteBorder(2, 0, 0, 0, borderColor));
        GridBagConstraints gbc_panelProductTable = new GridBagConstraints();
        gbc_panelProductTable.weightx = 1.0;
        gbc_panelProductTable.gridwidth = 2;
        gbc_panelProductTable.insets = new Insets(5, 20, 5, 10);
        gbc_panelProductTable.fill = GridBagConstraints.BOTH;
        gbc_panelProductTable.gridx = 0;
        gbc_panelProductTable.gridy = 1;
        add(panelProductTable, gbc_panelProductTable);
        GridBagLayout gbl_panelProductTable = new GridBagLayout();
        gbl_panelProductTable.columnWidths = new int[]{0, 0};
        gbl_panelProductTable.rowHeights = new int[]{0, 0, 0, 0};
        gbl_panelProductTable.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_panelProductTable.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
        panelProductTable.setLayout(gbl_panelProductTable);

        JLabel lblNewLabel_4 = new JLabel("Thông Tin Sản Phẩm");
        lblNewLabel_4.setForeground(textColor);
        lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 15));
        GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
        gbc_lblNewLabel_4.weighty = 0.1;
        gbc_lblNewLabel_4.weightx = 1.0;
        gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel_4.gridx = 0;
        gbc_lblNewLabel_4.gridy = 0;
        panelProductTable.add(lblNewLabel_4, gbc_lblNewLabel_4);

        textField_3 = new JTextField();
        textField_3.addKeyListener(this);
        textField_3.setBorder(new LineBorder(borderColor, 2, true));
        GridBagConstraints gbc_textField_3 = new GridBagConstraints();
        gbc_textField_3.ipady = 5;
        gbc_textField_3.weighty = 0.1;
        gbc_textField_3.weightx = 1.0;
        gbc_textField_3.insets = new Insets(0, 0, 5, 0);
        gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_3.gridx = 0;
        gbc_textField_3.gridy = 1;
        panelProductTable.add(textField_3, gbc_textField_3);
        textField_3.setColumns(10);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBorder(new LineBorder(borderColor, 2, true));
        GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
        gbc_scrollPane_1.weighty = 0.8;
        gbc_scrollPane_1.weightx = 1.0;
        gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
        gbc_scrollPane_1.gridx = 0;
        gbc_scrollPane_1.gridy = 2;
        panelProductTable.add(scrollPane_1, gbc_scrollPane_1);

        String[] columnNames1 = {"Mã SP", "Tên SP", "Mã loại SP"};
        Object[][] data1 = {};

        productModel = new DefaultTableModel(data1, columnNames1) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table_1 = new JTable(productModel);
        table_1.setRowHeight(20);
        table_1.getColumnModel().getColumn(0).setPreferredWidth(50);
        table_1.getColumnModel().getColumn(1).setPreferredWidth(150);
        table_1.getColumnModel().getColumn(2).setPreferredWidth(100);

        TableColumnModel columnProductModel = table_1.getColumnModel();
        columnProductModel.getColumn(0).setCellRenderer(centerRenderer);
        columnProductModel.getColumn(1).setCellRenderer(centerRenderer);
        columnProductModel.getColumn(2).setCellRenderer(centerRenderer);
        table_1.setBorder(new LineBorder(borderColor, 2, false));
        table_1.getTableHeader().setBorder(new LineBorder(borderColor, 2, false));
        table_1.getTableHeader().setReorderingAllowed(false);
        table_1.getTableHeader().setBackground(borderColor);
        table_1.getTableHeader().setForeground(Color.white);
        scrollPane_1.setViewportView(table_1);

    }

    public void loadDataTable(ArrayList<CategoryDTO> result) {
        categoryModel.setRowCount(0);
        for (CategoryDTO c : result) {
            String status = c.getStatus() == 1 ? "Kích hoạt" : "Vô hiệu hóa";

            categoryModel.addRow(new Object[]{
                c.getIDLOAISP(), c.getTENLOAISANPHAM(), status
            });
        }
    }

    public void loadDataProductTable(ArrayList<ProductDTO> result) {
        productModel.setRowCount(0);
        for (ProductDTO p : result) {

            productModel.addRow(new Object[]{
                p.getIDSP(), p.getTEN(), categoryBUS.getCategoryName(p.getIDLOAISP())
            });
        }
    }

    public int getRowSelected() {
        int index = table.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại sản phẩm!");
        }
        return index;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == btnAddCate) {
            CategoryDTO category = getcategoryinfo();
            if (categoryBUS.checkDup(category.getTENLOAISANPHAM(), category.getIDLOAISP())) {
                Boolean a = categoryBUS.add(category);
                if (a == false) {
                    JOptionPane.showMessageDialog(this, "Thêm Loại Sản phẩm thất bại !");
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm loại Sản phẩm thành công !");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Loại Sản phẩm đã tồn tại !");
            }
            resetForm();

        } else if (e.getSource() == btnDeleteCate) {
            int index = getRowSelected();
            if (index != -1) {
                Boolean a = categoryBUS.delete(listCategory.get(index));
                if (a == true) {
                    JOptionPane.showMessageDialog(this, "Xóa Sản phẩm thành công !");
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa Sản phẩm thất bại !");
                }
            }
            resetForm();

        } else if (e.getSource() == btnEditCate) {
            int index = getRowSelected();
            boolean a = false;
            if (index != -1) {
                if (validateCate()) {
                    CategoryDTO cate = getcategoryinfo();
                    if (listCategory.get(index).getIDLOAISP() == Integer.parseInt(cateIdTxt.getText()) || listCategory.get(index).getTENLOAISANPHAM().contains(cateNameTxt.getText())) {
                        a = categoryBUS.update(cate);
                        if (a == false) {
                            JOptionPane.showMessageDialog(this, "Sửa loại Sản phẩm thất bại  !");
                        } else {
                            JOptionPane.showMessageDialog(this, "Sửa loại Sản phẩm thành công !");
                        }
                    } else if (categoryBUS.checkDup(cate.getTENLOAISANPHAM(), cate.getIDLOAISP())) {
                        a = categoryBUS.update(cate);
                        if (a == false) {
                            JOptionPane.showMessageDialog(this, "Sửa loại Sản phẩm thất bại  !");
                        } else {
                            JOptionPane.showMessageDialog(this, "Sửa loại Sản phẩm thành công !");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "loại sản phẩm đã tồn tại !");
                    }

                }
            }
            resetForm();
        } else if (e.getSource() == table) {
            int index = table.getSelectedRow();
            cateIdTxt.setText(String.valueOf(listCategory.get(index).getIDLOAISP()));
            cateNameTxt.setText(listCategory.get(index).getTENLOAISANPHAM());

            productModel.setRowCount(0);
            for (ProductDTO p : listProduct) {
                if (p.getIDLOAISP() == (listCategory.get(index).getIDLOAISP())) {
                    productModel.addRow(new Object[]{
                        p.getIDSP(), p.getTEN(), CategoryBUS.getCategoryName(p.getIDLOAISP())
                    });
                }
            }

        } else if (e.getSource() == btnRefreshCate) {
            CategoryBUS categoryBUS = new CategoryBUS();
            ArrayList<CategoryDTO> listCategory = categoryBUS.getALL();
            ProductBUS productBUS = new ProductBUS();
            ArrayList<ProductDTO> listProduct = productBUS.getALL();
            loadDataProductTable(listProduct);
            resetForm();
//        } else if (e.getSource() == btnExport) {
//        	exportExcel();
//        } else if (e.getSource() == btnImport) {
//        	importExcel();
        } else if (e.getSource() == btnKhoiphucCate) {
            int index = getRowSelected();
            if (index != -1) {
                Boolean a = categoryBUS.nondelete(listCategory.get(index));
                if (a == true) {
                    JOptionPane.showMessageDialog(this, "Khôi phục Sản phẩm thành công !");
                } else {
                    JOptionPane.showMessageDialog(this, "Khôi phục Sản phẩm thất bại !");
                }
            }
            resetForm();
        }

    }

    public CategoryDTO getcategoryinfo() {
        CategoryDTO cate = new CategoryDTO();

        if (validateCate()) {
            String catename = cateNameTxt.getText();
            int cateid ;
            if(!cateIdTxt.getText().isEmpty())
                cateid = Integer.parseInt(cateIdTxt.getText());
            else
                cateid = CategoryDAO.getInstance().getAutoIncrement();         
            int status = 1;
            cate = new CategoryDTO(cateid, catename, status);
        }
        return cate;
    }

    public boolean validateCate() {
        // Kiểm tra trường IDPR
        if (cateNameTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống dữ liêu");
            return false;
        }
        // Kiểm tra các trường số liệu khác
        // Tương tự như trường giá, kiểm tra và xử lý lỗi cho các trường còn lại
        // Nếu dữ liệu hợp lệ, trả về true
        return true;
    }

    public void resetForm() {
        listProduct.clear();
        listProduct = productBUS.getALL();
        loadDataProductTable(listProduct);
        listCategory.clear();
        listCategory = categoryBUS.getALL();
        loadDataTable(listCategory);
        cateIdTxt.setText("");
        cateNameTxt.setText("");
    }

    public void importExcel() {
//        try {
//            categoryBUS.importExcel();
//            CategoryBUS categoryBUS = new CategoryBUS();
//            ArrayList<CategoryDTO> listCategory = categoryBUS.getALL();
//            JOptionPane.showMessageDialog(this, "Nhập thành công !");
//            loadDataTable(listCategory);
//        } catch (Exception e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Nhập thất bại !");
//        }
    }

    private void exportExcel() {
//    	try {
//    		ExcelExporter excel = new ExcelExporter();
//        	excel.exportJTableToExcel(table);
//		} catch (Exception e2) {
//            JOptionPane.showMessageDialog(this, "Lỗi Xuất Excel !");
//		}
//	}
//	
//	public void filterTable(String searchText) {
//	    searchText = searchText.toLowerCase();
//	    ArrayList<CategoryDTO> filteredList = new ArrayList<>();
//
//	    for (CategoryDTO c : listCategory) {
//	        if (c.getCategoryName().toLowerCase().contains(searchText) || String.valueOf(c.getCategoryId()).toLowerCase().contains(searchText)) {
//	            filteredList.add(c);
//	        }
//	    }
//
//	    loadDataTable(filteredList);
    }

    public void filterProductTable(String searchText) {
        ArrayList<ProductDTO> filteredList = new ArrayList<>();

        for (ProductDTO p : listProduct) {
            if (p.getTEN().toLowerCase().contains(searchText.toLowerCase()) || p.getIDSP() == Integer.parseInt(searchText)
                    && p.getIDLOAISP() == Integer.parseInt(searchText)) {
                filteredList.add(p);
            }
        }

        loadDataProductTable(filteredList);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
//		// TODO Auto-generated method stub
//        try {
//            filterTable(cateFindTxt.getText());
//            filterProductTable(textField_3.getText());
//        } catch (Exception  ex) {
//            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }
}
