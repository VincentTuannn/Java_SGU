/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import bus.ProductBUS;
import bus.lohangBUS;
import dto.ProductDTO;
import dto.lohangDTO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class lohang extends JPanel {

    private JTextField timeTextField;
    private JComboBox<String> productNameComboBox;
    private JButton searchButton;
    private JButton refreshButton;
    private JTable table;
    lohangBUS lohangBUS = new lohangBUS();
    ArrayList<lohangDTO> listlohang = lohangBUS.getALL();
    ProductBUS productBUS = new ProductBUS();
    ArrayList<ProductDTO> listproduct = productBUS.getALL();

public final void loadSupplierTable(ArrayList<lohangDTO> result) {
    DefaultTableModel lohangmodel = (DefaultTableModel) table.getModel();
    lohangmodel.setRowCount(0);

    // Sắp xếp danh sách kết quả theo thứ tự hạn sử dụng sắp hết
    Collections.sort(result, new Comparator<lohangDTO>() {
        @Override
        public int compare(lohangDTO lohang1, lohangDTO lohang2) {
            return Integer.compare(lohang1.getHANSUDUNG(), lohang2.getHANSUDUNG());
        }
    });

    for (lohangDTO s : result) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(s.getNGAYSANXUAT());

        calendar.add(Calendar.DAY_OF_MONTH, s.getHANSUDUNG());

        Date newDate = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String newDateString = sdf.format(newDate);

        // Kiểm tra ngày hết hạn so với ngày hiện tại và thiết lập tình trạng
        String status;
        if (newDate.compareTo(new Date()) <= 0) {
            status = "Hết hạn";
        } else {
            status = "Còn hạn";
        }

        lohangmodel.addRow(new Object[]{
            s.getIDLOHANG(), productBUS.getProductName(s.getIDSANPHAM()), s.getSOLUONG(), s.getNGAYSANXUAT(), newDateString, status
        });
    }
}

    public final void loaddatacbbox(ArrayList<ProductDTO> result) {
        productNameComboBox.removeAllItems(); // Xóa tất cả các item trong combobox

        for (ProductDTO product : result) {
            productNameComboBox.addItem(product.getTEN()); // Thêm tên sản phẩm vào combobox
        }
    }

    public lohang() {
        // Set layout for the panel
        setLayout(new BorderLayout());

        // Create and add title label
        JLabel titleLabel = new JLabel("Lô hàng", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Create panel for input components
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Set insets for spacing

        // Add components to the input panel using GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel timeLabel = new JLabel("Thời gian:");
        inputPanel.add(timeLabel, gbc);

        gbc.gridx = 1;
        timeTextField = new JTextField(15);
        inputPanel.add(timeTextField, gbc);

        gbc.gridx = 2;
        JLabel productLabel = new JLabel("Tên sản phẩm:");
        inputPanel.add(productLabel, gbc);

        gbc.gridx = 3;
        productNameComboBox = new JComboBox<>();
        inputPanel.add(productNameComboBox, gbc);

        gbc.gridx = 4;
        searchButton = new JButton("Tìm kiếm");
        inputPanel.add(searchButton, gbc);

        // Add "Refresh" button to the input panel
        gbc.gridx = 5;
        refreshButton = new JButton("Làm mới");
        inputPanel.add(refreshButton, gbc);

        // Add input panel to the main panel
        add(inputPanel, BorderLayout.CENTER);

        // Create and add table to display data
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.SOUTH);
        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "Mã Lô Hàng", "Tên Sản Phẩm", "Số lượng", "Ngày Sản Xuất", "Ngày Hết Hạn","Tình trạng"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        loadSupplierTable(listlohang);
        loaddatacbbox(listproduct);

        // Add action listener for the "Search" button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String time = timeTextField.getText();
                String productName = (String) productNameComboBox.getSelectedItem();

                // Lấy IDSANPHAM tương ứng với tên sản phẩm được chọn
                int selectedProductId = getProductIDByName(productName);

                // Gọi phương thức search từ lớp lohangBUS và nhận kết quả trả về
                ArrayList<lohangDTO> searchResult = lohangBUS.search(time, selectedProductId);

                // Load dữ liệu vào bảng
                loadSupplierTable(searchResult);
            }
        });

        // Add action listener for the "Refresh" button
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Load all data from the database
                listlohang = lohangBUS.getALL();
                listproduct = productBUS.getALL();

                // Load data into the combobox and table
                loadSupplierTable(listlohang);
                loaddatacbbox(listproduct);
            }
        });
    }

    private int getProductIDByName(String productName) {
        for (ProductDTO product : this.listproduct) {
            if (product.getTEN().equals(productName)) {
                return product.getIDSP();
            }
        }
        return 0; // Trả về 0 nếu không tìm thấy
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new lohang());
        frame.pack();
        frame.setVisible(true);
    }
}
