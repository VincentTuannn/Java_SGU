package view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.border.MatteBorder;

import bus.LoginBUS;
import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;

public class Login extends JFrame implements MouseListener {

    private static final String LoadataEmail = null;
    private static final String LoadataPassword = null;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField emailText;
    private JPasswordField passwordField;
    private JLabel lblNewLabel_4;

    public Login() {
        // Khai báo và khởi tạo giá trị RGB cho màu sắc
        // Khởi tạo một instance của LoginBUS và màu sắc
        Action action = new LoginBUS(this);
        // Thiết lập màu nền của cửa sổ là màu trắng
        getContentPane().setBackground(Color.white);
        GridLayout layout = new GridLayout(1, 2);
        layout.setHgap(5);
        getContentPane().setLayout(layout);

        //Panel bên trái
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridLayout());
        getContentPane().add(panel);

        ImageIcon myPicture = new ImageIcon("../doan1/src/main/java/img/logo1.jpg");
        Image image = myPicture.getImage().getScaledInstance(350, 400, Image.SCALE_SMOOTH);
        myPicture = new ImageIcon(image);
        JLabel lblNewLabel = new JLabel(myPicture);
        panel.add(lblNewLabel);
        // Panel bên phải của cửa sổ
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        panel_1.setLayout(new GridBagLayout());
        getContentPane().add(panel_1);

        JLabel close = new JLabel("");
        close.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ImageIcon closeIcon = new ImageIcon("../doan1/src/main/java/img/close.png");
        Image imageClose = closeIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        closeIcon = new ImageIcon(imageClose);
        close.setIcon(closeIcon);
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        panel_1.add(close);
        GridBagConstraints dbc_close = new GridBagConstraints();
        dbc_close.gridx = 3;
        dbc_close.gridy = 0;
        panel_1.add(close, dbc_close);

        // Label "LOGIN"
        JLabel lblNewLabel_1 = new JLabel("LOGIN");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(15, 0, 15, 0);
        gbc_lblNewLabel_1.gridx = 1;
        gbc_lblNewLabel_1.gridy = 1;
        panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);

        // Ô nhập địa chỉ email
        emailText = new JTextField("Nhập địa chỉ email");
        emailText.setFont(new Font("Tahoma", Font.PLAIN, 18));
        emailText.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                // Hiển thị placeholder khi không có focus
                if (emailText.getText().isEmpty()) {
                    emailText.setText("Nhập địa chỉ email");
                    emailText.setForeground(Color.GRAY);
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                // Xóa placeholder khi có focus
                if (emailText.getText().equals("Nhập địa chỉ email")) {
                    emailText.setText("");
                    emailText.setForeground(Color.BLACK);
                }
            }
        }); // Kết thúc chú thích

        // Label hiển thị chữ "Email:"
        JLabel lblNewLabel_2 = new JLabel("Email:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_2.gridx = 0;
        gbc_lblNewLabel_2.gridy = 2;
        panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);

        // Ô nhập địa chỉ email
        emailText.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(34, 33, 75)));
        GridBagConstraints gbc_emailText = new GridBagConstraints();
        gbc_emailText.gridwidth = 3;
        gbc_emailText.fill = GridBagConstraints.HORIZONTAL;
        gbc_emailText.insets = new Insets(10, 0, 10, 0);
        gbc_emailText.gridx = 0;
        gbc_emailText.gridy = 3;
        panel_1.add(emailText, gbc_emailText);
        emailText.setColumns(50);

        // Ô nhập mật khẩu
        passwordField = new JPasswordField("");
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Xóa placeholder mật khẩu khi có focus
                if (String.valueOf(passwordField.getPassword()).equals("**********************************************************************")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Hiển thị placeholder mật khẩu khi không có focus
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText("Nhập mật khẩu ");
                    passwordField.setForeground(Color.GRAY);
                }
            }
        }); // Kết thúc chú thích

        // Label hiển thị chữ "Password:"
        JLabel lblNewLabel_3 = new JLabel("Password:");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();

        gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_3.gridx = 0;
        gbc_lblNewLabel_3.gridy = 4;
        panel_1.add(lblNewLabel_3, gbc_lblNewLabel_3);

        // Ô nhập mật khẩu
        passwordField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(34, 33, 75)));
        GridBagConstraints gbc_passwordField = new GridBagConstraints();
        gbc_passwordField.gridwidth = 3;//độ rộng của phần tử
        gbc_passwordField.insets = new Insets(10, 0, 10, 0);
        gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordField.gridx = 0;
        gbc_passwordField.gridy = 5;
        panel_1.add(passwordField, gbc_passwordField);

        // Label "Quên Mật Khẩu?" dùng để khôi phục mật khẩu
        lblNewLabel_4 = new JLabel("Quên Mật Khẩu?");
        lblNewLabel_4.addMouseListener(this);
        lblNewLabel_4.setFont(lblNewLabel_4.getFont().deriveFont(lblNewLabel_4.getFont().getStyle() | Font.BOLD));
        lblNewLabel_4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
        gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel_4.gridx = 0;
        gbc_lblNewLabel_4.gridy = 7;
        panel_1.add(lblNewLabel_4, gbc_lblNewLabel_4);

        // Nút "Đăng Nhập"
        JButton Login_button = new JButton("Đăng Nhập");

        Login_button.addActionListener(action);

        Login_button.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        Login_button.setFont(new Font("Tahoma", Font.BOLD, 18));
        Login_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Login_button.setFocusPainted(false);
        Login_button.setRequestFocusEnabled(true);
        Login_button.requestFocus();
        Login_button.setBackground(Color.black);
        Login_button.setForeground(Color.white);
        GridBagConstraints gbc_Login_button = new GridBagConstraints();
        gbc_Login_button.weighty = 0.3;
        gbc_Login_button.insets = new Insets(0, 0, 0, 10);
        gbc_Login_button.fill = GridBagConstraints.HORIZONTAL;
        gbc_Login_button.ipady = 20;
        gbc_Login_button.weightx = 1.0;
        gbc_Login_button.gridwidth = 3;
        gbc_Login_button.gridx = 0;
        gbc_Login_button.gridy = 9;
        panel_1.add(Login_button, gbc_Login_button);

    }

    public String LoadataEmail() {
        String login_email = emailText.getText();

        return login_email;
    }

    public String LoadataPassword() {
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        return password;

    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1.0");
        try {
            displayLogin();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void displayLogin() {
        Login loginBr = new Login();
        loginBr.setSize(650, 350);
        loginBr.setUndecorated(true);
        loginBr.setVisible(true);
        loginBr.setLocationRelativeTo(null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == lblNewLabel_4) {
            ForgotPassword fgp = new ForgotPassword();
            fgp.setUndecorated(true);
            fgp.setLocationRelativeTo(null);
            fgp.setVisible(true);
            dispose();
        }
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
}
