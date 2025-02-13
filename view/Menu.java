package view;

import bus.LoginBUS;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import bus.LoginBUS;
import java.awt.Toolkit;
//import bus.StatisticsBUS;
public class Menu extends JFrame {

    private JPanel contentPanel;
    private Map<String, JButton> buttonMap = new HashMap<>();
    public LoginBUS loginBUS;
    private boolean isEmployee;
//	StatisticsBUS statisticsBUS = new StatisticsBUS();

    public void setIsEmployee(boolean isEmployee) {
        this.isEmployee = isEmployee;
    }

    public Menu() {
        this.setTitle("Trang chủ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        // Đặt kích thước của cửa sổ bằng với kích thước màn hình
        this.setSize(screenWidth, screenHeight);

        setMinimumSize(new java.awt.Dimension(700, 650));
        this.setResizable(false);

        this.setVisible(true);

        this.setLocationRelativeTo(null);

        /*==================== Begin: Color ====================*/
        Color woodColor = new Color(210, 180, 140);
        Color darkGreen = new Color(0, 100, 0);
        Color buttonGreen = new Color(0, 150, 0);
        Color purple_light = new Color(202, 207, 255);
        /*==================== End: Color ====================*/

 /* ==================== Begin: Sidebar  ====================*/
        getContentPane().setLayout(new BorderLayout(0, 0));
        JPanel navbar_panel = new JPanel();
        navbar_panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        navbar_panel.setPreferredSize(new Dimension(250, 400));
        getContentPane().add(navbar_panel, BorderLayout.WEST);

        navbar_panel.setBackground(Color.black);

        JLabel picture_navbar = new JLabel("");
        picture_navbar.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(72, 61, 139)));

        Image my_email = new ImageIcon("../doan1/src/main/java/img/logo1.jpg").getImage();
        my_email = my_email.getScaledInstance(250, 200, Image.SCALE_SMOOTH);
        picture_navbar.setIcon(new ImageIcon(my_email));
        navbar_panel.add(picture_navbar);
        createMenuButton(navbar_panel, "Tài khoản");
        createMenuButton(navbar_panel, "Khách Hàng");        
        createMenuButton(navbar_panel, "Hóa Đơn");
        createMenuButton(navbar_panel, "Sản Phẩm");
        createMenuButton(navbar_panel, "Phiếu Nhập");
        createMenuButton(navbar_panel, "Nhà Cung Cấp");
        createMenuButton(navbar_panel, "Thống Kê");
        createMenuButton(navbar_panel, "Khác");

        createMenuButton(navbar_panel, "Đăng Xuất");
        createMenuButton(navbar_panel, "Thoát");

        /* ==================== End: Sidebar  ====================*/
 /* ==================== Begin: Button  ====================*/
        contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setPreferredSize(new Dimension(1110, 856));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

    }

    public void createMenuButton(JPanel panel, String text) {
        Image iconImage = new ImageIcon("../doan1/src/main/java/img/" + text.toLowerCase() + ".png").getImage();
        Image scaledImage = iconImage.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage);
        JButton button = new JButton(text, icon);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setForeground(Color.white);
        button.setBackground(Color.BLACK);
        button.setFont(new Font("Verdana", Font.BOLD, 16));
        button.setBorder(null);
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(250, 60));

        /* ==================== Begin: Hover  ====================*/
        button.addMouseListener(new MouseAdapter() {
            private boolean clicked = false;

            @Override
            public void mouseClicked(MouseEvent e) {
                clicked = !clicked;
                if (clicked) {
                    button.setBackground(Color.decode("#d1d1ef"));
                    buttonMap.values().stream()
                            .filter(b -> b != button)
                            .forEach(b -> b.setBackground(Color.BLACK));
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!clicked) {
                    button.setBackground(Color.BLACK.brighter());
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!clicked) {
                    button.setBackground(Color.BLACK);
                }
            }
        });

        /* ==================== End: Hover  ====================*/
        buttonMap.put(text, button);
        button.addActionListener(e -> handleButtonClick(text));

        panel.add(button);
    }

    public void handleButtonClick(String text) {
        JButton button = buttonMap.get(text);
        if (button != null) {
            System.out.println("Button123 '" + text + "' clicked");
            contentPanel.removeAll();
            switch (text) {
                case "Sản Phẩm": {
                    menuProduct menuProduct = new menuProduct();
                    contentPanel.add(menuProduct);
                    contentPanel.revalidate();
                    contentPanel.repaint();
                    break;
                }

//                case "Phiếu thử":{
//                    phieuthu phieuthu = new phieuthu();
//                    contentPanel.add(phieuthu);
//                    contentPanel.revalidate();
//                    contentPanel.repaint();
//                }
//                case "Phiếu Nhập": {
//                    Receipt receipt = new Receipt();
//                    contentPanel.add(receipt);
//                    contentPanel.revalidate();
//                    contentPanel.repaint();
//                    break;
//                }
//
                case "Khác": {
                    khacmenu customer = new khacmenu();
                    contentPanel.add(customer);
                    contentPanel.revalidate();
                    contentPanel.repaint();
                    break;
                }
//
//                case "Hóa Đơn": {
//                    Invoice invoice = new Invoice();
//                    contentPanel.add(invoice);
//                    contentPanel.revalidate();
//                    contentPanel.repaint();
//                    break;
//                }
//
//                case "Nhân Viên": {
//                    if (!isEmployee) {
//                        Staff staff = new Staff();
//                        contentPanel.add(staff);
//                        contentPanel.revalidate();
//                        contentPanel.repaint();
//                    }
//                    break;
//                }
//
                case "Nhà Cung Cấp": {
                    Supplier supplier = new Supplier();
                    contentPanel.add(supplier);
                    contentPanel.revalidate();
                    contentPanel.repaint();
                    break;
                }
//
//                case "Thống Kê": {
//                    menuStatistics statistics = new menuStatistics();
//                    contentPanel.add(statistics);
//                    contentPanel.revalidate();
//                    contentPanel.repaint();
//                    break;
//                }

                case "Đăng Xuất": {
                    Login login = new Login();
                    login.displayLogin();
                    this.dispose();
                    break;
                }
                case "Thoát":{
                    System.exit(0);
                    break;
                }
                default:
                    throw new IllegalArgumentException("Unexpected value: " + text);
            }

        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Menu frame = new Menu();
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
