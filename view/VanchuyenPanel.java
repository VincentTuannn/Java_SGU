package view;

import bus.vanchuyenBUS;
import dao.vanchuyenDAO;
import dto.vanchuyenDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class VanchuyenPanel extends JPanel {

    private JTextField searchTextField;
    private JTextField idTextField;
    private JTextField nameTextField;
    private JComboBox<String> statusComboBox;
    private JButton searchButton;
    private JButton editButton;
    private JButton refreshButton;
    private JButton addButton;
    private JTable table;

    private vanchuyenBUS VANCHUYENBUS = new vanchuyenBUS();
    ArrayList<vanchuyenDTO> listvanchuyen = VANCHUYENBUS.getALL();

    public VanchuyenPanel() {
        // Set layout for the panel
        setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Phương thức vận chuyển", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Input panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel searchLabel = new JLabel("Từ khóa tìm kiếm:");
        inputPanel.add(searchLabel, gbc);

        gbc.gridx = 1;
        searchTextField = new JTextField(15);
        inputPanel.add(searchTextField, gbc);

        gbc.gridx = 2;
        searchButton = new JButton("Tìm kiếm");
        inputPanel.add(searchButton, gbc);

        gbc.gridx = 3;
        JLabel idLabel = new JLabel("ID:");
        inputPanel.add(idLabel, gbc);

        gbc.gridx = 4;
        idTextField = new JTextField(15);
        idTextField.setEditable(false); // Prevent editing
        inputPanel.add(idTextField, gbc);

        gbc.gridx = 5;
        JLabel nameLabel = new JLabel("Tên:");
        inputPanel.add(nameLabel, gbc);

        gbc.gridx = 6;
        nameTextField = new JTextField(15);
        inputPanel.add(nameTextField, gbc);

        gbc.gridx = 7;
        JLabel statusLabel = new JLabel("Trạng thái:");
        inputPanel.add(statusLabel, gbc);

        gbc.gridx = 8;
        String[] statuses = {"Kích hoạt", "Khóa"}; // Sample statuses
        statusComboBox = new JComboBox<>(statuses);
        inputPanel.add(statusComboBox, gbc);

        gbc.gridx = 9;
        editButton = new JButton("Sửa");
        inputPanel.add(editButton, gbc);

        gbc.gridx = 10;
        refreshButton = new JButton("Làm mới");
        inputPanel.add(refreshButton, gbc);

        gbc.gridx = 11;
        addButton = new JButton("Thêm");
        inputPanel.add(addButton, gbc);

        add(inputPanel, BorderLayout.CENTER);

        // Table
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.SOUTH);
        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null}
                },
                new String[]{
                    "Mã vận chuyển", "Tên", "Tình trạng"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        loadDataTable();

        // Add mouse listener to table
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = table.getSelectedRow();
                displayRowSelected(row);
            }
        });

        // Add action listener to search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = searchTextField.getText();
                ArrayList<vanchuyenDTO> searchResult = VANCHUYENBUS.search(keyword);
                loadDataTable1(searchResult);
            }
        });

        // Add action listener to edit button
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0 && row < table.getRowCount()) {
                    String id = idTextField.getText();
                    String newName = nameTextField.getText().trim();
                    String oldName = (String) table.getValueAt(row, 1);

                    if (id.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn dịch vụ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if (!newName.equals(oldName)) { // Check if the new name is different from the old one
                            // Check if the new name exists in the list
                            boolean nameExists = false;
                            for (vanchuyenDTO item : listvanchuyen) {
                                if (item.getTEN().equals(newName)) {
                                    nameExists = true;
                                    break;
                                }
                            }

                            if (nameExists) {
                                JOptionPane.showMessageDialog(null, "Tên đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            } else {
                                // No error, proceed with updating data
                                // Implement edit functionality here
                                vanchuyenDTO dto = new vanchuyenDTO();
                                dto.setIDVANCHUYEN(Integer.parseInt(id));
                                dto.setTEN(newName);
                                dto.setStatus(statusComboBox.getSelectedIndex() == 0 ? 1 : 0); // Assuming index 0 is "Kích hoạt"
                                if (VANCHUYENBUS.update(dto)) {
                                    loadDataTable(); // Reload data after successful update
                                    JOptionPane.showMessageDialog(null, "Sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                                } else {
                                    JOptionPane.showMessageDialog(null, "Sửa thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                }
                                clearFields();

                            }
                        } else {
                            // No error, proceed with updating data
                            // Implement edit functionality here
                            vanchuyenDTO dto = new vanchuyenDTO();
                            dto.setIDVANCHUYEN(Integer.parseInt(id));
                            dto.setTEN(newName);
                            dto.setStatus(statusComboBox.getSelectedIndex() == 0 ? 1 : 0); // Assuming index 0 is "Kích hoạt"
                            if (VANCHUYENBUS.update(dto)) {
                                loadDataTable(); // Reload data after successful update
                                JOptionPane.showMessageDialog(null, "Sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                            } else {
                                JOptionPane.showMessageDialog(null, "Sửa thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            }
                            clearFields();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn dịch vụ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Add action listener to refresh button
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataTable();
                clearFields();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kiểm tra dữ liệu nhập vào
                String newName = nameTextField.getText().trim();
                if (newName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập tên phương thức thanh toán", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    return; // Dừng nếu tên trống
                }

                // Kiểm tra xem tên mới đã tồn tại chưa
                boolean nameExists = false;
                for (vanchuyenDTO item : listvanchuyen) {
                    if (item.getTEN().equalsIgnoreCase(newName)) {
                        nameExists = true;
                        break;
                    }
                }

                if (nameExists) {
                    JOptionPane.showMessageDialog(null, "Tên phương thức thanh toán đã tồn tại", "Thông báo", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Thêm mới phương thức thanh toán
                    vanchuyenDTO dto = new vanchuyenDTO();
                    dto.setTEN(newName);
                    dto.setStatus(1); // Giả sử mặc định status là 1 (Kích hoạt)

                    int id = vanchuyenDAO.getInstance().getAutoIncrement();
                    dto.setIDVANCHUYEN(id);

                    // Thêm mới vào cơ sở dữ liệu
                    if (VANCHUYENBUS.addvanchuyen(dto)) {
                        loadDataTable(); // Reload data sau khi thêm thành công
                        JOptionPane.showMessageDialog(null, "Thêm phương thức thanh toán thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        clearFields(); // Xóa các trường nhập liệu
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm phương thức thanh toán thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

    }

    public void loadDataTable1(ArrayList<vanchuyenDTO> list) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows

        for (vanchuyenDTO data : list) {
            String status = data.getStatus() == 1 ? "Kích hoạt" : "Khóa";

            model.addRow(new Object[]{
                data.getIDVANCHUYEN(), // Assuming ID is the first column
                data.getTEN(), // Replace getField1() with the actual method to get the data for each column
                status
            });
        }
    }

    public void loadDataTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        listvanchuyen = VANCHUYENBUS.getALL();
        model.setRowCount(0); // Clear existing rows

        for (vanchuyenDTO data : listvanchuyen) {
            String status = data.getStatus() == 1 ? "Kích hoạt" : "Khóa";

            model.addRow(new Object[]{
                data.getIDVANCHUYEN(), // Assuming ID is the first column
                data.getTEN(), // Replace getField1() with the actual method to get the data for each column
                status
            });
        }
    }

    public void displayRowSelected(int row) {
        if (row >= 0 && row < table.getRowCount()) {
            idTextField.setText(String.valueOf(table.getValueAt(row, 0)));
            nameTextField.setText(String.valueOf(table.getValueAt(row, 1)));
            String status = String.valueOf(table.getValueAt(row, 2));
            statusComboBox.setSelectedItem(status);
        }
    }

    public void clearFields() {
        idTextField.setText("");
        nameTextField.setText("");
        statusComboBox.setSelectedIndex(0); // Reset to the first item
        searchTextField.setText("");

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new VanchuyenPanel());
        frame.pack();
        frame.setVisible(true);
    }
}
